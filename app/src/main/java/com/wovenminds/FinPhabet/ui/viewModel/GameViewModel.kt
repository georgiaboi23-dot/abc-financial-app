package com.wovenminds.FinPhabet.ui.viewModel

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wovenminds.FinPhabet.data.datastore.PremiumManager
import com.wovenminds.FinPhabet.data.model.GameMode
import com.wovenminds.FinPhabet.data.model.ContentItem
import com.wovenminds.FinPhabet.data.model.Question
import com.wovenminds.FinPhabet.data.repository.LearnRepository
import com.wovenminds.FinPhabet.data.repository.QuestionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.wovenminds.FinPhabet.data.billing.BillingManager
import androidx.compose.runtime.State
import androidx.lifecycle.AndroidViewModel
import com.wovenminds.FinPhabet.audio.AudioManager
import com.wovenminds.FinPhabet.R
import kotlinx.serialization.descriptors.PrimitiveKind

class GameViewModel(private val learnRepository: LearnRepository,
    private val questionRepository: QuestionRepository,
    private val premiumManager: PremiumManager) : ViewModel()
{
    val currentQuestion: Question? get() = _uiState.value.selectedPack?.questions?.getOrNull(_uiState.value.questionIndex)

    val isPremium: Boolean get() = _uiState.value.isPremium

    private var audioManager: AudioManager? = null



    private var billingManager: BillingManager? = null

    private val _uiState = MutableStateFlow(GameState())
    val uiState: StateFlow<GameState> = _uiState
    val learnCount:Int get() = _uiState.value.learnItems.size

    private val learnItems = learnRepository.getLearnItems()

    val _currentGameMode = MutableStateFlow<GameMode>(GameMode.PRACTICE)

    val currentGameMode:StateFlow<GameMode> = _currentGameMode

    private val _isPremiumUnlocked = MutableStateFlow(false)

    val isPremiumUnlocked:StateFlow<Boolean> = _isPremiumUnlocked



    val currentLearnItem: ContentItem?get() = _uiState.value.learnItems.getOrNull(_uiState.value.currentLearnIndex)

    init{

        viewModelScope.launch {
            premiumManager.isPremiumUnlocked.collect{
                unlocked -> _uiState.update {
                    it.copy(isPremium= unlocked)
            }
            }
        }
    }

    fun initAudioManager(context: Context)
    {
        if(audioManager == null)
        {
            audioManager = AudioManager(context.applicationContext)

            audioManager?.loadSound(R.raw.correctanswer)
            audioManager?.loadSound(R.raw.wronganswer)
        }
    }

    fun restorePurchase()
    {
        billingManager?.restorePurchases()
    }

    fun initBilling(context: Context)
    {
        if (billingManager != null)
            return

        billingManager = BillingManager(context.applicationContext)
        {
            unlockPremium()
        }

        billingManager?.startConnection {
            billingManager?.restorePurchases()
        }
    }

    fun purchaseChallenge(activity: Activity)
    {
        billingManager?.launchPurchase(activity)
    }

    private fun unlockPremium()
    {
        viewModelScope.launch {
            premiumManager.setPremiumUnlocked()

            _isPremiumUnlocked.value = true
        }
    }

    fun loadPremiumState()
    {
        viewModelScope.launch {

            _isPremiumUnlocked.value = premiumManager.isPremiumUnlocked()
        }
    }

    fun generatePracticeQuestion(isPremium: Boolean): Question
    {
        val items= learnRepository.getLearnItems()
        val premiumItems = if(_uiState.value.isPremium)
            questionRepository.getContent()
        else
            emptyList()

        val allItems = premiumItems + items

        if(allItems.size < 3 ) {
            throw IllegalStateException("Need at least 3 learn items for practice mode.")
        }
            val correctItem = allItems.random()
            val wrongOptions = allItems.filter{
                it.word != correctItem.word
            }.shuffled().take(2).map { it.word }

            val allOptions = (wrongOptions + correctItem.word).shuffled()

            return Question(
                id = correctItem.id,
                definition = correctItem.definition,
                options = allOptions,
                correctAnswer = correctItem.word
            )
    }


    fun loadLearnItems()
    {
        val freeItems = learnRepository.getLearnItems()
        val premiumItems = if(_uiState.value.isPremium)
            questionRepository.getContent()
        else
            emptyList()

        val allLearnItems = premiumItems + freeItems

        //learnCount = allLearnItems.size

        _uiState.value = _uiState.value.copy(learnItems = allLearnItems)
    }


    fun nextLearnItem()
    {
        val currentIndex = _uiState.value.currentLearnIndex

        if (currentIndex < _uiState.value.learnItems.size -1)
        {
            _uiState.update { it.copy(currentLearnIndex = currentIndex+1) }
        }
    }

    fun previousLearnItem()
    {
        val currentIndex = _uiState.value.currentLearnIndex

        if(currentIndex > 0)
        {
            _uiState.update { it.copy(currentLearnIndex = currentIndex - 1) }
        }
    }

    fun loadQuestion()
    {

        val questionPool = generatePracticeQuestion(_uiState.value.isPremium)

        _uiState.update{ it.copy(currentQuestion=questionPool)}
    }

    fun submitAnswer(answer: String)
    {
        val question = _uiState.value.currentQuestion?: return

        val isCorrect = answer == question.correctAnswer

        val nextIndex = _uiState.value.questionIndex + 1

        if (nextIndex > _uiState.value.totalQuestion)
        {
            _uiState.value = _uiState.value.copy(isGameOver = true)
        }
        else
        {
            _uiState.value = _uiState.value.copy(questionIndex = nextIndex)
        }

        if (isCorrect)
            audioManager?.playSound(R.raw.correctanswer)
        else
            audioManager?.playSound(R.raw.wronganswer)

        _uiState.update {
            it.copy(score = if (isCorrect) it.score +1
            else
            it.score)

        }
        loadQuestion()
    }

    override fun onCleared() {
        audioManager?.release()
        super.onCleared()
    }

    fun setMode(mode: GameMode)
    {
        _uiState.update{
            current -> current.copy(mode=mode)
        }
    }

    fun resetGame()
    {
        _uiState.value = _uiState.value.copy(
            score = 0,
            questionIndex = 0,
            totalQuestion = 10,
            currentQuestion =null,
            isGameOver = false
        )

    }

    fun onChallengeSelected()
    {

            _uiState.update {
                it.copy(showPurchaseDialog = true)
            }

    }

    fun dismissPurchaseDialog()
    {
        _uiState.update {
            it.copy(showPurchaseDialog = false)
        }
    }

    fun purchasePremium(activity: Activity)
    {

        billingManager?.launchPurchase(activity)
        viewModelScope.launch{
            premiumManager.setPremiumUnlocked()

            _uiState.value = _uiState.value.copy(isPremium = true,
                showPurchaseDialog = false)

            loadLearnItems()
            loadQuestion()
        }
    }

    private fun startChallenge()
    {
        loadQuestion()
    }

    fun unlockChallenge(activity: Activity)
    {
        if(isPremium)
        {
            startGame(GameMode.CHALLENGE)
        }
        else
            purchasePremium(activity)
    }

    fun startGame(mode: GameMode)
    {

        println("Start Game Mode: $mode")

        _currentGameMode.value = mode
        _uiState.update { it.copy (mode = mode,
            score = 0,
            isGameOver = false) }
        if (mode != GameMode.LEARN)
        {
            loadQuestion()
        }
    }


}
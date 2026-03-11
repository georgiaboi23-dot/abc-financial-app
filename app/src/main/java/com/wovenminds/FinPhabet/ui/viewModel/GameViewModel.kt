package com.wovenminds.FinPhabet.ui.viewModel

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

class GameViewModel(private val learnRepository: LearnRepository,
    private val questionRepository: QuestionRepository,
    private val premiumManager: PremiumManager) : ViewModel()
{
    val currentQuestion: Question? get() = _uiState.value.selectedPack?.questions?.getOrNull(_uiState.value.questionIndex)

    val isPremium: Boolean get() = _uiState.value.isPremium

    private val _uiState = MutableStateFlow(GameState())
    val uiState: StateFlow<GameState> = _uiState
    val learnCount:Int get() = _uiState.value.learnItems.size

    private val learnItems = learnRepository.getLearnItems()


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

        _uiState.update {
            it.copy(score = if (isCorrect) it.score +1
            else
            it.score)

        }
        loadQuestion()
    }

    fun setMode(mode: GameMode)
    {
        _uiState.update{
            current -> current.copy(mode=mode)
        }
    }

    fun resetGame()
    {
        _uiState.value = GameState()

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

    fun purchasePremium()
    {
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

    fun unlockChallenge()
    {
        _uiState.value = _uiState.value.copy(isPremium = true)

    }

    fun startGame(mode: GameMode)
    {

        println("Start Game Mode: $mode")


        _uiState.update { it.copy (mode = mode,
            score = 0,
            isGameOver = false) }
        if (mode != GameMode.LEARN)
        {
            loadQuestion()
        }
    }


}
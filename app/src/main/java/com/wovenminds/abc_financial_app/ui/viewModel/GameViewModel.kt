package com.wovenminds.abc_financial_app.ui.viewModel

import androidx.lifecycle.ViewModel
import com.wovenminds.abc_financial_app.ui.viewModel.GameState
import com.wovenminds.abc_financial_app.data.model.GameMode
import com.wovenminds.abc_financial_app.data.model.LearnItem
import com.wovenminds.abc_financial_app.data.model.Question
import com.wovenminds.abc_financial_app.data.model.QuestionPack
import com.wovenminds.abc_financial_app.data.repository.LearnRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel()
{

    private val learnRepository = LearnRepository()
    val currentQuestion: Question? get() = _uiState.value.selectedPack?.questions?.getOrNull(_uiState.value.questionIndex)
    private val _uiState = MutableStateFlow(GameState())
    val uiState: StateFlow<GameState> = _uiState
    val learnCount:Int get() = learnItems.size

    private val learnItems = learnRepository.getLearnItems()

    val currentLearnItem: LearnItem?get() = learnItems.getOrNull(_uiState.value.currentLearnIndex)


    fun selectPack(pack: QuestionPack)
    {
        _uiState.update {
            current -> current.copy(
                selectedPack = pack,
                questionIndex = 0,
                score =0,
                isGameOver = false
            )
        }
    }

    fun generatePracticeQuestion(): Question
    {
        val items= learnRepository.getLearnItems()

        if(items.size < 3 ) {
            throw IllegalStateException("Need at least 3 learn items for practice mode.")
        }
            val correctItem = items.random()
            val wrongOptions = items.filter{
                it.word != correctItem.word
            }.shuffled().take(2).map { it.word }

            val allOptions = (wrongOptions + correctItem.word).shuffled()

            return Question(
                definition = correctItem.definition,
                options = allOptions,
                correctAnswer = correctItem.word
            )
    }


    fun nextLearnItem()
    {
        val currentIndex = _uiState.value.currentLearnIndex

        if (currentIndex < learnItems.lastIndex)
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


        val questionPool = generatePracticeQuestion()

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
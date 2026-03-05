package com.wovenminds.abc_financial_app.game

import androidx.lifecycle.ViewModel
import com.wovenminds.abc_financial_app.game.model.GameMode
import com.wovenminds.abc_financial_app.game.model.LearnItem
import com.wovenminds.abc_financial_app.game.model.Question
import com.wovenminds.abc_financial_app.game.model.QuestionPack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel()
{
    val currentQuestion: Question? get() = _uiState.value.selectedPack?.questions?.getOrNull(_uiState.value.questionIndex)
    private val _uiState = MutableStateFlow(GameState())
    val uiState:StateFlow<GameState> = _uiState

    private val easyQuestions = listOf(Triple("What does ROI stand for?", listOf("Return on Investment","Rate of Income","Revenue on Interest"),"Return on Investment"))

    private val mediumQuestions = listOf(Triple("What is diversification?", listOf("Putting all money in one stock","Spreading investments","Selling everything"),"Spreading investments"))

    private val hardQuestions = listOf(Triple("What does P/E ratio measure?", listOf("Price to Earning","Profit Efficiency","Portfolio Equity"),"Price to Earnings"))
    private val learnItems:List<LearnItem> = listOf(LearnItem(id="A", letter = "A", word="Asset", definition = "Something of value that you own."))

    val currentLearnItem: LearnItem?get() = learnItems.getOrNull(_uiState.value.currentLearnIndex)



    fun selectPack(pack: QuestionPack)
    {
        _uiState.update{
            current -> current.copy(
                selectedPack = pack,
                questionIndex = 0,
                score =0,
                isGameOver = false
            )
        }
    }

    fun loadQuestion(pack: QuestionPack)
    {

        val state=_uiState.value
        val pack = state.selectedPack ?:
        return

        val questionPool = pack.questions


        if(state.questionIndex >= questionPool.size)
        {
            _uiState.update { it.copy(isGameOver = true) }
            return
        }
        val question = questionPool[state.questionIndex]

        _uiState.update{ it.copy( selectedPack = pack, questionIndex = 0, score = 0 )}
    }

    fun submitAnswer(answer: String)
    {
        val question = currentQuestion?: return

        val isCorrect = answer == question.correctAnswer

        _uiState.update { current ->
            val newScore = if (isCorrect) {
                current.score + 1
            } else {
                current.score
            }
            current.copy(score = newScore, questionIndex = current.questionIndex + 1)

        }
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

    fun startGame()
    {
        val pack = _uiState.value.selectedPack?:return

        _uiState.update { it.copy (questionIndex = 0,score = 0, isGameOver = false) }
    }
    fun getModeDescription(): String
    {
        return when (_uiState.value.mode)
        {
            GameMode.LEARN -> "Learn teaches financial concepts step-by-step with explanations."
            GameMode.PRACTICE -> "Practice lets you answer questions without scoring or pressure."
            GameMode.CHALLENGE -> "Challenge allows the ability to test knowledge with scoring and focus."
            null -> ""
        }
    }
}
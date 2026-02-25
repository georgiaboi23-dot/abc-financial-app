package com.wovenminds.abc_financial_app.game

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel()
{
    private val _uiState = MutableStateFlow(GameState())
    val uiState:StateFlow<GameState> = _uiState

    private val easyQuestions = listOf(Triple("What does ROI stand for?", listOf("Return on Investment","Rate of Income","Revenue on Interest"),"Return on Investment"))

    private val mediumQuestions = listOf(Triple("What is diversification?", listOf("Putting all money in one stock","Spreading investments","Selling everything"),"Spreading investments"))

    private val hardQuestions = listOf(Triple("What does P/E ratio measure?", listOf("Price to Earning","Profit Efficiency","Portfolio Equity"),"Price to Earnings"))

    init
    {
        loadQuestion()
    }

    private fun loadQuestion()
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

        _uiState.update{ it.copy( currentQuestion = question.prompt, options = question.options, correctAnswer = question.correctAnswer)}
    }

    fun submitAnswer(answer: String)
    {
        _uiState.update{
            current -> val newScore = if (answer == current.correctAnswer) {
            current.score + 1
        } else {
            current.score
        }
            val updatedState = current.copy(score = newScore, questionIndex = current.questionIndex + 1)
            updatedState
        }
        loadQuestion()

    }

    fun setDifficulty(difficulty: Difficulty)
    {
        _uiState.value = GameState()
        loadQuestion()
    }

    fun resetGame()
    {
        _uiState.value = GameState()
        loadQuestion()
    }
}
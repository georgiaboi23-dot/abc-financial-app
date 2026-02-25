package com.wovenminds.abc_financial_app.game

data class GameState(val score: Int=0, val questionIndex: Int=0, val currentQuestion: String = "",
                     val options: List<String> = emptyList(), val correctAnswer: String = "",
                     val selectedPack: QuestionPack? = null,val isGameOver: Boolean=false)
enum class Difficulty{
    EASY,
    MEDIUM,
    HARD
}

package com.wovenminds.abc_financial_app.game.model

data class Question(
    val prompt: String,
    val options: List<String>,
    val correctAnswer: String
)
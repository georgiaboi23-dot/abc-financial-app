package com.wovenminds.abc_financial_app.game

data class Question(
    val prompt: String,
    val options: List<String>,
    val correctAnswer: String
)
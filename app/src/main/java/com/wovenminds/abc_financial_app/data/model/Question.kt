package com.wovenminds.abc_financial_app.data.model

data class Question(
    val definition: String,
    val options: List<String>,
    val correctAnswer: String
)
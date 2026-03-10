package com.wovenminds.abc_financial_app.data.model

data class Question(
    val id:String,
    val definition: String,
    val options: List<String>,
    val correctAnswer: String
)
package com.wovenminds.FinPhabet.data.model

data class Question(
    val id:String,
    val definition: String,
    val options: List<String>,
    val correctAnswer: String
)
package com.wovenminds.FinPhabet.data.model

data class QuestionPack (
    val id: String,
    val title: String,
    val isPremium:Boolean,
    val questions: List<Question>
)
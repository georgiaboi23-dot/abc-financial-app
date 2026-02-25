package com.wovenminds.abc_financial_app.game

data class QuestionPack (
    val id: String,
    val title: String,
    val isPremium:Boolean,
    val questions: List<Question>
)
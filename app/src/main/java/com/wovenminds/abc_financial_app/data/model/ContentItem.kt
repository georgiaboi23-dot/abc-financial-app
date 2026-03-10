package com.wovenminds.abc_financial_app.data.model

data class ContentItem(
    //Unique identifier (important for scaling)
    val id: String,

    //Alphabet letter (A,B,C...)
    val letter: String,

    //Financial word
    val word: String,

    //Clear definition for learning
    val definition: String

)
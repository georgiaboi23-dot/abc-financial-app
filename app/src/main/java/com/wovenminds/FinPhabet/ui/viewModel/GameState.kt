package com.wovenminds.FinPhabet.ui.viewModel

import com.wovenminds.FinPhabet.data.model.ContentItem
import com.wovenminds.FinPhabet.data.model.GameMode
import com.wovenminds.FinPhabet.data.model.Question
import com.wovenminds.FinPhabet.data.model.QuestionPack

data class GameState(val score: Int=0,
                     val questionIndex: Int=0,
                     val totalQuestion: Int=10,
                     val currentLearnIndex: Int=0,
                     val currentQuestion: Question?=null,
                     val mode: GameMode = GameMode.LEARN,
                     val selectedPack: QuestionPack? = null,
                     val learnItems: List<ContentItem> = emptyList(),
                     val isPremium: Boolean=false,
                     val showPurchaseDialog: Boolean = false,
                     val isGameOver: Boolean=false)
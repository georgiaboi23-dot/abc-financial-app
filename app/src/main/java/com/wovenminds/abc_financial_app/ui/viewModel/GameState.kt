package com.wovenminds.abc_financial_app.ui.viewModel

import android.text.BoringLayout
import com.wovenminds.abc_financial_app.data.model.ContentItem
import com.wovenminds.abc_financial_app.data.model.GameMode
import com.wovenminds.abc_financial_app.data.model.Question
import com.wovenminds.abc_financial_app.data.model.QuestionPack

data class GameState(val score: Int=0,
                     val questionIndex: Int=0,
                     val currentLearnIndex: Int=0,
                     val currentQuestion: Question?=null,
                     val mode: GameMode? = null,
                     val selectedPack: QuestionPack? = null,
                     val learnItems: List<ContentItem> = emptyList(),
                     val isPremium: Boolean=false,
                     val showPurchaseDialog: Boolean = false,
                     val isGameOver: Boolean=false)
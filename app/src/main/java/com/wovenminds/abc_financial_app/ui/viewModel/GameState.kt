package com.wovenminds.abc_financial_app.ui.viewModel

import com.wovenminds.abc_financial_app.data.model.GameMode
import com.wovenminds.abc_financial_app.data.model.Question
import com.wovenminds.abc_financial_app.data.model.QuestionPack

data class GameState(val score: Int=0,
                     val questionIndex: Int=0,
                     val currentLearnIndex: Int=0,
                     val currentQuestion: Question?=null,
                     val mode: GameMode? = null,
                     val selectedPack: QuestionPack? = null,
                     val isGameOver: Boolean=false)
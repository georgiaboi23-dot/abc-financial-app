package com.wovenminds.abc_financial_app.game

import com.wovenminds.abc_financial_app.game.model.GameMode
import com.wovenminds.abc_financial_app.game.model.Question
import com.wovenminds.abc_financial_app.game.model.QuestionPack


data class GameState(val score: Int=0,
                     val questionIndex: Int=0,
                     val currentLearnIndex: Int=0,
                     val mode: GameMode? = null,
                     val selectedPack: QuestionPack? = null,
                     val isGameOver: Boolean=false)

package com.wovenminds.abc_financial_app.ui.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wovenminds.abc_financial_app.data.repository.LearnRepository
import com.wovenminds.abc_financial_app.data.repository.QuestionRepository
import com.wovenminds.abc_financial_app.data.datastore.PremiumManager

class GameViewModelFactory (private val context: Context): ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GameViewModel::class.java))
        {
            val learnRepository = LearnRepository()
            val questionRepository = QuestionRepository()
            val premiumManager = PremiumManager(context)

            return GameViewModel(learnRepository,questionRepository, premiumManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
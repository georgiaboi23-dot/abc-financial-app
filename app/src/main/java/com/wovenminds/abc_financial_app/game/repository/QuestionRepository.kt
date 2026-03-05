package com.wovenminds.abc_financial_app.game.repository

import com.wovenminds.abc_financial_app.game.model.Question
import com.wovenminds.abc_financial_app.game.model.QuestionPack

object QuestionRepository
{
    val freePack = QuestionPack(
        id = "free_pack",
        title = "Free Basics",
        isPremium = false,
        questions = listOf(
            Question(
                prompt = "What does ROI stand for?",
                options = listOf("Return on Investment", "Rate of Income", "Revenue on Interest"),
                "Return on Investment"
            )
        )
    )
    val premiumPack = QuestionPack(
        id = "premium_investing",
        title = "Investing Fundamentals",
        isPremium = true,
        questions = listOf(
            Question(
                prompt = "What is diversification?",
                options = listOf(
                    "Putting all money in one stock",
                    "Spreading investments",
                    "Selling everything"
                ),
                correctAnswer = "Spreading investments"
            )
        )
    )
    fun getAllPacks(): List<QuestionPack>
    {
        return listOf(freePack,premiumPack)
    }

}
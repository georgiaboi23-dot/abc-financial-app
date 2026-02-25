package com.wovenminds.abc_financial_app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wovenminds.abc_financial_app.game.QuestionPack

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "packs")
    {
        composable("home") {
            HomeScreen( onStartGame = { navController.navigate("game")})
        }
        composable("game"){
            GameScreen()
        }
        composable("packs")
        {
            PackSelectionScreen(onPackSelected = { pack -> navController.navigate("game")})
        }
    }
}
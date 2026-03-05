package com.wovenminds.abc_financial_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wovenminds.abc_financial_app.game.GameViewModel
import com.wovenminds.abc_financial_app.ui.screens.GameScreen
import com.wovenminds.abc_financial_app.ui.screens.HomeScreen
import com.wovenminds.abc_financial_app.ui.screens.PackSelectionScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val gameViewModel: GameViewModel = viewModel()

    NavHost(navController = navController, startDestination = "home")
    {
        composable("home") {
            HomeScreen(onStart = { navController.navigate("packs") })
        }
        composable("game"){

            GameScreen(
                viewModel = gameViewModel,
                onClose =
                    {
                        navController.popBackStack("packs", false)
                    }
            )
        }
        composable("packs")
        {

            PackSelectionScreen(
                viewModel = gameViewModel, onStartGame =
                    {

                        navController.navigate("game")
                    }
            )
        }
    }
}

package com.wovenminds.FinPhabet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wovenminds.FinPhabet.ui.viewModel.GameViewModel
import com.wovenminds.FinPhabet.ui.screens.GameScreen
import com.wovenminds.FinPhabet.ui.screens.HomeScreen
import com.wovenminds.FinPhabet.ui.screens.PackSelectionScreen
import com.wovenminds.FinPhabet.data.model.GameMode

@Composable
fun AppNavigation(gameViewModel: GameViewModel) {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "home")
    {
        composable("home") {
            HomeScreen(gameViewModel,  navController)
        }
        composable("game/{mode}"){
           backStackEntry -> val modeString = backStackEntry.arguments?.getString("mode")

            val mode = GameMode.valueOf(modeString!!)

            GameScreen(
                viewModel = gameViewModel,
                mode=mode,
                onClose =
                    {
                        navController.popBackStack()
                    }
            )
        }
        composable("packs")
        {

            PackSelectionScreen(
                viewModel = gameViewModel, onStartGame =
                    {
                        mode -> gameViewModel.startGame(mode)
                    navController.navigate("game/$mode")
                    }
            )
        }
    }
}

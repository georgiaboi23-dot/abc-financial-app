package com.wovenminds.abc_financial_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wovenminds.abc_financial_app.ui.viewModel.GameViewModel
import com.wovenminds.abc_financial_app.ui.screens.GameScreen
import com.wovenminds.abc_financial_app.ui.screens.HomeScreen
import com.wovenminds.abc_financial_app.ui.screens.PackSelectionScreen
import com.wovenminds.abc_financial_app.data.model.GameMode
import com.wovenminds.abc_financial_app.ui.viewModel.GameViewModelFactory

@Composable
fun AppNavigation(gameViewModel: GameViewModel) {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "home")
    {
        composable("home") {
            HomeScreen(onStart = { navController.navigate("packs") })
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

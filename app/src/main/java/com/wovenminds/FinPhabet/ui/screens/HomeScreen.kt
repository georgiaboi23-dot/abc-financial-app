package com.wovenminds.FinPhabet.ui.screens


import android.app.Activity
import android.graphics.Color
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wovenminds.FinPhabet.ui.theme.CountopiaBlue
import com.wovenminds.FinPhabet.ui.theme.conColorval
import com.wovenminds.FinPhabet.ui.theme.gameButtonRust
import com.wovenminds.FinPhabet.ui.theme.gameTextBlack
import com.wovenminds.FinPhabet.ui.theme.gameTextWhite
import com.wovenminds.FinPhabet.ui.viewModel.GameViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen( viewModel: GameViewModel, navController: NavController)
{

    val context = LocalContext.current

    val isPremiumUnlocked by viewModel.isPremiumUnlocked.collectAsState()
    val activity = context as? Activity


    LaunchedEffect(Unit) {

            viewModel.initBilling(context)
            viewModel.restorePurchase()

    }
    Scaffold(
        containerColor = CountopiaBlue,
        topBar = {
            TopAppBar(
                title = {Text("Home")},
                colors = TopAppBarDefaults.topAppBarColors(containerColor = CountopiaBlue, titleContentColor = conColorval, actionIconContentColor = conColorval),
                actions = { IconButton(
                    onClick = {activity?.finishAffinity()
                    android.os.Process.killProcess(android.os.Process.myPid())
                    System.exit(0)}
                ) {Text("X", style= MaterialTheme.typography.titleMedium) }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(text = "ABC Financial")
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                navController.navigate("packs")
            },
                colors = ButtonDefaults.buttonColors(gameButtonRust, gameTextWhite),
                border = BorderStroke(1.dp,gameTextWhite))
            {
                Text("Select a Path")
            }
        }
    }
}
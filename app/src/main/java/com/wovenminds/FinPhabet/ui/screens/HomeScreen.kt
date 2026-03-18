package com.wovenminds.FinPhabet.ui.screens

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wovenminds.FinPhabet.ui.viewModel.GameViewModel

@Composable
fun HomeScreen( viewModel: GameViewModel, navController: NavController)
{

    val context = LocalContext.current

    val isPremiumUnlocked by viewModel.isPremiumUnlocked.collectAsState()


    LaunchedEffect(Unit) {

            viewModel.initBilling(context)
            viewModel.restorePurchase()

    }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = "ABC Financial")
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("packs")
        })
        {
            Text("Select a Path")
        }
    }
}
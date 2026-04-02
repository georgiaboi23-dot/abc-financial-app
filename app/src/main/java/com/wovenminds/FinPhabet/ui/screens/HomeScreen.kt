package com.wovenminds.FinPhabet.ui.screens

import ads_mobile_sdk.h6
import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
        topBar = {
            TopAppBar(
                title = {Text("Home")},
                actions = { IconButton(
                    onClick = {activity?.finish()}
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
            })
            {
                Text("Select a Path")
            }
        }
    }
}
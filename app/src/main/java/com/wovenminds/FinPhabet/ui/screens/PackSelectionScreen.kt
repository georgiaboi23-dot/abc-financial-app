package com.wovenminds.FinPhabet.ui.screens

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.wovenminds.FinPhabet.data.model.GameMode
import com.wovenminds.FinPhabet.ui.viewModel.GameViewModel
import com.wovenminds.FinPhabet.data.model.QuestionPack



@Composable
fun PackSelectionScreen( viewModel: GameViewModel, onStartGame: (GameMode)-> Unit)
{

    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val activity = context as Activity
    var showPurchaseDialog by remember {
        mutableStateOf(false)
    }

    var selectedPack by remember {
        mutableStateOf<QuestionPack?> (null)
    }

        Column(
         modifier = Modifier.fillMaxSize(),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally
     )
     {
         Text("Choose Mode")
         Spacer(modifier = Modifier.height(16.dp))


         Button({
             viewModel.setMode(GameMode.LEARN)
             viewModel.startGame(GameMode.LEARN)
             onStartGame(GameMode.LEARN)
         }, modifier = Modifier.fillMaxWidth())
         {
             Text("LEARN")

         }
         Spacer(modifier = Modifier.height(4.dp))
         Text(text= getModeDescription(GameMode.LEARN))
         Spacer(modifier = Modifier.height(8.dp))


         Button(onClick = {
             viewModel.setMode(GameMode.PRACTICE)
             viewModel.startGame(GameMode.PRACTICE)
             onStartGame(GameMode.PRACTICE)
         }, modifier = Modifier.fillMaxWidth()) {

                 Text("PRACTICE")

         }
         Spacer(modifier = Modifier.height(4.dp))
         Text(text=getModeDescription(GameMode.PRACTICE))

         Spacer(modifier = Modifier.height(8.dp))

         Button(onClick = {
             if(!state.isPremium)
             {
                 showPurchaseDialog = true
             }
             else {
                 viewModel.setMode(GameMode.CHALLENGE)
                 viewModel.onChallengeSelected()
                 viewModel.startGame(GameMode.CHALLENGE)
                 onStartGame(GameMode.CHALLENGE)
             }
         }, modifier = Modifier.fillMaxWidth()) {
             Text("CHALLENGE")

         }
         Spacer(modifier = Modifier.height(4.dp))
         Text(text=getModeDescription(GameMode.CHALLENGE))
         Spacer(modifier = Modifier.height(8.dp))
     }

    if(showPurchaseDialog)
    {
        PremiumPurchaseDialog (onDismiss = {
            showPurchaseDialog = false},
            {
                showPurchaseDialog = false
                viewModel.unlockChallenge(activity)
                //viewModel.purchasePremium()
            }
        )
    }
}


fun getModeDescription(mode: GameMode): String {
    return when (mode) {
        GameMode.LEARN -> "Learn teaches financial concepts step-by-step with explanations."
        GameMode.PRACTICE -> "Practice lets you answer questions without scoring or pressure."
        GameMode.CHALLENGE -> "Challenge allows the ability to test knowledge with scoring and focus."
        null -> ""
    }
}
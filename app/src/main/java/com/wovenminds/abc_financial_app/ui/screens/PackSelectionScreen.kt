package com.wovenminds.abc_financial_app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wovenminds.abc_financial_app.game.model.GameMode
import com.wovenminds.abc_financial_app.game.GameViewModel
import com.wovenminds.abc_financial_app.game.repository.QuestionRepository

@Composable
fun PackSelectionScreen( viewModel: GameViewModel, onStartGame: ()-> Unit)
{
    val packs= QuestionRepository.getAllPacks()
    val state by viewModel.uiState.collectAsState()

    Text(text=viewModel.getModeDescription())


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
             onStartGame()
         }, modifier = Modifier.fillMaxWidth())
         {
             Text("LEARN")
         }

         Button(onClick = {
             viewModel.setMode(GameMode.PRACTICE)
             onStartGame()
         }, modifier = Modifier.fillMaxWidth()) {

                 Text("PRACTICE")
         }

         Button(onClick = {
             onStartGame()

         }, modifier = Modifier.fillMaxWidth()) {
             Text("CHALLENGE")
         }
     }
}
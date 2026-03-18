package com.wovenminds.FinPhabet.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.wovenminds.FinPhabet.data.model.GameMode
import com.wovenminds.FinPhabet.ui.viewModel.GameViewModel
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

@Composable
fun QuestionScreen(viewModel: GameViewModel) {
    val state by viewModel.uiState.collectAsState()
    val question = state.currentQuestion ?: return

    val context = LocalContext.current

    val gameMode by viewModel.currentGameMode.collectAsState()



    LaunchedEffect(Unit) {
        viewModel.initAudioManager(context)
    }


    Column(modifier = Modifier.fillMaxSize().padding(24.dp)
        .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp))
    {
        Text(
            text = question.definition,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))

        question.options.forEach { option ->
            Button(
                onClick = {
                    viewModel.submitAnswer(option)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(option)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Score: ${state.score}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
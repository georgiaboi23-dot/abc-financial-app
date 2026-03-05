package com.wovenminds.abc_financial_app.ui.screens

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wovenminds.abc_financial_app.game.GameViewModel

@Composable
fun QuestionScreen(viewModel: GameViewModel) {
    val state by viewModel.uiState.collectAsState()
    val question = state.selectedPack?.questions?.getOrNull(state.questionIndex)?: return


    Column(modifier = Modifier.fillMaxSize().padding(24.dp))
    {
        Text(
            text = question.prompt,
            style = MaterialTheme.typography.titleLarge
        )
    }

        Spacer(modifier = Modifier.height(16.dp))

        question.options.forEach {
            option:String -> Button(onClick = {
                viewModel.submitAnswer(option)
        }, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp))
        {
            Text(option)
        }

        }
}
package com.wovenminds.abc_financial_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.wovenminds.abc_financial_app.game.GameViewModel

@Composable
fun LearnScreen(viewModel: GameViewModel)
{
    val state by viewModel.uiState.collectAsState()

    val item = viewModel.currentLearnItem ?: return

    Column(modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Text(text = item.letter,
            style = MaterialTheme.typography.displayLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text= item.word,
            style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = item.definition, style= MaterialTheme.typography.bodyMedium)
    }
}
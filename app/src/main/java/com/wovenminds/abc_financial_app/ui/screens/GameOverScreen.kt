package com.wovenminds.abc_financial_app.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import  androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GameOverScreen(score: Int,
             onRestart: () -> Unit,
             onExit: () -> Unit = {})
{
    Column(modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text="Game Over",
            style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Your Score: $score",
            style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onRestart,
            modifier = Modifier.fillMaxWidth())
        {
            Text("Play Again")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = onExit,
            modifier = Modifier.fillMaxWidth())
        {
            Text("Exit")
        }
    }
}
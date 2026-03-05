package com.wovenminds.abc_financial_app.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.wovenminds.abc_financial_app.game.model.GameMode
import com.wovenminds.abc_financial_app.game.GameViewModel



@Composable
fun GameHeader(
    score: Int,
    questionIndex: Int,
    totalQuestions: Int,
    onClose: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        //Left side: Score
        Text(
            text = "Score: $score",
            style = MaterialTheme.typography.titleMedium
        )

        //Center: Question Progress
        Text(
            text = "Q $questionIndex/$totalQuestions",
            style = MaterialTheme.typography.titleMedium
        )

        //Right side: Close
        TextButton(onClose)
        {
            Text("Close")
        }
    }
}

@Composable
fun GameScreen(viewModel: GameViewModel, onClose: () -> Unit) {
    val state by viewModel.uiState.collectAsState()

    if (state.isGameOver) {
         GameOverScreen(
            score = state.score,
            onRestart = {
                viewModel.resetGame()
            }
        )
    }
    else
    {
        when (state.mode) {
            GameMode.LEARN -> {
                LearnScreen(viewModel = viewModel)
            }

            GameMode.PRACTICE,
            GameMode.CHALLENGE -> {
                val question = viewModel.currentQuestion
                if (question != null) {
                    QuestionScreen(viewModel = viewModel)
                } else {
                    Text("No question available.")
                }
            }

            null -> {
                Text("Mode not selected")
            }
        }
    }

    Scaffold(
        topBar =
            {
                GameHeader(
                    score = state.score,
                    questionIndex = state.questionIndex,
                    totalQuestions = state.questionIndex + 10,
                    onClose = onClose

                    /*title = { Text("ABC Financial") },
                    actions = {
                        IconButton(onClick = onClose) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close Game"
                            )
                        }
                    }*/
                )
            })
    { padding ->
        Box(modifier = Modifier.padding(padding))
        {
            Text("Game Mode Coming Soon")
        }

    }
}
/*    modifier = TODO(),
    bottomBar = TODO(),
snackbarHost = TODO(),
floatingActionButton = TODO(),
floatingActionButtonPosition = TODO(),
containerColor = TODO(),
contentColor = TODO(),
contentWindowInsets = TODO(),
content = TODO(),
modifier = TODO(),
bottomBar = TODO(),
snackbarHost = TODO(),
floatingActionButton = TODO(),
floatingActionButtonPosition = TODO(),
containerColor = TODO(),
contentColor = TODO(),
contentWindowInsets = TODO(),
content = TODO()*/


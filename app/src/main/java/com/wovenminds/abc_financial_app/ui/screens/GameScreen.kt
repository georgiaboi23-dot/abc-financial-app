package com.wovenminds.abc_financial_app.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.wovenminds.abc_financial_app.data.model.GameMode
import com.wovenminds.abc_financial_app.ui.viewModel.GameViewModel



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
fun GameScreen(viewModel: GameViewModel,
               mode:GameMode,
               onClose: () -> Unit)
{
    val state by viewModel.uiState.collectAsState()




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
        Box(modifier = Modifier.padding(paddingValues = padding))
        {
            when (state.mode) {
                GameMode.LEARN -> {
                    LearnScreen(viewModel = viewModel)
                }

                GameMode.PRACTICE,
                GameMode.CHALLENGE -> {
                    QuestionScreen(viewModel = viewModel)

                }

                null -> {
                    Text("Game Mode Coming Soon!")
                }
            }

        }

    }
}



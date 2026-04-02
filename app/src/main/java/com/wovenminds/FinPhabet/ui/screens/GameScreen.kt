package com.wovenminds.FinPhabet.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.wovenminds.FinPhabet.data.model.GameMode
import com.wovenminds.FinPhabet.ui.viewModel.GameViewModel
import kotlinx.coroutines.delay


@Composable
fun GameHeader(
    score: Int,
    questionIndex: Int,
    totalQuestions: Int,
    gameMode: GameMode,
    timeLeft: Int,
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
        if (gameMode != GameMode.LEARN)

            Text(
                text = "Score: $score",
                style = MaterialTheme.typography.titleMedium
            )

        Text(
            text = when(gameMode)
            {
                GameMode.CHALLENGE -> "Time: $timeLeft"
                GameMode.PRACTICE -> "Practice"
                GameMode.LEARN -> "Learn"
            }
        )

        //Center: Question Progress
        if (gameMode != GameMode.LEARN)
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
               onClose: () -> Unit,
               onGameOver: () -> Unit)
{
    val state by viewModel.uiState.collectAsState()

    var timeLeft by remember { mutableStateOf(15) }

    LaunchedEffect(state.isGameOver) {
        if (state.isGameOver)
        {
            onGameOver()
        }
    }

    if(mode == GameMode.CHALLENGE)
    {
        LaunchedEffect(state.currentQuestion) {
            timeLeft = 15
            while(timeLeft > 0)
            {
                delay(1000L)
                timeLeft -= 1
            }
            if (timeLeft == 0)
            {
                viewModel.submitAnswer("")
            }
        }
    }


    Scaffold(
        topBar =
            {
                GameHeader(
                    score = state.score,
                    questionIndex = state.questionIndex,
                    totalQuestions = state.totalQuestion,
                    gameMode = mode,
                    timeLeft = timeLeft,
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



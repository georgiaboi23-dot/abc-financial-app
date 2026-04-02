package com.wovenminds.FinPhabet.ui.screens


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import  androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavController
import com.wovenminds.FinPhabet.ui.viewModel.GameViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameOverScreen(viewModel: GameViewModel,
                   navController: NavController) {
    val state by viewModel.uiState.collectAsState()
    val currentMode = state.mode
    var showBalloons by remember { mutableStateOf(true) }

    Scaffold(topBar = {
        TopAppBar(title = { Text("Game Over") })
    })
    { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            if (showBalloons)
            {
                BalloonsAnimation(onFinished = {
                    showBalloons = false
                })
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Final Score",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${state.score}/${state.questionIndex}",
                style = MaterialTheme.typography.displayMedium
            )
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    viewModel.resetGame()
                    viewModel.startGame(state.mode)
                    navController.navigate("game/$currentMode")
                    {
                        popUpTo("gameOver") {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Play Again")
            }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = {
                    viewModel.resetGame()
                    navController.navigate("packs")
                    {
                        popUpTo("gameOver")
                        {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text("Back to packs")
            }
        }
    }
}


@Composable
fun BalloonsAnimation(
    modifier: Modifier = Modifier,
    onFinished:() -> Unit
)
{
    val screenHeight = LocalConfiguration.current.screenHeightDp.toFloat()
    val screenWidth = LocalConfiguration.current.screenWidthDp.toFloat()
    val balloonsToDisplay = 28

    val balloons = remember {
        List(balloonsToDisplay){ index ->
            val baseX = (index.toFloat()/balloonsToDisplay) * screenWidth
            val randomOffset = Random.nextFloat() * 40f - 20f
            BalloonConfig(
                startX = baseX + randomOffset,
                delay = Random.nextLong(0,2000),
                drift = Random.nextFloat() * 120f - 60f,
                size = Random.nextFloat() * 20f + 24f
            )
        }
    }

    var finishedCount by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize())
    {
        balloons.forEach {
            config -> SingleBalloon(config = config,
                startY = screenHeight,
                onDone = {finishedCount++
                if (finishedCount == balloons.size)
                {onFinished()}})
        }
    }
}

@Composable
fun SingleBalloon(
    config: BalloonConfig,
    startY: Float,
    onDone:() -> Unit
){
    val offsetY = remember { Animatable(startY) }
    val offsetX = remember { Animatable(config.startX) }
    val color = remember { balloonColor() }
    val travelDistance = startY + 120f
    val speed = 120f
    val lengthDisplay = ((travelDistance/speed)* 1000).toInt()

    LaunchedEffect(Unit){
        delay(config.delay)
        launch {
            offsetY.animateTo(targetValue = -120f,
                animationSpec = tween(durationMillis = lengthDisplay,
                    easing = LinearEasing))
        }
        launch {
            offsetX.animateTo(
                targetValue = config.startX + config.drift,
                animationSpec = tween(durationMillis = lengthDisplay)
            )
        }
        delay(lengthDisplay.toLong())
        onDone()
    }
    BalloonShape(
        x = offsetX.value,
        y = offsetY.value,
        color = color,
        balloonSize = config.size
    )
}

fun balloonColor(): Color
{
    val colors = listOf(
        Color(0xFFE53935),
        Color(0xFF8E24AA),
        Color(0xFF1E88E5),
        Color(0xFF43A047),
        Color(0xFFFDD835),
        Color(0xFFFF7043)
    )
    return colors.random()
}

@Composable
fun BalloonShape(
    x:Float,
    y:Float,
    color: Color,
    balloonSize: Float
)
{
    Canvas(modifier = Modifier
        .size(balloonSize.dp)
        .offset(x.dp,y.dp))
    {
        val width = size.width
        val height = size.height

        drawOval(
            color=color,
            size = Size(width, height * 0.75f)
        )

        drawLine(
            color = Color.Gray,
            start = Offset(width/2, height * 0.75f),
            end = Offset(width/2, height),
            strokeWidth = 2f
        )
    }
}


data class BalloonConfig(
    val startX: Float,
    val delay: Long,
    val drift: Float,
    val size:Float
)
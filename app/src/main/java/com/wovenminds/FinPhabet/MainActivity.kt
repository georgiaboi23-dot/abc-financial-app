package com.wovenminds.FinPhabet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wovenminds.FinPhabet.ui.navigation.AppNavigation
import com.wovenminds.FinPhabet.ui.theme.AbcfinancialappTheme
import com.wovenminds.FinPhabet.ui.viewModel.GameViewModel
import com.wovenminds.FinPhabet.ui.viewModel.GameViewModelFactory

class MainActivity : ComponentActivity() {

    val viewModel: GameViewModel by viewModels {
        GameViewModelFactory(applicationContext)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AbcfinancialappTheme {
                AppNavigation(viewModel)

                }
            }
        }
    }


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AbcfinancialappTheme {
        Greeting("Android")
    }
}
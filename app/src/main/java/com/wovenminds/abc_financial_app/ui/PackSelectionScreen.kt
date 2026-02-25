package com.wovenminds.abc_financial_app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wovenminds.abc_financial_app.game.QuestionRepository

@Composable
fun PackSelectionScreen( onPackSelected:(com.wovenminds.abc_financial_app.game.QuestionPack) -> Unit)
{
    val packs= QuestionRepository.getAllPacks()
    Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text="Select a Pack")
        Spacer(modifier = Modifier.height(16.dp))

        packs.forEach{ pack ->  Button(onClick = {onPackSelected(pack)},
            modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp))
            {
                Text(text = pack.title)
            }
        }
    }
}
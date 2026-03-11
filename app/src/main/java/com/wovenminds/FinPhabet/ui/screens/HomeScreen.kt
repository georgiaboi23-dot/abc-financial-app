package com.wovenminds.FinPhabet.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen( onStart:() -> Unit)
{
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = "ABC Financial")
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onStart)
        {
            Text("Select a Path")
        }
    }
}
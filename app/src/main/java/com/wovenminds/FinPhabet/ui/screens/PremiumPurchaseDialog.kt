package com.wovenminds.FinPhabet.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.material3.*

@Composable
fun PremiumPurchaseDialog(onDismiss:() -> Unit,
                          onPurchaseComplete: () -> Unit)
{
    AlertDialog(onDismissRequest = onDismiss,
        title = {
            Text("Unlock Challenge")
        },
        text = {
            Text("Purchase to access challenge mode and extra content.")
        },
        confirmButton = {
            Button(onClick = onPurchaseComplete)
            {
                Text("Buy Challenge")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss)
            {
                Text("Cancel")
            }
        })
}
package com.wovenminds.abc_financial_app.ui.screens

import android.app.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.material3.*

@Composable
fun PremiumPurchaseDialog(onDismiss:() -> Unit,
                          onPurchaseComplete: () -> Unit)
{
    AlertDialog(onDismissRequest = onDismiss,
        title = {
            Text("Unlock Premium")
        },
        text = {
            Text("Purchase premium to access challenge mode and extra content.")
        },
        confirmButton = {
            Button(onClick = onPurchaseComplete)
            {
                Text("Buy Premium")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss)
            {
                Text("Cancel")
            }
        })
}
package com.example.autopulse_poe.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.theme.AutoPulseError
import com.example.autopulse_poe.ui.theme.AutoPulseWarning

@Composable
fun ClearDtcDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,

        containerColor = MaterialTheme.colorScheme.surface,

        titleContentColor = MaterialTheme.colorScheme.onSurface,

        textContentColor = MaterialTheme.colorScheme.onSurfaceVariant,

        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    tint = AutoPulseError
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "Reset ECU?",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Black
                )
            }
        },

        text = {
            Column {
                Text(
                    text = "This will clear all Diagnostic Trouble Codes and reset the Check Engine Light (MIL).",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Note: Ensure repairs are completed before clearing codes.",
                    color = AutoPulseWarning,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },

        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text(
                    text = "CLEAR ALL",
                    color = AutoPulseError,
                    fontWeight = FontWeight.Black
                )
            }
        },

        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = "CANCEL",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    )
}
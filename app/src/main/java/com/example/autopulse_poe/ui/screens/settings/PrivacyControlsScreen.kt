package com.example.autopulse_poe.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun PrivacyControlsScreen(onBack: () -> Unit) {
    var analyticsEnabled by remember { mutableStateOf(true) }
    var locationSharing by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        // Header
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.White)
            }
            Text(
                text = "Privacy Controls",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Column(modifier = Modifier.padding(20.dp)) {
            NeonCard(borderColor = NeonMagenta) {
                PrivacyOption("Anonymous Analytics", analyticsEnabled) { analyticsEnabled = it }
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.White.copy(alpha = 0.05f))
                PrivacyOption("Route History Sharing", locationSharing) { locationSharing = it }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "Data Management", color = NeonRed, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(16.dp))

            NeonCard(borderColor = NeonRed) {
                TextButton(onClick = { /* Clear History */ }, modifier = Modifier.fillMaxWidth()) {
                    Text("Clear Diagnostic History", color = NeonRed)
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), color = Color.White.copy(alpha = 0.05f))
                TextButton(onClick = { /* Delete Account */ }, modifier = Modifier.fillMaxWidth()) {
                    Text("Request Account Deletion", color = NeonRed)
                }
            }
        }
    }
}

@Composable
fun PrivacyOption(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Text(text = label, color = Color.White, modifier = Modifier.weight(1f))
        Switch(checked = checked, onCheckedChange = onCheckedChange, colors = SwitchDefaults.colors(checkedThumbColor = NeonMagenta))
    }
}

package com.example.autopulse_poe.ui.screens.hud

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
fun HudCustomizationScreen(onBack: () -> Unit) {
    var speedWarning by remember { mutableStateOf(true) }
    var rpmWarning by remember { mutableStateOf(false) }

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
                text = "HUD Settings",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = "Visual Options", color = NeonCyan, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(16.dp))

            NeonCard(borderColor = NeonCyan) {
                HudOptionRow("Mirror Image (for Windshield)", true)
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.White.copy(alpha = 0.05f))
                HudOptionRow("High Contrast Mode", true)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "Alerts & Warnings", color = NeonRed, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(16.dp))

            NeonCard(borderColor = NeonRed) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Speeding Alert", color = Color.White, modifier = Modifier.weight(1f))
                    Switch(checked = speedWarning, onCheckedChange = { speedWarning = it }, colors = SwitchDefaults.colors(checkedThumbColor = NeonRed))
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.White.copy(alpha = 0.05f))
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Shift Point Warning", color = Color.White, modifier = Modifier.weight(1f))
                    Switch(checked = rpmWarning, onCheckedChange = { rpmWarning = it }, colors = SwitchDefaults.colors(checkedThumbColor = NeonRed))
                }
            }
        }
    }
}

@Composable
fun HudOptionRow(label: String, active: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Text(text = label, color = Color.White, modifier = Modifier.weight(1f))
        Checkbox(checked = active, onCheckedChange = {}, colors = CheckboxDefaults.colors(checkedColor = NeonCyan))
    }
}

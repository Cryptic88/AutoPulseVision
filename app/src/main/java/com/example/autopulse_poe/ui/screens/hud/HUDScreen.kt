package com.example.autopulse_poe.ui.screens.hud

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.theme.DarkBackground
import com.example.autopulse_poe.ui.theme.NeonGreen

@Composable
fun HUDScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "85",
                fontSize = 180.sp,
                fontWeight = FontWeight.Black,
                color = NeonGreen
            )
            Text(
                text = "km/h",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = NeonGreen.copy(alpha = 0.7f)
            )
            
            Spacer(modifier = Modifier.height(48.dp))
            
            Row {
                HUDStatItem(label = "RPM", value = "2450")
                Spacer(modifier = Modifier.width(48.dp))
                HUDStatItem(label = "TEMP", value = "92°C")
            }
        }
    }
}

@Composable
fun HUDStatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, color = NeonGreen.copy(alpha = 0.5f), fontSize = 16.sp)
        Text(text = value, color = NeonGreen, fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}

package com.example.autopulse_poe.ui.screens.performance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Timer
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
fun AccelerationTimerScreen(onBack: () -> Unit) {
    var isRacing by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
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
                text = "Acceleration Timer",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (isRacing) "RACING" else "READY",
                color = if (isRacing) NeonGreen else NeonCyan,
                fontWeight = FontWeight.Black,
                fontSize = 24.sp
            )
            
            Text(
                text = "06.42",
                fontSize = 120.sp,
                fontWeight = FontWeight.Black,
                color = Color.White
            )
            
            Text(
                text = "SECONDS",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White.copy(alpha = 0.5f)
            )

            Spacer(modifier = Modifier.height(64.dp))

            // Split Times
            Row(modifier = Modifier.padding(horizontal = 32.dp)) {
                SplitItem("0-60", "2.81 s", Modifier.weight(1f))
                SplitItem("0-100", "6.42 s", Modifier.weight(1f))
                SplitItem("1/4 Mile", "14.5 s", Modifier.weight(1f))
            }
        }

        // Action Button
        Button(
            onClick = { isRacing = !isRacing },
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
                .height(64.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isRacing) NeonRed else NeonGreen,
                contentColor = Color.Black
            )
        ) {
            Text(
                text = if (isRacing) "STOP RACE" else "START RACE",
                fontWeight = FontWeight.Black,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun SplitItem(label: String, time: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, color = NeonCyan, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Text(text = time, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Black)
    }
}

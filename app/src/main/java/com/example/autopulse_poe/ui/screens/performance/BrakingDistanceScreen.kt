package com.example.autopulse_poe.ui.screens.performance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
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
fun BrakingDistanceScreen(onBack: () -> Unit) {
    var isMonitoring by remember { mutableStateOf(false) }

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
                text = "Braking Distance",
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
                text = if (isMonitoring) "BRAKING DETECTED" else "AWAITING DECELERATION",
                color = if (isMonitoring) NeonRed else NeonOrange,
                fontWeight = FontWeight.Black,
                fontSize = 18.sp
            )
            
            Text(
                text = "38.5",
                fontSize = 120.sp,
                fontWeight = FontWeight.Black,
                color = Color.White
            )
            
            Text(
                text = "METERS (100-0 km/h)",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = NeonCyan
            )

            Spacer(modifier = Modifier.height(48.dp))

            NeonCard(borderColor = NeonMagenta, modifier = Modifier.padding(horizontal = 40.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Warning, contentDescription = null, tint = NeonMagenta)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(text = "Peak Deceleration", color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp)
                        Text(text = "1.12 G", color = Color.White, fontWeight = FontWeight.Black, fontSize = 24.sp)
                    }
                }
            }
        }

        Button(
            onClick = { isMonitoring = !isMonitoring },
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
                .height(64.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isMonitoring) NeonRed else NeonCyan,
                contentColor = Color.Black
            )
        ) {
            Text(
                text = if (isMonitoring) "CANCEL TEST" else "ARM SENSORS",
                fontWeight = FontWeight.Black,
                fontSize = 18.sp
            )
        }
    }
}

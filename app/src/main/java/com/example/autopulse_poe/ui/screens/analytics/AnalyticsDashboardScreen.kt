package com.example.autopulse_poe.ui.screens.analytics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun AnalyticsDashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(
            text = "Advanced Analytics",
            fontSize = 28.sp,
            fontWeight = FontWeight.Black,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        NeonCard(borderColor = NeonPurple) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.ShowChart, contentDescription = null, tint = NeonPurple, modifier = Modifier.size(32.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Driving Patterns", color = Color.White, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Your average speed has increased by 12% during peak morning hours. Fuel efficiency is optimal at 55 mph.",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Seasonal Trends", color = NeonCyan, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        
        NeonCard(borderColor = NeonCyan) {
            Box(modifier = Modifier.fillMaxWidth().height(120.dp).background(Color.White.copy(alpha = 0.05f)), contentAlignment = Alignment.Center) {
                Text("Summer vs Winter Performance Chart", color = Color.White.copy(alpha = 0.3f))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Anomaly Detection", color = NeonRed, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(12.dp))
        
        NeonCard(borderColor = NeonRed) {
            Text(text = "Unusual Battery Drain Detected", color = NeonRed, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(
                text = "Resting voltage dropped below 11.8V three times this week.",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp
            )
        }
        
        Spacer(modifier = Modifier.height(40.dp))
    }
}

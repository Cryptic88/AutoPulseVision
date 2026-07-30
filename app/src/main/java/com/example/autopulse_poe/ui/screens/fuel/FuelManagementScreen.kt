package com.example.autopulse_poe.ui.screens.fuel

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun FuelManagementScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(
            text = "Fuel Management",
            fontSize = 28.sp,
            fontWeight = FontWeight.Black,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        NeonCard(borderColor = NeonGreen) {
            Text(text = "Average Consumption", color = Color.White, fontWeight = FontWeight.Bold)
            Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.padding(top = 8.dp)) {
                Text(text = "28.4", fontSize = 48.sp, fontWeight = FontWeight.Black, color = NeonGreen)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "MPG", fontSize = 24.sp, color = Color.White, modifier = Modifier.padding(bottom = 8.dp))
            }
            Text(text = "6% improvement from last month", color = NeonCyan, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Manual Cost Input
        Text(text = "Track Fuel Purchase", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        
        NeonCard(borderColor = NeonMagenta) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Enter Fuel Price", color = Color.White.copy(alpha = 0.5f), fontSize = 12.sp)
                    Text("$3.45 / Gal", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { /* Update Price */ },
                    colors = ButtonDefaults.buttonColors(containerColor = NeonMagenta)
                ) {
                    Text("UPDATE", fontWeight = FontWeight.Black)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Consumption Trend", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))

        // Simple Chart Placeholder
        NeonCard(borderColor = NeonPurple, modifier = Modifier.height(200.dp)) {
            Box(modifier = Modifier.fillMaxSize()) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val path = Path().apply {
                        moveTo(0f, size.height * 0.8f)
                        lineTo(size.width * 0.2f, size.height * 0.6f)
                        lineTo(size.width * 0.4f, size.height * 0.7f)
                        lineTo(size.width * 0.6f, size.height * 0.4f)
                        lineTo(size.width * 0.8f, size.height * 0.5f)
                        lineTo(size.width, size.height * 0.2f)
                    }
                    drawPath(
                        path = path,
                        color = NeonCyan,
                        style = Stroke(width = 3.dp.toPx())
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Recent Fill-ups", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))

        FuelLogItem("July 25, 2026", "12.4 Gal", "$45.20", "28.1 MPG")
        FuelLogItem("July 18, 2026", "10.8 Gal", "$39.50", "29.4 MPG")
        FuelLogItem("July 10, 2026", "11.2 Gal", "$41.00", "27.8 MPG")
        
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun FuelLogItem(date: String, amount: String, cost: String, mpg: String) {
    NeonCard(borderColor = Color.White.copy(alpha = 0.1f), modifier = Modifier.padding(bottom = 12.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text(text = date, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(text = "$amount • $cost", color = Color.White.copy(alpha = 0.5f), fontSize = 12.sp)
            }
            Text(text = mpg, color = NeonGreen, fontWeight = FontWeight.Black, fontSize = 16.sp)
        }
    }
}

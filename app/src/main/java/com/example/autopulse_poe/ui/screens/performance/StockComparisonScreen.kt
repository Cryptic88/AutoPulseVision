package com.example.autopulse_poe.ui.screens.performance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.TrendingUp
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
fun StockComparisonScreen(onBack: () -> Unit) {
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
                text = "Stock Comparison",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            Text(text = "Vehicle: 2024 Tesla Model S Plaid", color = NeonCyan, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(24.dp))

            ComparisonCard("Max Horsepower", "1,020 HP", "1,035 HP", "+1.5%", NeonMagenta)
            Spacer(modifier = Modifier.height(16.dp))
            ComparisonCard("Max Torque", "1,420 Nm", "1,445 Nm", "+1.1%", NeonCyan)
            Spacer(modifier = Modifier.height(16.dp))
            ComparisonCard("0-100 km/h", "2.10 s", "1.98 s", "-9.5%", NeonGreen)

            Spacer(modifier = Modifier.height(32.dp))

            NeonCard(borderColor = NeonOrange) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.TrendingUp, contentDescription = null, tint = NeonOrange)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Tuning Insights", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Your vehicle is currently performing slightly above stock specifications in power delivery. Torque gains are consistent with a Stage 1 software optimization.",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 14.sp
                )
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun ComparisonCard(label: String, stock: String, current: String, gain: String, color: Color) {
    NeonCard(borderColor = color.copy(alpha = 0.3f)) {
        Column {
            Text(text = label, color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp)
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "FACTORY", color = Color.White.copy(alpha = 0.4f), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    Text(text = stock, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                Icon(Icons.Default.CompareArrows, contentDescription = null, tint = color.copy(alpha = 0.5f))
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = "CURRENT", color = color.copy(alpha = 0.7f), fontSize = 10.sp, fontWeight = FontWeight.Black)
                    Text(text = current, color = color, fontSize = 24.sp, fontWeight = FontWeight.Black)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                color = if (gain.startsWith("+") || label.contains("0-100")) NeonGreen.copy(alpha = 0.1f) else NeonRed.copy(alpha = 0.1f),
                shape = androidx.compose.foundation.shape.CircleShape,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = gain,
                    color = if (gain.startsWith("+") || label.contains("0-100")) NeonGreen else NeonRed,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                )
            }
        }
    }
}

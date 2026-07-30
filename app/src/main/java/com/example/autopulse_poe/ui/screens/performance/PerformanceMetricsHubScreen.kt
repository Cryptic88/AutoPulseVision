package com.example.autopulse_poe.ui.screens.performance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ElectricCar
import androidx.compose.material.icons.filled.Speed
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
fun PerformanceMetricsHubScreen(onBack: () -> Unit) {
    var isDynoMode by remember { mutableStateOf(false) }

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
                text = "Performance Metrics",
                fontSize = 22.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            // Calculated Horsepower
            NeonCard(borderColor = NeonMagenta) {
                Text(text = "ESTIMATED POWER", color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(text = "285", fontSize = 64.sp, fontWeight = FontWeight.Black, color = NeonMagenta)
                    Text(text = "HP", fontSize = 24.sp, color = Color.White, modifier = Modifier.padding(bottom = 12.dp, start = 8.dp))
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(Icons.Default.ElectricCar, contentDescription = null, tint = NeonMagenta.copy(alpha = 0.3f), modifier = Modifier.size(48.dp))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Calculated Torque
            NeonCard(borderColor = NeonCyan) {
                Text(text = "ESTIMATED TORQUE", color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(text = "420", fontSize = 64.sp, fontWeight = FontWeight.Black, color = NeonCyan)
                    Text(text = "Nm", fontSize = 24.sp, color = Color.White, modifier = Modifier.padding(bottom = 12.dp, start = 8.dp))
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(Icons.Default.Speed, contentDescription = null, tint = NeonCyan.copy(alpha = 0.3f), modifier = Modifier.size(48.dp))
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Dynamometer Mode Toggle
            NeonCard(borderColor = if (isDynoMode) NeonGreen else Color.Gray) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "DYNAMOMETER MODE", color = if (isDynoMode) NeonGreen else Color.White, fontWeight = FontWeight.Black)
                        Text(text = "Enable live power/torque graphing", color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp)
                    }
                    Switch(
                        checked = isDynoMode,
                        onCheckedChange = { isDynoMode = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = NeonGreen)
                    )
                }

                if (isDynoMode) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(Color.Black.copy(alpha = 0.3f), MaterialTheme.shapes.medium),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Live Graph Rendering...", color = NeonGreen.copy(alpha = 0.5f))
                        // Real implementation would have a Canvas drawing lines here
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

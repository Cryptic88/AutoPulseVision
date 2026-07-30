package com.example.autopulse_poe.ui.screens.performance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonButton
import com.example.autopulse_poe.ui.theme.*

@Composable
fun PerformanceMenuScreen(
    onNavigateToMetrics: () -> Unit,
    onNavigateToAcceleration: () -> Unit,
    onNavigateToBraking: () -> Unit,
    onNavigateToCustomPids: () -> Unit,
    onNavigateToStock: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(
            text = "Performance Suite",
            fontSize = 28.sp,
            fontWeight = FontWeight.Black,
            color = Color.White,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        PerformanceMenuButton(
            title = "Performance Metrics Hub",
            subtitle = "Live Horsepower & Torque Estimator",
            icon = Icons.Default.BarChart,
            gradient = listOf(NeonMagenta, NeonPurple),
            onClick = onNavigateToMetrics
        )

        Spacer(modifier = Modifier.height(20.dp))

        PerformanceMenuButton(
            title = "Acceleration Timer",
            subtitle = "0-100 km/h & 1/4 Mile Racing",
            icon = Icons.Default.Timer,
            gradient = listOf(NeonCyan, NeonBlue),
            onClick = onNavigateToAcceleration
        )

        Spacer(modifier = Modifier.height(20.dp))

        PerformanceMenuButton(
            title = "Braking Distance",
            subtitle = "Precision Stopping Power Measurement",
            icon = Icons.Default.Warning,
            gradient = listOf(NeonOrange, Color(0xFFFF4500)),
            onClick = onNavigateToBraking
        )

        Spacer(modifier = Modifier.height(20.dp))

        PerformanceMenuButton(
            title = "Custom PIDs Setup",
            subtitle = "Advanced formula definition for tuners",
            icon = Icons.Default.SettingsInputComponent,
            gradient = listOf(Color.Gray, Color.DarkGray),
            onClick = onNavigateToCustomPids
        )

        Spacer(modifier = Modifier.height(20.dp))

        PerformanceMenuButton(
            title = "Comparison to Stock",
            subtitle = "Current sensor data vs. Factory specs",
            icon = Icons.Default.CompareArrows,
            gradient = listOf(NeonGreen, Color(0xFF006400)),
            onClick = onNavigateToStock
        )
        
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun PerformanceMenuButton(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    gradient: List<Color>,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = androidx.compose.ui.graphics.Brush.linearGradient(gradient),
                    shape = MaterialTheme.shapes.large
                )
                .padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column {
                    Text(
                        text = title,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        text = subtitle,
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

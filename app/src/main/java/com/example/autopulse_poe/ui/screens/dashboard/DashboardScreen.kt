package com.example.autopulse_poe.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.autopulse_poe.ui.components.*
import com.example.autopulse_poe.ui.theme.*

@Composable
fun DashboardScreen(
    onNavigateToHUD: () -> Unit = {},
    onNavigateToAi: () -> Unit = {},
    onNavigateToDiagnostics: () -> Unit = {},
    onNavigateToTrips: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onNavigateToAchievements: () -> Unit = {},
    onNavigateToPerformance: () -> Unit = {},
    onNavigateToFuel: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.DirectionsCar,
                    contentDescription = null,
                    tint = NeonCyan,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "AutoPulse",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            }
            Row {
                IconButton(onClick = onNavigateToHUD) {
                    Icon(Icons.Default.Visibility, contentDescription = "HUD", tint = NeonGreen)
                }
                IconButton(onClick = onNavigateToSettings) {
                    Icon(Icons.Default.Settings, contentDescription = null, tint = Color.LightGray)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Welcome
        Row {
            Text(text = "Welcome ", color = NeonCyan, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = "Back, Alex", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Quick Actions Grid
        Row(modifier = Modifier.fillMaxWidth()) {
            NeonButton(
                text = "Start Trip",
                icon = Icons.Default.Navigation,
                gradientColors = listOf(Color(0xFF00C8FF), Color(0xFF0078FF)),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            NeonButton(
                text = "Diagnostics",
                icon = Icons.Default.Build,
                gradientColors = listOf(Color(0xFFFFB300), Color(0xFFFF6D00)),
                modifier = Modifier.weight(1f),
                onClick = onNavigateToDiagnostics
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            NeonButton(
                text = "Performance",
                icon = Icons.Default.Speed,
                gradientColors = listOf(NeonMagenta, NeonPurple),
                modifier = Modifier.weight(1f),
                onClick = onNavigateToPerformance
            )
            Spacer(modifier = Modifier.width(16.dp))
            NeonButton(
                text = "Trip History",
                icon = Icons.Default.History,
                gradientColors = listOf(Color(0xFF8E24AA), Color(0xFF5E35B1)),
                modifier = Modifier.weight(1f),
                onClick = onNavigateToTrips
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Live Trip Summary
        LiveTripSummaryCard(duration = "00:32", distance = "12.5 mi", avgSpeed = "45 mph")

        Spacer(modifier = Modifier.height(32.dp))

        // AI Mechanic Assistant Preview
        NeonCard(borderColor = NeonPurple) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = NeonPurple, modifier = Modifier.size(32.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "AI Mechanic", color = Color.White, fontWeight = FontWeight.Bold)
                    Text(text = "Ask me about your vehicle", color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp)
                }
                IconButton(onClick = onNavigateToAi) {
                    Icon(Icons.Default.ChevronRight, contentDescription = null, tint = NeonPurple)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Fuel Efficiency Section
        NeonCard(
            borderColor = NeonCyan,
            modifier = Modifier.clickable(onClick = onNavigateToFuel)
        ) {
            Text(text = "Fuel Efficiency", color = Color.White, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Simplified Gauge placeholder or custom drawn one
                Gauge(
                    value = 32f,
                    maxValue = 100f,
                    label = "",
                    unit = "",
                    modifier = Modifier.size(100.dp),
                    color = NeonGreen
                )
                Spacer(modifier = Modifier.width(24.dp))
                Column {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(text = "32", fontSize = 48.sp, fontWeight = FontWeight.Black, color = NeonGreen)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "MPG", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                    Text(text = "Fuel Level: 78%", color = NeonCyan)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Maintenance & Score
        Row(modifier = Modifier.fillMaxWidth()) {
            NeonCard(
                modifier = Modifier
                    .weight(1.2f)
                    .clickable(onClick = onNavigateToDiagnostics), 
                borderColor = NeonRed
            ) {
                Text(text = "Maintenance Alert", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Build, contentDescription = null, tint = NeonRed, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Oil Change Needed", color = Color.White, fontSize = 12.sp)
                }
                Text(text = "Overdue by 250 mi", color = NeonRed, fontSize = 11.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            NeonCard(
                modifier = Modifier
                    .weight(1f)
                    .clickable(onClick = onNavigateToAchievements), 
                borderColor = NeonMagenta
            ) {
                Text(text = "Safety Score", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                CircularScoreIndicator(score = 88, label = "Good", modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }
        
        Spacer(modifier = Modifier.height(40.dp))
    }
}

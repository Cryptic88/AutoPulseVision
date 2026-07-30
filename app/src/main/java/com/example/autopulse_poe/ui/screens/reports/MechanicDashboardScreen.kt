package com.example.autopulse_poe.ui.screens.reports

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.SettingsInputComponent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun MechanicDashboardScreen(onBack: () -> Unit) {
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
                text = "Mechanic Console",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
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
            MechanicSection("Diagnostic Summary", Icons.Default.Assignment, NeonCyan) {
                Text("Stored DTCs: 1 (Critical)", color = NeonRed, fontSize = 14.sp)
                Text("Pending DTCs: 2", color = NeonOrange, fontSize = 14.sp)
                Text("Permanent DTCs: 0", color = Color.White.copy(alpha = 0.6f), fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            MechanicSection("Live PID Stream", Icons.Default.SettingsInputComponent, NeonPurple) {
                MechanicStatRow("Calc Engine Load", "74.5 %")
                MechanicStatRow("Fuel Pressure", "345 kPa")
                MechanicStatRow("Timing Advance", "14.5 deg")
                MechanicStatRow("Intake Air Temp", "32 °C")
            }

            Spacer(modifier = Modifier.height(24.dp))

            MechanicSection("Module Info", Icons.Default.Memory, NeonMagenta) {
                Text("ECU Protocol: ISO 15765-4", color = Color.White.copy(alpha = 0.8f), fontSize = 13.sp)
                Text("CAN ID: 11-bit 500k", color = Color.White.copy(alpha = 0.8f), fontSize = 13.sp)
                Text("Adapter Latency: 42ms", color = NeonGreen, fontSize = 13.sp)
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun MechanicSection(title: String, icon: ImageVector, color: Color, content: @Composable ColumnScope.() -> Unit) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(12.dp))
        NeonCard(borderColor = color.copy(alpha = 0.4f)) {
            content()
        }
    }
}

@Composable
fun MechanicStatRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = label, color = Color.White.copy(alpha = 0.6f), fontSize = 13.sp)
        Text(text = value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
    }
}

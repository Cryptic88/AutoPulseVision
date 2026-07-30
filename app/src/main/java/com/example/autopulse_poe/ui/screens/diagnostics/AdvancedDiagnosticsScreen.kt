package com.example.autopulse_poe.ui.screens.diagnostics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun AdvancedDiagnosticsScreen(onBack: () -> Unit) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Emissions", "O2 Sensors", "Monitoring")

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
                text = "Advanced Tests",
                fontSize = 22.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color.Transparent,
            contentColor = NeonCyan,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = NeonCyan
                )
            },
            divider = {}
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 13.sp
                        )
                    }
                )
            }
        }

        Box(modifier = Modifier.weight(1f)) {
            when (selectedTab) {
                0 -> EmissionsView()
                1 -> OxygenSensorsView()
                2 -> MonitorView()
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EmissionsView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(text = "Emissions Readiness (Mode 01)", color = NeonCyan, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))
        
        NeonCard(borderColor = NeonCyan) {
            val monitors = listOf(
                "Misfire" to true, "Fuel System" to true, "Components" to true,
                "Catalyst" to true, "Evap System" to false, "Oxygen Sensor" to true,
                "O2 Heater" to true, "EGR System" to true
            )
            
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                monitors.forEach { (name, ready) ->
                    MonitorItem(name, ready)
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Status: Not Ready for Inspection",
            color = NeonRed,
            fontWeight = FontWeight.Black,
            fontSize = 14.sp
        )
    }
}

@Composable
fun OxygenSensorsView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(text = "Oxygen Sensor Tests (Mode 05)", color = NeonMagenta, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))

        NeonCard(borderColor = NeonMagenta) {
            Text(text = "Bank 1 Sensor 1", color = Color.White, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Voltage: 0.450V - 0.900V (Switching)",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            LinearProgressIndicator(
                progress = { 0.65f },
                modifier = Modifier.fillMaxWidth().height(4.dp).clip(CircleShape),
                color = NeonMagenta,
                trackColor = Color.White.copy(alpha = 0.1f)
            )
        }
    }
}

@Composable
fun MonitorView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(text = "On-Board Monitoring (Mode 06)", color = NeonPurple, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))
        
        Mode06Item("Catalyst Monitor Bank 1", "0.45", "0.60", true)
        Mode06Item("O2 Sensor Bank 1 Sensor 1", "1.20", "1.50", true)
        Mode06Item("Evaporative System", "0.08", "0.05", false)
    }
}

@Composable
fun MonitorItem(name: String, ready: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = if (ready) Icons.Default.CheckCircle else Icons.Default.Cancel,
            contentDescription = null,
            tint = if (ready) NeonGreen else NeonRed,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = name, color = Color.White, fontSize = 13.sp)
    }
}

@Composable
fun Mode06Item(title: String, value: String, limit: String, passed: Boolean) {
    NeonCard(borderColor = if (passed) NeonPurple.copy(alpha = 0.3f) else NeonRed.copy(alpha = 0.5f), modifier = Modifier.padding(bottom = 12.dp)) {
        Column {
            Text(text = title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text(text = "Value", color = Color.White.copy(alpha = 0.5f), fontSize = 11.sp)
                    Text(text = value, color = Color.White, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = "Limit", color = Color.White.copy(alpha = 0.5f), fontSize = 11.sp)
                    Text(text = limit, color = Color.White, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = "Result", color = Color.White.copy(alpha = 0.5f), fontSize = 11.sp)
                    Text(
                        text = if (passed) "PASS" else "FAIL",
                        color = if (passed) NeonGreen else NeonRed,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }
    }
}

package com.example.autopulse_poe.ui.screens.diagnostics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.BluetoothSearching
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.ClearDtcDialog
import com.example.autopulse_poe.ui.components.NeonButton
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun DiagnosticsMainScreen(
    onNavigateToDtcList: () -> Unit,
    onNavigateToAdvanced: () -> Unit,
    onNavigateToFreezeFrame: () -> Unit,
    onNavigateToVehicleInfo: () -> Unit,
    onNavigateToHistory: () -> Unit,
    onClearCodes: () -> Unit = {}
) {
    var showClearDialog by remember { mutableStateOf(false) }

    if (showClearDialog) {
        ClearDtcDialog(
            onConfirm = { 
                showClearDialog = false
                onClearCodes()
            },
            onDismiss = { showClearDialog = false }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(
            text = "Diagnostics Hub",
            fontSize = 28.sp,
            fontWeight = FontWeight.Black,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        NeonCard(borderColor = NeonCyan) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(NeonCyan.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.AutoMirrored.Filled.BluetoothSearching, contentDescription = null, tint = NeonCyan)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = "ADAPTER CONNECTED", color = NeonCyan, fontWeight = FontWeight.Black, fontSize = 12.sp)
                    Text(text = "OBDLink MX+ • 42ms Latency", color = Color.White, fontSize = 14.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            FaultSummaryItem("Stored", "1", NeonRed, Modifier.weight(1f))
            FaultSummaryItem("Pending", "2", NeonOrange, Modifier.weight(1f))
            FaultSummaryItem("Permanent", "0", Color.Gray, Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(32.dp))

        NeonButton(
            text = "Full Scan ECU",
            icon = Icons.Default.Search,
            gradientColors = listOf(NeonCyan, NeonBlue),
            onClick = onNavigateToDtcList
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        NeonButton(
            text = "Clear All Codes",
            icon = Icons.Default.DeleteForever,
            gradientColors = listOf(NeonRed, Color(0xFFB71C1C)),
            onClick = { showClearDialog = true }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Technical Data", color = Color.White.copy(alpha = 0.5f), fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            SecondaryActionCard("Freeze Frame", Icons.Default.CameraAlt, NeonMagenta, Modifier.weight(1f), onNavigateToFreezeFrame)
            Spacer(modifier = Modifier.width(12.dp))
            SecondaryActionCard("Advanced Tests", Icons.Default.SettingsInputComponent, NeonPurple, Modifier.weight(1f), onNavigateToAdvanced)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            SecondaryActionCard("Vehicle Info", Icons.Default.Info, NeonCyan, Modifier.weight(1f), onNavigateToVehicleInfo)
            Spacer(modifier = Modifier.width(12.dp))
            SecondaryActionCard("History Log", Icons.Default.History, NeonOrange, Modifier.weight(1f), onNavigateToHistory)
        }
        
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun FaultSummaryItem(label: String, count: String, color: Color, modifier: Modifier = Modifier) {
    NeonCard(borderColor = color.copy(alpha = 0.3f), modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Text(text = label, color = Color.White.copy(alpha = 0.6f), fontSize = 10.sp, fontWeight = FontWeight.Bold)
            Text(text = count, color = color, fontSize = 24.sp, fontWeight = FontWeight.Black)
        }
    }
}

@Composable
fun SecondaryActionCard(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color, modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier.height(80.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(0.dp)
    ) {
        NeonCard(borderColor = color.copy(alpha = 0.2f), modifier = Modifier.fillMaxSize()) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize()) {
                Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = label, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

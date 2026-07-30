package com.example.autopulse_poe.ui.screens.diagnostics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
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
fun VehicleInfoScreen(onBack: () -> Unit) {
    val infoItems = listOf(
        "VIN" to "1FTFX1EF5PKXXXXXX",
        "Calibration ID" to "VC3A-12A650-AA",
        "ECU Software" to "SW-92.01.45",
        "OBD Protocol" to "ISO 15765-4 (CAN 11/500)",
        "Manufacturer" to "Ford Motor Company",
        "ECU Voltage" to "14.2V"
    )

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
                text = "Vehicle Information",
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
            Text(
                text = "Data retrieved directly from the ECU via Mode 09.",
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            NeonCard(borderColor = NeonMagenta) {
                infoItems.forEachIndexed { index, (label, value) ->
                    InfoItemRow(label, value)
                    if (index < infoItems.size - 1) {
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 12.dp),
                            color = Color.White.copy(alpha = 0.05f)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            NeonCard(borderColor = NeonOrange) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Info, contentDescription = null, tint = NeonOrange)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Adapter Information", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Device: OBDLink MX+", color = Color.White.copy(alpha = 0.7f), fontSize = 13.sp)
                Text(text = "Firmware: v5.6.1", color = Color.White.copy(alpha = 0.7f), fontSize = 13.sp)
                Text(text = "Connection: Bluetooth LE", color = Color.White.copy(alpha = 0.7f), fontSize = 13.sp)
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun InfoItemRow(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, color = Color.White.copy(alpha = 0.5f), fontSize = 12.sp)
        Text(text = value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}

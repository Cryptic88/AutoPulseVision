package com.example.autopulse_poe.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.Refresh
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
fun BluetoothSettingsScreen(onBack: () -> Unit) {
    var isScanning by remember { mutableStateOf(false) }
    val devices = listOf(
        OBDDevice("OBDLink MX+", "AA:BB:CC:DD:EE:FF", true),
        OBDDevice("Vgate iCar Pro", "11:22:33:44:55:66", false),
        OBDDevice("ELM327 Interface", "77:88:99:00:11:22", false)
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
                text = "OBD-II Adapter",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { isScanning = !isScanning }) {
                Icon(Icons.Default.Refresh, contentDescription = "Scan", tint = NeonCyan)
            }
        }

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            if (isScanning) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth().height(2.dp),
                    color = NeonCyan,
                    trackColor = Color.Transparent
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            Text(text = "Paired Device", color = NeonCyan, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(12.dp))
            
            NeonCard(borderColor = NeonCyan) {
                DeviceItem(devices[0], true)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "Available Devices", color = Color.White.copy(alpha = 0.5f), fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(devices.drop(1)) { device ->
                    NeonCard(borderColor = Color.White.copy(alpha = 0.05f)) {
                        DeviceItem(device, false)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            NeonCard(borderColor = NeonPurple) {
                Text(text = "Adapter Diagnostics", color = Color.White, fontWeight = FontWeight.Bold)
                Text(text = "Firmware: v5.6.1 • ELM327 v2.2 compatible", color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp)
                Button(
                    onClick = { /* Run Test */ },
                    modifier = Modifier.padding(top = 12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = NeonPurple)
                ) {
                    Text("Run Interface Test")
                }
            }
        }
    }
}

data class OBDDevice(val name: String, val address: String, val isConnected: Boolean)

@Composable
fun DeviceItem(device: OBDDevice, isConnected: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Icon(Icons.Default.Bluetooth, contentDescription = null, tint = if (isConnected) NeonCyan else Color.White.copy(alpha = 0.3f))
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = device.name, color = Color.White, fontWeight = FontWeight.Bold)
            Text(text = device.address, color = Color.White.copy(alpha = 0.5f), fontSize = 11.sp)
        }
        if (isConnected) {
            Text(text = "CONNECTED", color = NeonGreen, fontSize = 10.sp, fontWeight = FontWeight.Black)
        }
    }
}

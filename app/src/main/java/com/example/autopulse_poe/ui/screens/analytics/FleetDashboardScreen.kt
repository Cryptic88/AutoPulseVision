package com.example.autopulse_poe.ui.screens.analytics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
fun FleetDashboardScreen() {
    val vehicles = listOf(
        FleetVehicle("Tesla Model S", "EV-942-CA", 94, NeonCyan),
        FleetVehicle("Ford F-150", "TRK-021-TX", 82, NeonOrange),
        FleetVehicle("BMW M3", "DRV-155-DE", 88, NeonMagenta)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(20.dp)
    ) {
        Text(
            text = "Fleet Dashboard",
            fontSize = 28.sp,
            fontWeight = FontWeight.Black,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(vehicles) { vehicle ->
                FleetCard(vehicle)
            }
        }
    }
}

data class FleetVehicle(val name: String, val plate: String, val health: Int, val color: Color)

@Composable
fun FleetCard(vehicle: FleetVehicle) {
    NeonCard(borderColor = vehicle.color.copy(alpha = 0.3f)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.size(48.dp).clip(CircleShape).background(vehicle.color.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.DirectionsCar, contentDescription = null, tint = vehicle.color)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = vehicle.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = vehicle.plate, color = Color.White.copy(alpha = 0.5f), fontSize = 12.sp)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(text = "${vehicle.health}%", color = vehicle.color, fontSize = 20.sp, fontWeight = FontWeight.Black)
                Text(text = "Health", color = Color.White.copy(alpha = 0.4f), fontSize = 10.sp)
            }
        }
    }
}

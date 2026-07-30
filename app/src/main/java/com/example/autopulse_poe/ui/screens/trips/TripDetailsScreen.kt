package com.example.autopulse_poe.ui.screens.trips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun TripDetailsScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
    ) {
        // Map Placeholder
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(Color.White.copy(alpha = 0.05f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Map, contentDescription = null, tint = NeonCyan.copy(alpha = 0.3f), modifier = Modifier.size(64.dp))
            Text(text = "Map Visualization Placeholder", color = NeonCyan.copy(alpha = 0.5f))
            
            // Back Button
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(DarkSurface.copy(alpha = 0.8f))
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.White)
            }
        }

        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "Downtown Commute",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = Color.White
            )
            Text(text = "July 28, 2026 • 08:30 AM", color = Color.White.copy(alpha = 0.5f), fontSize = 14.sp)

            Spacer(modifier = Modifier.height(24.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                TripDetailStat(label = "Distance", value = "12.5 mi", icon = Icons.Default.Timeline, color = NeonCyan, modifier = Modifier.weight(1f))
                TripDetailStat(label = "Duration", value = "18 min", icon = Icons.Default.Timer, color = NeonPurple, modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                TripDetailStat(label = "Avg Speed", value = "42 mph", icon = Icons.Default.Speed, color = NeonMagenta, modifier = Modifier.weight(1f))
                TripDetailStat(label = "Efficiency", value = "32 MPG", icon = Icons.Default.LocalGasStation, color = NeonOrange, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "Fuel & Cost", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(12.dp))
            
            NeonCard(borderColor = NeonGreen) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text(text = "Estimated Trip Cost", color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp)
                        Text(text = "$4.82", color = NeonGreen, fontSize = 28.sp, fontWeight = FontWeight.Black)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(text = "Efficiency", color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp)
                        Text(text = "32 MPG", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "Driving Behavior", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(12.dp))
            
            NeonCard(borderColor = NeonMagenta) {
                BehaviorItem("Hard Braking", "2 detected", Icons.Default.Warning, NeonRed)
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.White.copy(alpha = 0.1f))
                BehaviorItem("Rapid Acceleration", "0 detected", Icons.Default.CheckCircle, NeonGreen)
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.White.copy(alpha = 0.1f))
                BehaviorItem("Cornering", "Optimal", Icons.Default.CheckCircle, NeonGreen)
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun TripDetailStat(label: String, value: String, icon: ImageVector, color: Color, modifier: Modifier = Modifier) {
    NeonCard(borderColor = color.copy(alpha = 0.3f), modifier = modifier.padding(horizontal = 4.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = label, color = Color.White.copy(alpha = 0.5f), fontSize = 10.sp)
                Text(text = value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun BehaviorItem(label: String, value: String, icon: ImageVector, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = label, color = Color.White, fontSize = 14.sp, modifier = Modifier.weight(1f))
        Text(text = value, color = color, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}

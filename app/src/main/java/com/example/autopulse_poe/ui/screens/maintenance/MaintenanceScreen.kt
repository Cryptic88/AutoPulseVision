package com.example.autopulse_poe.ui.screens.maintenance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.History
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
fun MaintenanceScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
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
                text = "Maintenance",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            // Priority Alerts
            Text(text = "Priority Alerts", color = NeonRed, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))
            
            MaintenanceAlertItem(
                title = "Oil Change Required",
                subtitle = "Overdue by 250 miles",
                icon = Icons.Default.NotificationsActive,
                color = NeonRed
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Upcoming Service
            Text(text = "Upcoming Service", color = NeonCyan, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))
            
            MaintenanceServiceItem(
                title = "Tyre Rotation",
                dueIn = "In 1,200 miles",
                progress = 0.8f,
                icon = Icons.Default.Build,
                color = NeonCyan
            )
            Spacer(modifier = Modifier.height(12.dp))
            MaintenanceServiceItem(
                title = "Brake Fluid Flush",
                dueIn = "In 3 months",
                progress = 0.4f,
                icon = Icons.Default.Build,
                color = NeonPurple
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Service History
            Text(text = "Service History", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))
            
            NeonCard(borderColor = Color.White.copy(alpha = 0.1f)) {
                HistoryItem("Brake Pads Replaced", "May 12, 2026 • 42,000 mi")
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.White.copy(alpha = 0.05f))
                HistoryItem("Annual Inspection", "Jan 10, 2026 • 38,500 mi")
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun MaintenanceAlertItem(title: String, subtitle: String, icon: ImageVector, color: Color) {
    NeonCard(borderColor = color) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.size(40.dp).clip(CircleShape).background(color.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = color)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = subtitle, color = color, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun MaintenanceServiceItem(title: String, dueIn: String, progress: Float, icon: ImageVector, color: Color) {
    NeonCard(borderColor = color.copy(alpha = 0.3f)) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Text(text = dueIn, color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth().height(6.dp).clip(CircleShape),
                color = color,
                trackColor = Color.White.copy(alpha = 0.1f)
            )
        }
    }
}

@Composable
fun HistoryItem(title: String, date: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Default.History, contentDescription = null, tint = Color.White.copy(alpha = 0.4f), modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = title, color = Color.White, fontSize = 14.sp)
            Text(text = date, color = Color.White.copy(alpha = 0.4f), fontSize = 11.sp)
        }
    }
}

package com.example.autopulse_poe.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.theme.*

@Composable
fun PermissionsScreen(onFinish: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Essential Permissions",
            fontSize = 32.sp,
            fontWeight = FontWeight.Black,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "To provide accurate diagnostics and trip tracking, AutoPulse requires the following access:",
            color = Color.White.copy(alpha = 0.6f),
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(48.dp))

        PermissionItem(
            icon = Icons.Default.Bluetooth,
            title = "Bluetooth & Nearby Devices",
            desc = "Required to connect to your OBD-II adapter.",
            color = NeonCyan
        )
        
        Spacer(modifier = Modifier.height(24.dp))

        PermissionItem(
            icon = Icons.Default.LocationOn,
            title = "Location Access",
            desc = "Used for mapping your trips and calculating fuel efficiency.",
            color = NeonMagenta
        )

        Spacer(modifier = Modifier.height(24.dp))

        PermissionItem(
            icon = Icons.Default.Notifications,
            title = "Notifications",
            desc = "Receive critical maintenance alerts and diagnostic warnings.",
            color = NeonOrange
        )

        Spacer(modifier = Modifier.height(64.dp))

        Button(
            onClick = onFinish,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = NeonCyan)
        ) {
            Text("Grant All & Continue", fontWeight = FontWeight.Bold, color = DarkBackground)
        }
        
        TextButton(onClick = onFinish, modifier = Modifier.padding(top = 16.dp)) {
            Text("Skip for now", color = Color.White.copy(alpha = 0.4f))
        }
    }
}

@Composable
fun PermissionItem(icon: ImageVector, title: String, desc: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(color.copy(alpha = 0.1f), shape = MaterialTheme.shapes.medium),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = color)
        }
        Spacer(modifier = Modifier.width(20.dp))
        Column {
            Text(text = title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = desc, color = Color.White.copy(alpha = 0.5f), fontSize = 12.sp)
        }
    }
}

package com.example.autopulse_poe.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DragIndicator
import androidx.compose.material.icons.filled.Palette
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
fun DashboardEditorScreen(
    onBack: () -> Unit,
    onNavigateToThemePicker: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface)
            }
            Text(
                text = "Edit Dashboard",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = onNavigateToThemePicker) {
                Icon(Icons.Default.Palette, contentDescription = "Themes", tint = NeonCyan)
            }
        }

        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = "Active Widgets", color = NeonCyan, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(16.dp))
            
            WidgetEditorItem("Live Speedo (Large)", NeonCyan)
            WidgetEditorItem("Engine RPM (Medium)", NeonMagenta)
            WidgetEditorItem("Fuel Economy (Small)", NeonGreen)

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Icon(Icons.Default.Add, contentDescription = null, tint = NeonCyan)
                Spacer(modifier = Modifier.width(12.dp))
                Text("Add New Widget", color = MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}

@Composable
fun WidgetEditorItem(name: String, color: Color) {
    NeonCard(borderColor = color.copy(alpha = 0.3f), modifier = Modifier.padding(bottom = 12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Icon(Icons.Default.DragIndicator, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f))
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = name, color = MaterialTheme.colorScheme.onSurface, modifier = Modifier.weight(1f))
            TextButton(onClick = { }) {
                Text("Remove", color = NeonRed, fontSize = 12.sp)
            }
        }
    }
}

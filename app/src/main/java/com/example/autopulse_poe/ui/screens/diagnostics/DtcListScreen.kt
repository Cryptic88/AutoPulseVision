package com.example.autopulse_poe.ui.screens.diagnostics

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
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
fun DtcListScreen(
    onBack: () -> Unit,
    onNavigateToDetail: () -> Unit
) {
    var selectedSeverity by remember { mutableStateOf("All") }
    
    val mockDtcs = listOf(
        DtcItem("P0300", "Random Misfire", NeonRed, "Stored"),
        DtcItem("P0171", "System Too Lean", NeonOrange, "Pending"),
        DtcItem("P0442", "Evap Leak (Small)", NeonOrange, "Pending"),
        DtcItem("P0420", "Catalyst Efficiency", Color.Gray, "Permanent")
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
                text = "Fault Code List",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // Urgency Filter
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(label = "All", selected = selectedSeverity == "All") { selectedSeverity = "All" }
            FilterChip(label = "Critical", selected = selectedSeverity == "Critical") { selectedSeverity = "Critical" }
            FilterChip(label = "Pending", selected = selectedSeverity == "Pending") { selectedSeverity = "Pending" }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(mockDtcs) { dtc ->
                NeonCard(
                    borderColor = dtc.color.copy(alpha = 0.5f),
                    modifier = Modifier.clickable { onNavigateToDetail() }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(dtc.color.copy(alpha = 0.1f), MaterialTheme.shapes.small),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Warning, contentDescription = null, tint = dtc.color, modifier = Modifier.size(20.dp))
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = dtc.code, fontSize = 18.sp, fontWeight = FontWeight.Black, color = dtc.color)
                            Text(text = dtc.description, color = Color.White, fontSize = 14.sp)
                        }
                        Text(
                            text = dtc.status,
                            color = Color.White.copy(alpha = 0.4f),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FilterChip(label: String, selected: Boolean, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        color = if (selected) NeonCyan.copy(alpha = 0.1f) else Color.White.copy(alpha = 0.05f),
        shape = MaterialTheme.shapes.medium,
        border = if (selected) androidx.compose.foundation.BorderStroke(1.dp, NeonCyan) else null
    ) {
        Text(
            text = label,
            color = if (selected) NeonCyan else Color.White.copy(alpha = 0.6f),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

data class DtcItem(val code: String, val description: String, val color: Color, val status: String)

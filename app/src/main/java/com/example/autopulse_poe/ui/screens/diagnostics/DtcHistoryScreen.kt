package com.example.autopulse_poe.ui.screens.diagnostics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
fun DtcHistoryScreen(onBack: () -> Unit) {
    val historyItems = listOf(
        HistoryLogItem("P0300", "Random Misfire", "Detected", "July 28, 2026", NeonRed),
        HistoryLogItem("P0171", "System Too Lean", "Resolved", "July 15, 2026", NeonCyan),
        HistoryLogItem("P0420", "Catalyst Efficiency", "Pending", "June 30, 2026", Color.Gray)
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
                text = "DTC History",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(historyItems) { item ->
                HistoryCard(item)
            }
        }
    }
}

data class HistoryLogItem(val code: String, val desc: String, val status: String, val date: String, val color: Color)

@Composable
fun HistoryCard(item: HistoryLogItem) {
    NeonCard(borderColor = item.color.copy(alpha = 0.3f)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = item.code, color = item.color, fontWeight = FontWeight.Black, fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(12.dp))
                    Surface(
                        color = item.color.copy(alpha = 0.1f),
                        shape = CircleShape
                    ) {
                        Text(
                            text = item.status,
                            color = item.color,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                }
                Text(text = item.desc, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text(text = item.date, color = Color.White.copy(alpha = 0.5f), fontSize = 12.sp)
            }
        }
    }
}

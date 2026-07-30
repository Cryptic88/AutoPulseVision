package com.example.autopulse_poe.ui.screens.trips

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
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
fun TripsScreen(onNavigateToDetails: () -> Unit = {}) {
    var searchQuery by remember { mutableStateOf("") }
    
    val mockTrips = listOf(
        Trip("2026-07-28", "12.5 km", "18 min", 85, "28.4 MPG"),
        Trip("2026-07-27", "4.2 km", "7 min", 92, "31.2 MPG"),
        Trip("2026-07-26", "45.0 km", "55 min", 78, "26.5 MPG")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "Trip History",
                fontSize = 28.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            // Search Bar
            Surface(
                color = Color.White.copy(alpha = 0.05f),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Search, contentDescription = null, tint = Color.White.copy(alpha = 0.3f))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Search journeys...",
                        color = Color.White.copy(alpha = 0.3f),
                        fontSize = 15.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(Icons.Default.FilterList, contentDescription = null, tint = NeonCyan)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Filter Chips
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TripFilterChip("All", true)
                TripFilterChip("Recent", false)
                TripFilterChip("Eco", false)
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
        ) {
            items(mockTrips) { trip ->
                TripCard(trip, onClick = onNavigateToDetails)
            }
        }
    }
}

@Composable
fun TripFilterChip(label: String, selected: Boolean) {
    Surface(
        color = if (selected) NeonCyan.copy(alpha = 0.1f) else Color.White.copy(alpha = 0.05f),
        shape = RoundedCornerShape(20.dp),
        border = if (selected) androidx.compose.foundation.BorderStroke(1.dp, NeonCyan) else null
    ) {
        Text(
            text = label,
            color = if (selected) NeonCyan else Color.White.copy(alpha = 0.5f),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

data class Trip(val date: String, val distance: String, val duration: String, val score: Int, val efficiency: String)

@Composable
fun TripCard(trip: Trip, onClick: () -> Unit) {
    NeonCard(
        borderColor = NeonCyan.copy(alpha = 0.3f),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = trip.date, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text(
                    text = "${trip.distance} • ${trip.duration} • ${trip.efficiency}",
                    color = Color.White.copy(alpha = 0.5f),
                    fontSize = 12.sp
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = null,
                        tint = NeonCyan,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${trip.score}", fontSize = 20.sp, fontWeight = FontWeight.Black, color = Color.White)
                }
                Text(text = "Safety Score", color = NeonCyan, fontSize = 10.sp)
            }
        }
    }
}

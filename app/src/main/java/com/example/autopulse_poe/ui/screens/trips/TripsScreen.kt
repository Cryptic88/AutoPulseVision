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
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun TripsScreen(onNavigateToDetails: () -> Unit = {}) {
    var searchQuery by remember { mutableStateOf("") }

    val mockTrips = listOf(

        Trip(
            date = "2026-07-28",
            distance = "12.5 km",
            duration = "18 min",
            score = 85,
            efficiency = "28.4 MPG",
            route = listOf(
                RoutePoint(0.05f, 0.80f),
                RoutePoint(0.18f, 0.65f),
                RoutePoint(0.25f, 0.68f),
                RoutePoint(0.35f, 0.45f),
                RoutePoint(0.50f, 0.50f),
                RoutePoint(0.62f, 0.30f),
                RoutePoint(0.78f, 0.35f),
                RoutePoint(0.92f, 0.18f)
            )
        ),

        Trip(
            date = "2026-07-27",
            distance = "4.2 km",
            duration = "7 min",
            score = 92,
            efficiency = "31.2 MPG",
            route = listOf(
                RoutePoint(0.08f, 0.25f),
                RoutePoint(0.25f, 0.35f),
                RoutePoint(0.40f, 0.30f),
                RoutePoint(0.55f, 0.55f),
                RoutePoint(0.72f, 0.50f),
                RoutePoint(0.90f, 0.75f)
            )
        ),

        Trip(
            date = "2026-07-26",
            distance = "45.0 km",
            duration = "55 min",
            score = 78,
            efficiency = "26.5 MPG",
            route = listOf(
                RoutePoint(0.05f, 0.75f),
                RoutePoint(0.15f, 0.60f),
                RoutePoint(0.30f, 0.65f),
                RoutePoint(0.40f, 0.35f),
                RoutePoint(0.55f, 0.20f),
                RoutePoint(0.70f, 0.30f),
                RoutePoint(0.82f, 0.55f),
                RoutePoint(0.95f, 0.40f)
            )
        )
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

data class Trip(
    val date: String,
    val distance: String,
    val duration: String,
    val score: Int,
    val efficiency: String,
    val route: List<RoutePoint>
)

data class RoutePoint(
    val x: Float,
    val y: Float
)

@Composable
fun TripCard(
    trip: Trip,
    onClick: () -> Unit
) {
    NeonCard(
        borderColor = NeonCyan.copy(alpha = 0.3f),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // ------------------------------------------------
            // ROUTE PREVIEW
            // ------------------------------------------------

            RoutePreview(
                route = trip.route,
                modifier = Modifier
                    .width(105.dp)
                    .height(90.dp)
            )

            Spacer(modifier = Modifier.width(14.dp))

            // ------------------------------------------------
            // TRIP INFORMATION
            // ------------------------------------------------

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = trip.date,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = AutoPulseText
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "${trip.distance} • ${trip.duration}",
                    color = AutoPulseTextSecondary,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = trip.efficiency,
                    color = AutoPulseTextMuted,
                    fontSize = 11.sp
                )
            }

            // ------------------------------------------------
            // SAFETY SCORE
            // ------------------------------------------------

            Column(
                horizontalAlignment = Alignment.End
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = NeonCyan,
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = trip.score.toString(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black,
                        color = AutoPulseText
                    )
                }

                Text(
                    text = "Safety Score",
                    color = NeonCyan,
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Composable
private fun RoutePreview(
    route: List<RoutePoint>,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .background(
                color = AutoPulseBackground,
                shape = RoundedCornerShape(12.dp)
            )
    ) {

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {

            if (route.size >= 2) {

                val path = Path()

                val first = route.first()

                path.moveTo(
                    first.x * size.width,
                    first.y * size.height
                )

                route.drop(1).forEach { point ->

                    path.lineTo(
                        point.x * size.width,
                        point.y * size.height
                    )
                }

                // Route shadow
                drawPath(
                    path = path,
                    color = NeonCyan.copy(alpha = 0.15f),
                    style = Stroke(
                        width = 7.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                )

                // Main route
                drawPath(
                    path = path,
                    color = NeonCyan,
                    style = Stroke(
                        width = 2.5.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                )

                // Start point
                drawCircle(
                    color = AutoPulseSuccess,
                    radius = 4.dp.toPx(),
                    center = Offset(
                        route.first().x * size.width,
                        route.first().y * size.height
                    )
                )

                // End point
                drawCircle(
                    color = NeonMagenta,
                    radius = 4.dp.toPx(),
                    center = Offset(
                        route.last().x * size.width,
                        route.last().y * size.height
                    )
                )
            }
        }
    }
}

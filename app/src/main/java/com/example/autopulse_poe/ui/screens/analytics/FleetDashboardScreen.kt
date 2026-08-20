package com.example.autopulse_poe.ui.screens.analytics

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
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
fun FleetDashboardScreen() {

    val vehicles = listOf(
        FleetVehicle(
            name = "Tesla Model S",
            plate = "EV-942-CA",
            health = 94,
            status = "Healthy",
            lastSeen = "2 min ago",
            color = NeonCyan
        ),
        FleetVehicle(
            name = "Ford F-150",
            plate = "TRK-021-TX",
            health = 82,
            status = "Attention",
            lastSeen = "8 min ago",
            color = NeonOrange
        ),
        FleetVehicle(
            name = "BMW M3",
            plate = "DRV-155-DE",
            health = 88,
            status = "Healthy",
            lastSeen = "14 min ago",
            color = NeonMagenta
        )
    )

    val healthyVehicles = vehicles.count { it.health >= 85 }
    val attentionVehicles = vehicles.count { it.health in 70..84 }
    val criticalVehicles = vehicles.count { it.health < 70 }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {

        // ----------------------------------------------------
        // HEADER
        // ----------------------------------------------------

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "Fleet Dashboard",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Real-time vehicle overview",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f),
                    fontSize = 12.sp
                )
            }

            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(NeonCyan.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    Icons.Default.DirectionsCar,
                    contentDescription = null,
                    tint = NeonCyan
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ----------------------------------------------------
        // FLEET SUMMARY
        // ----------------------------------------------------

        Text(
            text = "FLEET STATUS",
            color = NeonCyan,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            FleetSummaryCard(
                title = "Healthy",
                value = healthyVehicles.toString(),
                icon = Icons.Default.CheckCircle,
                color = NeonGreen,
                modifier = Modifier.weight(1f)
            )

            FleetSummaryCard(
                title = "Attention",
                value = attentionVehicles.toString(),
                icon = Icons.Default.Warning,
                color = NeonOrange,
                modifier = Modifier.weight(1f)
            )

            FleetSummaryCard(
                title = "Critical",
                value = criticalVehicles.toString(),
                icon = Icons.Default.Error,
                color = NeonRed,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ----------------------------------------------------
        // FLEET HEALTH
        // ----------------------------------------------------

        NeonCard(
            borderColor = NeonPurple.copy(alpha = 0.5f)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    Icons.Default.HealthAndSafety,
                    contentDescription = null,
                    tint = NeonPurple,
                    modifier = Modifier.size(28.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "Overall Fleet Health",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Strong performance across all vehicles",
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        fontSize = 11.sp
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "88%",
                    color = NeonPurple,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            LinearProgressIndicator(
                progress = { 0.88f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(7.dp)
                    .clip(CircleShape),
                color = NeonPurple,
                trackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ----------------------------------------------------
        // VEHICLES
        // ----------------------------------------------------

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "YOUR VEHICLES",
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "${vehicles.size} VEHICLES",
                color = NeonCyan,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            items(vehicles) { vehicle ->

                FleetCard(
                    vehicle = vehicle,
                    onClick = {
                        // Future: navigate to vehicle details
                    }
                )
            }
        }
    }
}

// ------------------------------------------------------------
// DATA
// ------------------------------------------------------------

data class FleetVehicle(
    val name: String,
    val plate: String,
    val health: Int,
    val status: String,
    val lastSeen: String,
    val color: Color
)

// ------------------------------------------------------------
// SUMMARY CARD
// ------------------------------------------------------------

@Composable
fun FleetSummaryCard(
    title: String,
    value: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {

    NeonCard(
        borderColor = color.copy(alpha = 0.3f),
        modifier = modifier
    ) {

        Icon(
            icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = value,
            color = color,
            fontSize = 24.sp,
            fontWeight = FontWeight.Black
        )

        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            fontSize = 10.sp
        )
    }
}

// ------------------------------------------------------------
// VEHICLE CARD
// ------------------------------------------------------------

@Composable
fun FleetCard(
    vehicle: FleetVehicle,
    onClick: () -> Unit
) {

    val statusColor = when {
        vehicle.health >= 85 -> NeonGreen
        vehicle.health >= 70 -> NeonOrange
        else -> NeonRed
    }

    NeonCard(
        borderColor = vehicle.color.copy(alpha = 0.35f),
        modifier = Modifier.clickable {
            onClick()
        }
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Vehicle icon

            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(vehicle.color.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    Icons.Default.DirectionsCar,
                    contentDescription = null,
                    tint = vehicle.color,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = vehicle.name,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )

                Text(
                    text = vehicle.plate,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f),
                    fontSize = 11.sp
                )

                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(statusColor)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = vehicle.status,
                        color = statusColor,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "• ${vehicle.lastSeen}",
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.35f),
                        fontSize = 10.sp
                    )
                }
            }

            // Health

            Column(
                horizontalAlignment = Alignment.End
            ) {

                Text(
                    text = "${vehicle.health}%",
                    color = statusColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black
                )

                Text(
                    text = "HEALTH",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.35f),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        LinearProgressIndicator(
            progress = { vehicle.health / 100f },
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
                .clip(CircleShape),
            color = statusColor,
            trackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.07f)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            Text(
                text = "VIEW VEHICLE  →",
                color = vehicle.color,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

package com.example.autopulse_poe.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.MoreVert
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
fun VehicleProfilesScreen(
    onBack: () -> Unit,
    onAddVehicle: () -> Unit
) {

    val vehicles = listOf(
        VehicleProfile(
            name = "Tesla Model S",
            specs = "Electric • 2024",
            isActive = true,
            color = AutoPulseCyan
        ),
        VehicleProfile(
            name = "Ford F-150",
            specs = "V8 Gas • 2022",
            isActive = false,
            color = AutoPulseWarning
        )
    )

    Scaffold(
        containerColor = AutoPulseBackground,
        floatingActionButton = {

            FloatingActionButton(
                onClick = onAddVehicle,
                containerColor = AutoPulsePurple,
                contentColor = Color.White,
                shape = CircleShape
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Vehicle"
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 12.dp,
                        end = 20.dp,
                        top = 12.dp,
                        bottom = 16.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(onClick = onBack) {

                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = AutoPulseText
                    )
                }

                Column(
                    modifier = Modifier.padding(start = 4.dp)
                ) {

                    Text(
                        text = "My Vehicles",
                        color = AutoPulseText,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "${vehicles.size} vehicles saved",
                        color = AutoPulseTextSecondary,
                        fontSize = 11.sp
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    horizontal = 20.dp,
                    vertical = 8.dp
                ),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                items(vehicles) { vehicle ->

                    VehicleProfileCard(vehicle)
                }

                item {

                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}


@Composable
private fun VehicleProfileCard(
    vehicle: VehicleProfile
) {

    NeonCard(
        borderColor = if (vehicle.isActive) {
            vehicle.color.copy(alpha = 0.7f)
        } else {
            AutoPulseBorder
        }
    ) {

        Column {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .background(
                            vehicle.color.copy(alpha = 0.12f)
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.DirectionsCar,
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
                        color = AutoPulseText,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = vehicle.specs,
                        color = AutoPulseTextSecondary,
                        fontSize = 11.sp
                    )
                }

                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Vehicle options",
                    tint = AutoPulseTextMuted
                )
            }

            if (vehicle.isActive) {

                Spacer(modifier = Modifier.height(16.dp))

                Surface(
                    color = AutoPulseSuccess.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(8.dp)
                ) {

                    Row(
                        modifier = Modifier.padding(
                            horizontal = 10.dp,
                            vertical = 6.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = AutoPulseSuccess,
                            modifier = Modifier.size(14.dp)
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = "ACTIVE VEHICLE",
                            color = AutoPulseSuccess,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }
        }
    }
}


data class VehicleProfile(
    val name: String,
    val specs: String,
    val isActive: Boolean,
    val color: Color
)
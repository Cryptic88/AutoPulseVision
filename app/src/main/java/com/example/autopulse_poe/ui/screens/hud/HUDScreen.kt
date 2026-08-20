package com.example.autopulse_poe.ui.screens.hud

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.theme.*

@Composable
fun HUDScreen() {

    val speed = 85
    val rpm = 2450
    val temperature = 92
    val gear = "D"
    val speedLimit = 120

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        // CONNECTION STATUS
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(AutoPulseSuccess)
            )

            Spacer(modifier = Modifier.width(6.dp))

            Icon(
                Icons.Default.Bluetooth,
                contentDescription = null,
                tint = AutoPulseSuccess,
                modifier = Modifier.size(16.dp)
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = "OBD CONNECTED",
                color = AutoPulseSuccess,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // MAIN DISPLAY
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // SPEED
            Text(
                text = speed.toString(),
                fontSize = 150.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "km/h",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = AutoPulseCyan
            )

            Spacer(modifier = Modifier.height(35.dp))

            // VEHICLE DATA
            Row(
                horizontalArrangement = Arrangement.spacedBy(45.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                HUDStatItem(
                    label = "RPM",
                    value = rpm.toString()
                )

                HUDStatItem(
                    label = "TEMP",
                    value = "$temperature°C"
                )

                HUDStatItem(
                    label = "GEAR",
                    value = gear
                )
            }

            Spacer(modifier = Modifier.height(35.dp))

            // SPEED LIMIT
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "LIMIT",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(8.dp))

                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                    )
                ) {
                    Text(
                        text = speedLimit.toString(),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }

        // BOTTOM STATUS BAR
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp,
                    vertical = 20.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(
                    text = "TRIP",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "42.8 km",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "ENGINE",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "NORMAL",
                    color = AutoPulseSuccess,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black
                )
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {

                Text(
                    text = "OBD LATENCY",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "42 ms",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun HUDStatItem(
    label: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = value,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 25.sp,
            fontWeight = FontWeight.Black
        )
    }
}
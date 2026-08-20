package com.example.autopulse_poe.ui.screens.diagnostics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun FreezeFrameScreen(
    onBack: () -> Unit
) {

    val sensorData = listOf(
        "Engine RPM" to "3,450 RPM",
        "Vehicle Speed" to "82 km/h",
        "Coolant Temperature" to "98 °C",
        "Engine Load" to "74.5 %",
        "Long Fuel Trim" to "+4.2 %",
        "Short Fuel Trim" to "-1.8 %",
        "Intake Pressure" to "95 kPa",
        "MAF Flow" to "42.5 g/s"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        // HEADER

        Row(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 14.dp
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = onBack) {

                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {

                Text(
                    text = "Freeze Frame",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "ECU snapshot at fault detection",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 10.sp
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {

            // FAULT CONTEXT

            NeonCard(
                borderColor = AutoPulseError.copy(alpha = 0.45f)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        Icons.Default.CameraAlt,
                        contentDescription = null,
                        tint = AutoPulseError,
                        modifier = Modifier.size(25.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {

                        Text(
                            text = "CAPTURED WITH P0300",
                            color = AutoPulseError,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Black
                        )

                        Text(
                            text = "Random / multiple cylinder misfire",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "VEHICLE PARAMETERS",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            NeonCard(
                borderColor = AutoPulseCyan.copy(alpha = 0.30f)
            ) {

                sensorData.forEachIndexed { index, (label, value) ->

                    FreezeFrameItem(
                        label = label,
                        value = value
                    )

                    if (index < sensorData.lastIndex) {

                        HorizontalDivider(
                            modifier = Modifier.padding(
                                vertical = 11.dp
                            ),
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            NeonCard(
                borderColor = AutoPulsePurple.copy(alpha = 0.25f)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        Icons.Default.Info,
                        contentDescription = null,
                        tint = AutoPulsePurple
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Freeze frame data is captured automatically by the ECU when a diagnostic fault is recorded.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 11.sp,
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}


@Composable
fun FreezeFrameItem(
    label: String,
    value: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 12.sp
        )

        Text(
            text = value,
            color = AutoPulseCyan,
            fontWeight = FontWeight.Black,
            fontSize = 14.sp
        )
    }
}

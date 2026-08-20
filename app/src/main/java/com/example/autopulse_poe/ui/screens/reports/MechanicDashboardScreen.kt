package com.example.autopulse_poe.ui.screens.reports

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun MechanicDashboardScreen(
    onBack: () -> Unit
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
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    text = "Mechanic Console",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "ADVANCED DIAGNOSTICS",
                    color = NeonCyan,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {

            // Critical issue banner
            NeonCard(
                borderColor = NeonRed
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(NeonRed.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Warning,
                            contentDescription = null,
                            tint = NeonRed
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "CRITICAL DTC DETECTED",
                            color = NeonRed,
                            fontWeight = FontWeight.Black,
                            fontSize = 13.sp
                        )

                        Text(
                            text = "P0300 — Random/Multiple Misfire",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            MechanicSection(
                title = "Diagnostic Summary",
                icon = Icons.Default.Assignment,
                color = NeonCyan
            ) {

                DiagnosticCount(
                    "Stored DTCs",
                    "1",
                    NeonRed
                )

                DiagnosticCount(
                    "Pending DTCs",
                    "2",
                    NeonOrange
                )

                DiagnosticCount(
                    "Permanent DTCs",
                    "0",
                    NeonGreen
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            MechanicSection(
                title = "Live PID Stream",
                icon = Icons.Default.SettingsInputComponent,
                color = NeonPurple
            ) {

                MechanicStatRow(
                    "Calculated Engine Load",
                    "74.5 %",
                    White
                )

                MechanicStatRow(
                    "Fuel Pressure",
                    "345 kPa",
                    White
                )

                MechanicStatRow(
                    "Timing Advance",
                    "14.5°",
                    White
                )

                MechanicStatRow(
                    "Intake Air Temperature",
                    "32 °C",
                    White
                )

                MechanicStatRow(
                    "Engine RPM",
                    "3,450 RPM",
                    White
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            MechanicSection(
                title = "ECU / Module Information",
                icon = Icons.Default.Memory,
                color = NeonMagenta
            ) {

                MechanicStatRow(
                    "ECU Protocol",
                    "ISO 15765-4",
                    NeonMagenta
                )

                MechanicStatRow(
                    "CAN ID",
                    "11-bit / 500k",
                    MaterialTheme.colorScheme.onSurface
                )

                MechanicStatRow(
                    "Adapter Latency",
                    "42 ms",
                    NeonGreen
                )

                MechanicStatRow(
                    "ECU Voltage",
                    "14.2 V",
                    NeonGreen
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            NeonCard(
                borderColor = NeonOrange.copy(alpha = 0.5f)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        Icons.Default.Info,
                        contentDescription = null,
                        tint = NeonOrange
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "Technician Recommendation",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Inspect ignition components and intake system before clearing the stored DTC.",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    fontSize = 13.sp,
                    lineHeight = 19.sp
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun DiagnosticCount(
    label: String,
    value: String,
    color: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.background)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            fontSize = 13.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = value,
            color = color,
            fontSize = 16.sp,
            fontWeight = FontWeight.Black
        )
    }
}

@Composable
fun MechanicSection(
    title: String,
    icon: ImageVector,
    color: Color,
    content: @Composable ColumnScope.() -> Unit
) {

    Column {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        NeonCard(
            borderColor = color.copy(alpha = 0.35f)
        ) {
            content()
        }
    }
}

@Composable
fun MechanicStatRow(
    label: String,
    value: String,
    valueColor: Color = MaterialTheme.colorScheme.onSurface
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 7.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f),
            fontSize = 13.sp
        )

        Text(
            text = value,
            color = valueColor,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
        )
    }
}
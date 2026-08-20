package com.example.autopulse_poe.ui.screens.reports

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Share
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
fun ReportViewerScreen(
    onBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        // Header
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

            Text(
                text = "Health Report",
                fontSize = 21.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = { }
            ) {
                Icon(
                    Icons.Default.Share,
                    contentDescription = "Share report",
                    tint = NeonCyan
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onSurface)
                .verticalScroll(rememberScrollState())
                .padding(28.dp)
        ) {

            Text(
                text = "AUTOPULSE",
                color = Color.Black,
                fontWeight = FontWeight.Black,
                fontSize = 22.sp
            )

            Text(
                text = "VEHICLE HEALTH REPORT",
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 16.dp),
                thickness = 2.dp,
                color = Color.Black
            )

            Text(
                text = "2024 Tesla Model 3",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "VIN: 5YJ3E1EA5KF123456",
                color = Color.DarkGray,
                fontSize = 12.sp
            )

            Text(
                text = "Report Date: 30 July 2026",
                color = Color.DarkGray,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Health score
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFFF2F2F2),
                        MaterialTheme.shapes.medium
                    )
                    .padding(20.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(
                                Color(0xFF00A86B).copy(alpha = 0.12f)
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "92%",
                            color = Color(0xFF008A58),
                            fontWeight = FontWeight.Black,
                            fontSize = 20.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(18.dp))

                    Column {
                        Text(
                            text = "GOOD VEHICLE HEALTH",
                            color = Color.Black,
                            fontWeight = FontWeight.Black,
                            fontSize = 14.sp
                        )

                        Text(
                            text = "Vehicle operating within normal parameters.",
                            color = Color.DarkGray,
                            fontSize = 11.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            ReportSectionTitle("DIAGNOSTIC SUMMARY")

            ReportStatusRow(
                "Stored DTCs",
                "1",
                false
            )

            ReportStatusRow(
                "Pending DTCs",
                "2",
                false
            )

            ReportStatusRow(
                "Emissions Readiness",
                "PASS",
                true
            )

            Spacer(modifier = Modifier.height(24.dp))

            ReportSectionTitle("SENSOR SNAPSHOT")

            ReportTableItem("Engine RPM", "750 RPM")
            ReportTableItem("Coolant Temperature", "92 °C")
            ReportTableItem("Battery Voltage", "14.2 V")
            ReportTableItem("Fuel Trim", "+1.2 %")
            ReportTableItem("Engine Load", "74.5 %")

            Spacer(modifier = Modifier.height(24.dp))

            ReportSectionTitle("SYSTEM STATUS")

            ReportStatusRow(
                "Catalyst",
                "PASS",
                true
            )

            ReportStatusRow(
                "Oxygen Sensors",
                "PASS",
                true
            )

            ReportStatusRow(
                "Fuel System",
                "PASS",
                true
            )

            ReportStatusRow(
                "EVAP System",
                "ATTENTION",
                false
            )

            Spacer(modifier = Modifier.height(24.dp))

            ReportSectionTitle("RECOMMENDATION")

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFFFFF3E0),
                        MaterialTheme.shapes.medium
                    )
                    .padding(16.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.Top
                ) {

                    Icon(
                        Icons.Default.Warning,
                        contentDescription = null,
                        tint = Color(0xFFE67E00),
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Inspect the ignition and intake systems before clearing the stored diagnostic trouble code.",
                        color = Color.DarkGray,
                        fontSize = 12.sp,
                        lineHeight = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            HorizontalDivider(color = Color.LightGray)

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Generated by AutoPulse Vision",
                color = Color.Gray,
                fontSize = 10.sp
            )

            Text(
                text = "Diagnostic data retrieved through OBD-II interface.",
                color = Color.Gray,
                fontSize = 10.sp
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun ReportSectionTitle(
    title: String
) {
    Text(
        text = title,
        color = Color.Black,
        fontWeight = FontWeight.Black,
        fontSize = 14.sp,
        modifier = Modifier.padding(bottom = 10.dp)
    )
}

@Composable
fun ReportStatusRow(
    label: String,
    value: String,
    passed: Boolean
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = label,
            color = Color.DarkGray,
            fontSize = 12.sp
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                if (passed)
                    Icons.Default.CheckCircle
                else
                    Icons.Default.Warning,
                contentDescription = null,
                tint = if (passed)
                    Color(0xFF008A58)
                else
                    Color(0xFFE67E00),
                modifier = Modifier.size(15.dp)
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = value,
                color = if (passed)
                    Color(0xFF008A58)
                else
                    Color(0xFFE67E00),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun ReportTableItem(
    label: String,
    value: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = label,
            color = Color.DarkGray,
            fontSize = 12.sp
        )

        Text(
            text = value,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}

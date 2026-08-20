package com.example.autopulse_poe.ui.screens.performance

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun AdvancedPerformanceScreen(
    onBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        // ----------------------------------------------------
        // HEADER
        // ----------------------------------------------------

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onBack
            ) {

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Column(
                modifier = Modifier.padding(start = 4.dp)
            ) {

                Text(
                    text = "DYNO ANALYSIS",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Power & torque performance",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp
                )
            }
        }

        // ----------------------------------------------------
        // CONTENT
        // ----------------------------------------------------

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            // ------------------------------------------------
            // PERFORMANCE GRAPH
            // ------------------------------------------------

            NeonCard(
                borderColor = AutoPulsePurple.copy(alpha = 0.45f),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(330.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {

                        Text(
                            text = "POWER & TORQUE",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(3.dp))

                        Text(
                            text = "RPM PERFORMANCE CURVE",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.End
                    ) {

                        LegendItem(
                            label = "Power",
                            color = AutoPulsePurple
                        )

                        LegendItem(
                            label = "Torque",
                            color = AutoPulseCyan
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Graph
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(
                            color = Color.Black.copy(alpha = 0.22f),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(12.dp)
                ) {

                    Canvas(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        // ------------------------------------
                        // GRID
                        // ------------------------------------

                        val horizontalLines = 4
                        val verticalLines = 5

                        for (i in 0..horizontalLines) {

                            val y =
                                size.height * i / horizontalLines

                            drawLine(
                                color = AutoPulseBorder.copy(alpha = 0.45f),
                                start = androidx.compose.ui.geometry.Offset(
                                    0f,
                                    y
                                ),
                                end = androidx.compose.ui.geometry.Offset(
                                    size.width,
                                    y
                                ),
                                strokeWidth = 1.dp.toPx()
                            )
                        }

                        for (i in 0..verticalLines) {

                            val x =
                                size.width * i / verticalLines

                            drawLine(
                                color = AutoPulseBorder.copy(alpha = 0.35f),
                                start = androidx.compose.ui.geometry.Offset(
                                    x,
                                    0f
                                ),
                                end = androidx.compose.ui.geometry.Offset(
                                    x,
                                    size.height
                                ),
                                strokeWidth = 1.dp.toPx()
                            )
                        }

                        // ------------------------------------
                        // POWER CURVE
                        // ------------------------------------

                        val powerPath = Path().apply {

                            moveTo(
                                0f,
                                size.height * 0.90f
                            )

                            quadraticTo(
                                size.width * 0.18f,
                                size.height * 0.75f,
                                size.width * 0.35f,
                                size.height * 0.48f
                            )

                            quadraticTo(
                                size.width * 0.52f,
                                size.height * 0.22f,
                                size.width * 0.72f,
                                size.height * 0.15f
                            )

                            quadraticTo(
                                size.width * 0.88f,
                                size.height * 0.10f,
                                size.width,
                                size.height * 0.25f
                            )
                        }

                        // ------------------------------------
                        // TORQUE CURVE
                        // ------------------------------------

                        val torquePath = Path().apply {

                            moveTo(
                                0f,
                                size.height * 0.68f
                            )

                            quadraticTo(
                                size.width * 0.15f,
                                size.height * 0.30f,
                                size.width * 0.32f,
                                size.height * 0.22f
                            )

                            quadraticTo(
                                size.width * 0.48f,
                                size.height * 0.18f,
                                size.width * 0.62f,
                                size.height * 0.32f
                            )

                            quadraticTo(
                                size.width * 0.80f,
                                size.height * 0.48f,
                                size.width,
                                size.height * 0.55f
                            )
                        }

                        drawPath(
                            path = powerPath,
                            color = AutoPulsePurple,
                            style = Stroke(
                                width = 3.dp.toPx(),
                                cap = StrokeCap.Round
                            )
                        )

                        drawPath(
                            path = torquePath,
                            color = AutoPulseCyan,
                            style = Stroke(
                                width = 3.dp.toPx(),
                                cap = StrokeCap.Round
                            )
                        )
                    }

                    // X-axis label
                    Text(
                        text = "RPM",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 9.sp,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ------------------------------------------------
            // PEAK VALUES
            // ------------------------------------------------

            Text(
                text = "PEAK PERFORMANCE",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                PeakValueCard(
                    label = "PEAK POWER",
                    value = "285 HP",
                    subValue = "@ 6,400 RPM",
                    color = AutoPulsePurple,
                    modifier = Modifier.weight(1f)
                )

                PeakValueCard(
                    label = "PEAK TORQUE",
                    value = "420 Nm",
                    subValue = "@ 3,200 RPM",
                    color = AutoPulseCyan,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ------------------------------------------------
            // PERFORMANCE GAIN
            // ------------------------------------------------

            Text(
                text = "PERFORMANCE GAIN",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            NeonCard(
                borderColor = AutoPulseSuccess.copy(alpha = 0.4f)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                AutoPulseSuccess.copy(alpha = 0.12f),
                                RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = AutoPulseSuccess,
                            modifier = Modifier.size(21.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {

                        Text(
                            text = "ABOVE STOCK PERFORMANCE",
                            color = AutoPulseSuccess,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Black
                        )

                        Spacer(modifier = Modifier.height(3.dp))

                        Text(
                            text = "Current output is higher than factory specifications.",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 11.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                PerformanceGainRow(
                    label = "Horsepower",
                    value = "+15 HP",
                    percentage = "+5.6%",
                    color = AutoPulsePurple
                )

                PerformanceGainRow(
                    label = "Torque",
                    value = "+25 Nm",
                    percentage = "+6.3%",
                    color = AutoPulseCyan
                )

                PerformanceGainRow(
                    label = "Weight",
                    value = "-12 kg",
                    percentage = "-1.2%",
                    color = AutoPulseSuccess
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ------------------------------------------------
            // TEST INFORMATION
            // ------------------------------------------------

            Text(
                text = "TEST INFORMATION",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            NeonCard {

                PerformanceInfoRow(
                    label = "Peak Power RPM",
                    value = "6,400 RPM"
                )

                PerformanceInfoRow(
                    label = "Peak Torque RPM",
                    value = "3,200 RPM"
                )

                PerformanceInfoRow(
                    label = "Test Type",
                    value = "Rolling Dyno"
                )

                PerformanceInfoRow(
                    label = "Measurement",
                    value = "Estimated"
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}


// ============================================================
// LEGEND
// ============================================================

@Composable
private fun LegendItem(
    label: String,
    color: Color
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 4.dp)
    ) {

        Box(
            modifier = Modifier
                .size(7.dp)
                .background(
                    color = color,
                    shape = RoundedCornerShape(50)
                )
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


// ============================================================
// PEAK VALUE CARD
// ============================================================

@Composable
private fun PeakValueCard(
    label: String,
    value: String,
    subValue: String,
    color: Color,
    modifier: Modifier = Modifier
) {

    NeonCard(
        modifier = modifier,
        borderColor = color.copy(alpha = 0.35f)
    ) {

        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(7.dp))

        Text(
            text = value,
            color = color,
            fontSize = 24.sp,
            fontWeight = FontWeight.Black
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = subValue,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 10.sp
        )
    }
}


// ============================================================
// PERFORMANCE GAIN ROW
// ============================================================

@Composable
private fun PerformanceGainRow(
    label: String,
    value: String,
    percentage: String,
    color: Color
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(8.dp)
                .background(
                    color = color,
                    shape = RoundedCornerShape(50)
                )
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 13.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = value,
            color = color,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(10.dp))

        Surface(
            color = color.copy(alpha = 0.10f),
            shape = RoundedCornerShape(50)
        ) {

            Text(
                text = percentage,
                color = color,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                )
            )
        }
    }
}


// ============================================================
// INFORMATION ROW
// ============================================================

@Composable
private fun PerformanceInfoRow(
    label: String,
    value: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 7.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 13.sp
        )

        Text(
            text = value,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

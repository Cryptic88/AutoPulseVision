package com.example.autopulse_poe.ui.screens.performance

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun PerformanceMetricsHubScreen(
    onBack: () -> Unit,
    onNavigateToDyno: () -> Unit
){


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AutoPulseBackground)
    ) {

        // ----------------------------------------------------
        // HEADER
        // ----------------------------------------------------

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 12.dp,
                    end = 20.dp,
                    top = 12.dp,
                    bottom = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onBack
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = AutoPulseText
                )
            }

            Column(
                modifier = Modifier.padding(start = 4.dp)
            ) {

                Text(
                    text = "Performance Metrics",
                    color = AutoPulseText,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Live vehicle performance",
                    color = AutoPulseTextSecondary,
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

            Spacer(modifier = Modifier.height(8.dp))


            // ------------------------------------------------
            // POWER + TORQUE
            // ------------------------------------------------

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                PerformanceValueCard(
                    modifier = Modifier.weight(1f),
                    label = "EST. POWER",
                    value = "285",
                    unit = "HP",
                    change = "+15 HP",
                    accent = AutoPulsePurple,
                    icon = Icons.Default.Bolt
                )

                PerformanceValueCard(
                    modifier = Modifier.weight(1f),
                    label = "EST. TORQUE",
                    value = "420",
                    unit = "Nm",
                    change = "+25 Nm",
                    accent = AutoPulseCyan,
                    icon = Icons.Default.Speed
                )
            }


            Spacer(modifier = Modifier.height(28.dp))


            // ------------------------------------------------
            // LIVE TELEMETRY
            // ------------------------------------------------

            Text(
                text = "LIVE TELEMETRY",
                color = AutoPulseText,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                TelemetryCard(
                    modifier = Modifier.weight(1f),
                    label = "ENGINE RPM",
                    value = "4,250",
                    unit = "RPM",
                    accent = AutoPulseCyan
                )

                TelemetryCard(
                    modifier = Modifier.weight(1f),
                    label = "SPEED",
                    value = "86",
                    unit = "km/h",
                    accent = AutoPulseSuccess
                )
            }


            Spacer(modifier = Modifier.height(12.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                TelemetryCard(
                    modifier = Modifier.weight(1f),
                    label = "ENGINE LOAD",
                    value = "58",
                    unit = "%",
                    accent = AutoPulseWarning
                )

                TelemetryCard(
                    modifier = Modifier.weight(1f),
                    label = "THROTTLE",
                    value = "42",
                    unit = "%",
                    accent = AutoPulsePurple
                )
            }


            Spacer(modifier = Modifier.height(28.dp))


            // ------------------------------------------------
            // RPM GAUGE
            // ------------------------------------------------

            Text(
                text = "ENGINE RPM",
                color = AutoPulseText,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            NeonCard(
                borderColor = AutoPulseCyan.copy(alpha = 0.45f),
                modifier = Modifier.fillMaxWidth()
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),
                    contentAlignment = Alignment.Center
                ) {

                    PerformanceGauge(
                        value = 4250f,
                        maxValue = 7000f,
                        accent = AutoPulseCyan
                    )
                }
            }


            Spacer(modifier = Modifier.height(28.dp))


// ------------------------------------------------
// DYNO MODE
// ------------------------------------------------

            NeonCard(
                borderColor = AutoPulsePurple.copy(alpha = 0.45f),
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .background(
                                color = AutoPulsePurple.copy(alpha = 0.12f),
                                shape = RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.Tune,
                            contentDescription = null,
                            tint = AutoPulsePurple,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "DYNAMOMETER MODE",
                            color = AutoPulseText,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.height(3.dp))

                        Text(
                            text = "Analyse power and torque from your performance run",
                            color = AutoPulseTextSecondary,
                            fontSize = 11.sp,
                            lineHeight = 15.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // ------------------------------------------------
                // DYNO ACTION BUTTON
                // ------------------------------------------------

                Button(
                    onClick = onNavigateToDyno,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(13.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AutoPulsePurple,
                        contentColor = Color.White
                    )
                ) {

                    Icon(
                        imageVector = Icons.Default.Tune,
                        contentDescription = null,
                        modifier = Modifier.size(19.dp)
                    )

                    Spacer(modifier = Modifier.width(9.dp))

                    Text(
                        text = "OPEN DYNO ANALYSIS",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun PerformanceValueCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    unit: String,
    change: String,
    accent: Color,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {

    NeonCard(
        modifier = modifier,
        borderColor = accent.copy(alpha = 0.45f)
    ) {

        Text(
            text = label,
            color = AutoPulseTextSecondary,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            verticalAlignment = Alignment.Bottom
        ) {

            Text(
                text = value,
                color = accent,
                fontSize = 32.sp,
                fontWeight = FontWeight.Black
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = unit,
                color = AutoPulseText,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 5.dp)
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = accent.copy(alpha = 0.7f),
                modifier = Modifier.size(13.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "$change vs stock",
                color = AutoPulseSuccess,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
private fun TelemetryCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    unit: String,
    accent: Color
) {

    NeonCard(
        modifier = modifier,
        borderColor = accent.copy(alpha = 0.35f)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(
                        color = accent,
                        shape = androidx.compose.foundation.shape.CircleShape
                    )
            )

            Spacer(modifier = Modifier.width(7.dp))

            Text(
                text = label,
                color = AutoPulseTextSecondary,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            verticalAlignment = Alignment.Bottom
        ) {

            Text(
                text = value,
                color = AutoPulseText,
                fontSize = 25.sp,
                fontWeight = FontWeight.Black
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = unit,
                color = accent,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    }
}


@Composable
private fun PerformanceGauge(
    value: Float,
    maxValue: Float,
    accent: Color
) {

    Box(
        modifier = Modifier.size(210.dp),
        contentAlignment = Alignment.Center
    ) {

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {

            val strokeWidth = 14.dp.toPx()

            // Background track
            drawArc(
                color = AutoPulseSurfaceElevated,
                startAngle = 135f,
                sweepAngle = 270f,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth,
                    cap = StrokeCap.Round
                )
            )

            // Active value
            drawArc(
                color = accent,
                startAngle = 135f,
                sweepAngle = 270f * (value / maxValue),
                useCenter = false,
                style = Stroke(
                    width = strokeWidth,
                    cap = StrokeCap.Round
                )
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = value.toInt().toString(),
                color = AutoPulseText,
                fontSize = 38.sp,
                fontWeight = FontWeight.Black
            )

            Text(
                text = "RPM",
                color = accent,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "of ${maxValue.toInt()}",
                color = AutoPulseTextMuted,
                fontSize = 10.sp
            )
        }
    }
}
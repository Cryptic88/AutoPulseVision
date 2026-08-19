package com.example.autopulse_poe.ui.screens.trips

import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun TripDetailsScreen(
    onBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AutoPulseBackground)
            .verticalScroll(rememberScrollState())
    ) {

        // ====================================================
        // ROUTE MAP
        // ====================================================

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(AutoPulseSurface)
        ) {

            TripRouteMap(
                modifier = Modifier.fillMaxSize()
            )

            // ------------------------------------------------
            // BACK BUTTON
            // ------------------------------------------------

            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        AutoPulseBackground.copy(alpha = 0.85f)
                    )
            ) {

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = AutoPulseText
                )
            }

            // ------------------------------------------------
            // ROUTE LABEL
            // ------------------------------------------------

            Surface(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                color = AutoPulseBackground.copy(alpha = 0.9f),
                shape = RoundedCornerShape(10.dp)
            ) {

                Row(
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(
                                AutoPulseCyan,
                                CircleShape
                            )
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "TRIP ROUTE",
                        color = AutoPulseCyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }
            }
        }


        // ====================================================
        // TRIP INFORMATION
        // ====================================================

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = "Downtown Commute",
                fontSize = 25.sp,
                fontWeight = FontWeight.Black,
                color = AutoPulseText
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "28 July 2026 • 08:30 AM",
                color = AutoPulseTextSecondary,
                fontSize = 13.sp
            )


            Spacer(modifier = Modifier.height(24.dp))


            // =================================================
            // TRIP STATS
            // =================================================

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                TripDetailStat(
                    label = "DISTANCE",
                    value = "12.5 km",
                    icon = Icons.Default.Timeline,
                    color = AutoPulseCyan,
                    modifier = Modifier.weight(1f)
                )

                TripDetailStat(
                    label = "DURATION",
                    value = "18 min",
                    icon = Icons.Default.Timer,
                    color = AutoPulsePurple,
                    modifier = Modifier.weight(1f)
                )
            }


            Spacer(modifier = Modifier.height(10.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                TripDetailStat(
                    label = "AVG SPEED",
                    value = "42 km/h",
                    icon = Icons.Default.Speed,
                    color = AutoPulseCyanDark,
                    modifier = Modifier.weight(1f)
                )

                TripDetailStat(
                    label = "EFFICIENCY",
                    value = "8.2 L/100km",
                    icon = Icons.Default.LocalGasStation,
                    color = AutoPulseSuccess,
                    modifier = Modifier.weight(1f)
                )
            }


            Spacer(modifier = Modifier.height(30.dp))


            // =================================================
            // TRIP SCORE
            // =================================================

            Text(
                text = "TRIP SCORE",
                color = AutoPulseTextSecondary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            NeonCard(
                borderColor = AutoPulseCyan.copy(alpha = 0.45f)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "SAFETY SCORE",
                            color = AutoPulseTextMuted,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = "85",
                            color = White,
                            fontSize = 38.sp,
                            fontWeight = FontWeight.Black
                        )

                        Text(
                            text = "Excellent driving",
                            color = AutoPulseSuccess,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Score indicator

                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(
                                AutoPulseCyan.copy(alpha = 0.1f)
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "85%",
                            color = AutoPulseCyan,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(30.dp))


            // =================================================
            // FUEL & COST
            // =================================================

            Text(
                text = "FUEL & COST",
                color = AutoPulseTextSecondary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            NeonCard(
                borderColor = AutoPulseSuccess.copy(alpha = 0.4f)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {

                        Text(
                            text = "ESTIMATED TRIP COST",
                            color = AutoPulseTextMuted,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = "R48.20",
                            color = White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Black
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.End
                    ) {

                        Text(
                            text = "FUEL USED",
                            color = AutoPulseTextMuted,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = "1.03 L",
                            color = AutoPulseText,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(30.dp))


            // =================================================
            // DRIVING BEHAVIOUR
            // =================================================

            Text(
                text = "DRIVING BEHAVIOUR",
                color = AutoPulseTextSecondary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            NeonCard(
                borderColor = AutoPulseCyanDark.copy(alpha = 0.35f)
            ) {

                BehaviorItem(
                    label = "Hard Braking",
                    value = "2 detected",
                    icon = Icons.Default.Warning,
                    color = AutoPulseError
                )

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = AutoPulseBorder
                )

                BehaviorItem(
                    label = "Rapid Acceleration",
                    value = "0 detected",
                    icon = Icons.Default.CheckCircle,
                    color = AutoPulseSuccess
                )

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = AutoPulseBorder
                )

                BehaviorItem(
                    label = "Cornering",
                    value = "Optimal",
                    icon = Icons.Default.CheckCircle,
                    color = AutoPulseSuccess
                )

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = AutoPulseBorder
                )

                BehaviorItem(
                    label = "Excessive Speed",
                    value = "1 detected",
                    icon = Icons.Default.Speed,
                    color = AutoPulseWarning
                )
            }


            Spacer(modifier = Modifier.height(30.dp))


            // =================================================
            // TRIP SUMMARY
            // =================================================

            Text(
                text = "TRIP SUMMARY",
                color = AutoPulseTextSecondary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            NeonCard {

                TripSummaryRow(
                    label = "Starting Location",
                    value = "Home"
                )

                TripSummaryRow(
                    label = "Destination",
                    value = "Downtown"
                )

                TripSummaryRow(
                    label = "Maximum Speed",
                    value = "68 km/h"
                )

                TripSummaryRow(
                    label = "Average RPM",
                    value = "2,840 RPM"
                )
            }


            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}


// ============================================================
// ROUTE MAP
// ============================================================

@Composable
private fun TripRouteMap(
    modifier: Modifier = Modifier
) {

    Canvas(
        modifier = modifier
    ) {

        // ----------------------------------------------------
        // BACKGROUND GRID
        // ----------------------------------------------------

        val gridSpacing = 55.dp.toPx()

        var x = 0f

        while (x < size.width) {

            drawLine(
                color = AutoPulseBorder.copy(alpha = 0.25f),
                start = Offset(x, 0f),
                end = Offset(x, size.height),
                strokeWidth = 1.dp.toPx()
            )

            x += gridSpacing
        }

        var y = 0f

        while (y < size.height) {

            drawLine(
                color = AutoPulseBorder.copy(alpha = 0.25f),
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 1.dp.toPx()
            )

            y += gridSpacing
        }


        // ----------------------------------------------------
        // ROAD LINES
        // ----------------------------------------------------

        val roadColor = AutoPulseTextMuted.copy(alpha = 0.12f)

        drawLine(
            color = roadColor,
            start = Offset(0f, size.height * 0.25f),
            end = Offset(size.width, size.height * 0.65f),
            strokeWidth = 18.dp.toPx()
        )

        drawLine(
            color = roadColor,
            start = Offset(size.width * 0.15f, size.height),
            end = Offset(size.width * 0.75f, 0f),
            strokeWidth = 14.dp.toPx()
        )


        // ----------------------------------------------------
        // ROUTE
        // ----------------------------------------------------

        val routePath = Path().apply {

            moveTo(
                size.width * 0.15f,
                size.height * 0.75f
            )

            cubicTo(
                size.width * 0.30f,
                size.height * 0.68f,
                size.width * 0.28f,
                size.height * 0.40f,
                size.width * 0.45f,
                size.height * 0.43f
            )

            cubicTo(
                size.width * 0.62f,
                size.height * 0.46f,
                size.width * 0.62f,
                size.height * 0.25f,
                size.width * 0.82f,
                size.height * 0.22f
            )
        }


        drawPath(
            path = routePath,
            color = AutoPulseCyan.copy(alpha = 0.18f),
            style = Stroke(
                width = 10.dp.toPx(),
                cap = StrokeCap.Round
            )
        )

        drawPath(
            path = routePath,
            color = AutoPulseCyan,
            style = Stroke(
                width = 4.dp.toPx(),
                cap = StrokeCap.Round
            )
        )


        // ----------------------------------------------------
        // START MARKER
        // ----------------------------------------------------

        drawCircle(
            color = AutoPulseSuccess,
            radius = 9.dp.toPx(),
            center = Offset(
                size.width * 0.15f,
                size.height * 0.75f
            )
        )

        drawCircle(
            color = AutoPulseBackground,
            radius = 4.dp.toPx(),
            center = Offset(
                size.width * 0.15f,
                size.height * 0.75f
            )
        )


        // ----------------------------------------------------
        // END MARKER
        // ----------------------------------------------------

        drawCircle(
            color = AutoPulseCyanDark,
            radius = 9.dp.toPx(),
            center = Offset(
                size.width * 0.82f,
                size.height * 0.22f
            )
        )

        drawCircle(
            color = AutoPulseBackground,
            radius = 4.dp.toPx(),
            center = Offset(
                size.width * 0.82f,
                size.height * 0.22f
            )
        )
    }
}


// ============================================================
// TRIP DETAIL STAT
// ============================================================

@Composable
private fun TripDetailStat(
    label: String,
    value: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {

    NeonCard(
        borderColor = color.copy(alpha = 0.3f),
        modifier = modifier
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(19.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {

                Text(
                    text = label,
                    color = AutoPulseTextMuted,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = value,
                    color = AutoPulseText,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
    }
}


// ============================================================
// BEHAVIOUR ITEM
// ============================================================

@Composable
private fun BehaviorItem(
    label: String,
    value: String,
    icon: ImageVector,
    color: Color
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = label,
            color = AutoPulseText,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = value,
            color = color,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
        )
    }
}


// ============================================================
// SUMMARY ROW
// ============================================================

@Composable
private fun TripSummaryRow(
    label: String,
    value: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = label,
            color = AutoPulseTextSecondary,
            fontSize = 13.sp
        )

        Text(
            text = value,
            color = AutoPulseText,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
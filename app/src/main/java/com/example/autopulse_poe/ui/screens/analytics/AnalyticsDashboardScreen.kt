package com.example.autopulse_poe.ui.screens.analytics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun AnalyticsDashboardScreen() {

    var selectedPeriod by remember { mutableStateOf("7D") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
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
                    text = "Advanced Analytics",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )

                Text(
                    text = "Understand your driving behaviour",
                    color = Color.White.copy(alpha = 0.55f),
                    fontSize = 12.sp
                )
            }

            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(NeonPurple.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Analytics,
                    contentDescription = null,
                    tint = NeonPurple
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ----------------------------------------------------
        // PERIOD SELECTOR
        // ----------------------------------------------------

        Text(
            text = "ANALYSIS PERIOD",
            color = NeonCyan,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            listOf("7D", "30D", "90D").forEach { period ->

                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            selectedPeriod = period
                        },
                    color = if (selectedPeriod == period) {
                        NeonCyan.copy(alpha = 0.15f)
                    } else {
                        Color.White.copy(alpha = 0.04f)
                    },
                    shape = RoundedCornerShape(10.dp),
                    border = if (selectedPeriod == period) {
                        androidx.compose.foundation.BorderStroke(
                            1.dp,
                            NeonCyan.copy(alpha = 0.6f)
                        )
                    } else {
                        null
                    }
                ) {
                    Box(
                        modifier = Modifier.padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = period,
                            color = if (selectedPeriod == period) {
                                NeonCyan
                            } else {
                                Color.White.copy(alpha = 0.6f)
                            },
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ----------------------------------------------------
        // PERFORMANCE OVERVIEW
        // ----------------------------------------------------

        Text(
            text = "PERFORMANCE OVERVIEW",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            AnalyticsStatCard(
                title = "Efficiency",
                value = "28.4",
                unit = "MPG",
                change = "+6%",
                color = NeonGreen,
                modifier = Modifier.weight(1f)
            )

            AnalyticsStatCard(
                title = "Avg Speed",
                value = "54",
                unit = "km/h",
                change = "+4%",
                color = NeonCyan,
                modifier = Modifier.weight(1f)
            )

            AnalyticsStatCard(
                title = "Safety",
                value = "92",
                unit = "/100",
                change = "+8%",
                color = NeonPurple,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ----------------------------------------------------
        // DRIVING PATTERNS
        // ----------------------------------------------------

        NeonCard(
            borderColor = NeonPurple
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(NeonPurple.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.ShowChart,
                        contentDescription = null,
                        tint = NeonPurple
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "Driving Patterns",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Text(
                        text = "Your driving behaviour is improving",
                        color = NeonPurple,
                        fontSize = 11.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Your average speed has increased by 12% during peak morning hours. Fuel efficiency is currently strongest around 55 km/h.",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 13.sp,
                lineHeight = 19.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ----------------------------------------------------
        // EFFICIENCY GRAPH
        // ----------------------------------------------------

        Text(
            text = "EFFICIENCY TREND",
            color = NeonCyan,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        NeonCard(
            borderColor = NeonCyan
        ) {

            Column {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Fuel efficiency",
                        color = Color.White.copy(alpha = 0.6f),
                        fontSize = 12.sp
                    )

                    Text(
                        text = "28.4 MPG",
                        color = NeonGreen,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                AnalyticsLineChart()

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Mon", color = Color.White.copy(alpha = 0.4f), fontSize = 10.sp)
                    Text("Tue", color = Color.White.copy(alpha = 0.4f), fontSize = 10.sp)
                    Text("Wed", color = Color.White.copy(alpha = 0.4f), fontSize = 10.sp)
                    Text("Thu", color = Color.White.copy(alpha = 0.4f), fontSize = 10.sp)
                    Text("Fri", color = Color.White.copy(alpha = 0.4f), fontSize = 10.sp)
                    Text("Sat", color = Color.White.copy(alpha = 0.4f), fontSize = 10.sp)
                    Text("Sun", color = Color.White.copy(alpha = 0.4f), fontSize = 10.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ----------------------------------------------------
        // DRIVING INSIGHTS
        // ----------------------------------------------------

        Text(
            text = "DRIVING INSIGHTS",
            color = NeonOrange,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        InsightItem(
            icon = Icons.Default.Speed,
            title = "Optimal cruising speed",
            description = "Your vehicle performs most efficiently around 55 km/h.",
            color = NeonGreen
        )

        Spacer(modifier = Modifier.height(10.dp))

        InsightItem(
            icon = Icons.Default.Warning,
            title = "Hard braking detected",
            description = "3 hard braking events were recorded this week.",
            color = NeonOrange
        )

        Spacer(modifier = Modifier.height(10.dp))

        InsightItem(
            icon = Icons.Default.LocalGasStation,
            title = "Fuel efficiency improving",
            description = "Your average consumption improved by 6% this month.",
            color = NeonCyan
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ----------------------------------------------------
        // ANOMALY DETECTION
        // ----------------------------------------------------

        NeonCard(
            borderColor = NeonRed
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    Icons.Default.Warning,
                    contentDescription = null,
                    tint = NeonRed,
                    modifier = Modifier.size(28.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "Anomaly Detected",
                        color = NeonRed,
                        fontWeight = FontWeight.Black,
                        fontSize = 15.sp
                    )

                    Text(
                        text = "Battery behaviour requires attention",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Resting voltage dropped below 11.8V three times this week. Consider checking the battery and charging system.",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            OutlinedButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = NeonRed
                )
            ) {
                Text(
                    text = "VIEW DIAGNOSTIC DATA",
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

// ------------------------------------------------------------
// ANALYTICS STAT CARD
// ------------------------------------------------------------

@Composable
fun AnalyticsStatCard(
    title: String,
    value: String,
    unit: String,
    change: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    NeonCard(
        borderColor = color.copy(alpha = 0.35f),
        modifier = modifier
    ) {

        Text(
            text = title,
            color = Color.White.copy(alpha = 0.5f),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            verticalAlignment = Alignment.Bottom
        ) {

            Text(
                text = value,
                color = color,
                fontSize = 22.sp,
                fontWeight = FontWeight.Black
            )

            Spacer(modifier = Modifier.width(3.dp))

            Text(
                text = unit,
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 9.sp,
                modifier = Modifier.padding(bottom = 3.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = change,
            color = NeonGreen,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// ------------------------------------------------------------
// LINE CHART
// ------------------------------------------------------------

@Composable
fun AnalyticsLineChart() {

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
    ) {

        val points = listOf(
            Offset(0f, size.height * 0.75f),
            Offset(size.width * 0.16f, size.height * 0.60f),
            Offset(size.width * 0.33f, size.height * 0.65f),
            Offset(size.width * 0.50f, size.height * 0.42f),
            Offset(size.width * 0.66f, size.height * 0.50f),
            Offset(size.width * 0.83f, size.height * 0.30f),
            Offset(size.width, size.height * 0.22f)
        )

        // Grid lines
        repeat(4) { index ->

            val y = size.height * (index + 1) / 5f

            drawLine(
                color = Color.White.copy(alpha = 0.05f),
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 1.dp.toPx()
            )
        }

        // Graph
        val path = Path().apply {
            moveTo(points.first().x, points.first().y)

            points.drop(1).forEach {
                lineTo(it.x, it.y)
            }
        }

        drawPath(
            path = path,
            color = NeonCyan,
            style = Stroke(
                width = 3.dp.toPx()
            )
        )

        points.forEach {
            drawCircle(
                color = NeonCyan,
                radius = 4.dp.toPx(),
                center = it
            )
        }
    }
}

// ------------------------------------------------------------
// INSIGHT ITEM
// ------------------------------------------------------------

@Composable
fun InsightItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String,
    color: Color
) {

    Surface(
        color = Color.White.copy(alpha = 0.04f),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(22.dp)
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Text(
                    text = title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )

                Text(
                    text = description,
                    color = Color.White.copy(alpha = 0.55f),
                    fontSize = 11.sp,
                    lineHeight = 16.sp
                )
            }
        }
    }
}
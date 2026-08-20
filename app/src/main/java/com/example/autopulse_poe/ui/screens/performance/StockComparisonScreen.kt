package com.example.autopulse_poe.ui.screens.performance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.CircularScoreIndicator
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun StockComparisonScreen(
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

            IconButton(onClick = onBack) {

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
                    text = "STOCK COMPARISON",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Vehicle performance analysis",
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
            // VEHICLE
            // ------------------------------------------------

            NeonCard(
                borderColor = AutoPulseCyan.copy(alpha = 0.45f)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .background(
                                AutoPulseCyan.copy(alpha = 0.10f),
                                RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.DirectionsCar,
                            contentDescription = null,
                            tint = AutoPulseCyan,
                            modifier = Modifier.size(25.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "CURRENT VEHICLE",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(3.dp))

                        Text(
                            text = "2024 Tesla Model S Plaid",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Text(
                        text = "CONNECTED",
                        color = AutoPulseSuccess,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ------------------------------------------------
            // OVERALL RESULT
            // ------------------------------------------------

            Text(
                text = "PERFORMANCE OVERVIEW",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            NeonCard(
                borderColor = AutoPulseSuccess.copy(alpha = 0.45f)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "OVERALL RESULT",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "ABOVE STOCK",
                            color = AutoPulseSuccess,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Black
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Vehicle is performing above factory specifications.",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 11.sp
                        )
                    }

                    CircularScoreIndicator(
                        score = 97,
                        label = "SCORE",
                        color = AutoPulseSuccess
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ------------------------------------------------
            // PERFORMANCE COMPARISON
            // ------------------------------------------------

            Text(
                text = "PERFORMANCE COMPARISON",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            StockComparisonCard(
                label = "MAX HORSEPOWER",
                factory = "1,020 HP",
                current = "1,035 HP",
                difference = "+15 HP",
                percentage = "+1.5%",
                accent = AutoPulsePurple,
                positive = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            StockComparisonCard(
                label = "MAX TORQUE",
                factory = "1,420 Nm",
                current = "1,445 Nm",
                difference = "+25 Nm",
                percentage = "+1.8%",
                accent = AutoPulseCyan,
                positive = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            StockComparisonCard(
                label = "0–100 KM/H",
                factory = "2.10 s",
                current = "1.98 s",
                difference = "-0.12 s",
                percentage = "-5.7%",
                accent = AutoPulseSuccess,
                positive = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            StockComparisonCard(
                label = "BRAKING DISTANCE",
                factory = "34.2 m",
                current = "32.8 m",
                difference = "-1.4 m",
                percentage = "-4.1%",
                accent = AutoPulseWarning,
                positive = true
            )

            Spacer(modifier = Modifier.height(28.dp))

            // ------------------------------------------------
            // INSIGHTS
            // ------------------------------------------------

            Text(
                text = "AUTOPULSE INSIGHTS",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            NeonCard(
                borderColor = AutoPulseCyan.copy(alpha = 0.35f)
            ) {

                Row(
                    verticalAlignment = Alignment.Top
                ) {

                    Icon(
                        imageVector = Icons.Default.TrendingUp,
                        contentDescription = null,
                        tint = AutoPulseCyan,
                        modifier = Modifier.size(22.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {

                        Text(
                            text = "Performance Analysis",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Current measurements indicate improved power delivery compared with the factory baseline. Acceleration and braking performance are also showing measurable improvements.",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 12.sp,
                            lineHeight = 18.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}

@Composable
private fun StockComparisonCard(
    label: String,
    factory: String,
    current: String,
    difference: String,
    percentage: String,
    accent: Color,
    positive: Boolean,
    modifier: Modifier = Modifier
) {

    NeonCard(
        modifier = modifier.fillMaxWidth(),
        borderColor = accent.copy(alpha = 0.35f)
    ) {

        // ----------------------------------------------------
        // TITLE
        // ----------------------------------------------------

        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.8.sp
        )

        Spacer(modifier = Modifier.height(14.dp))

        // ----------------------------------------------------
        // FACTORY VS CURRENT
        // ----------------------------------------------------

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "FACTORY",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = factory,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Icon(
                imageVector = Icons.Default.CompareArrows,
                contentDescription = null,
                tint = accent.copy(alpha = 0.55f),
                modifier = Modifier.size(24.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.End
            ) {

                Text(
                    text = "AUTOPULSE",
                    color = accent,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = current,
                    color = accent,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // ----------------------------------------------------
        // RESULT
        // ----------------------------------------------------

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Surface(
                color = if (positive) {
                    AutoPulseSuccess.copy(alpha = 0.10f)
                } else {
                    AutoPulseError.copy(alpha = 0.10f)
                },
                shape = RoundedCornerShape(50)
            ) {

                Row(
                    modifier = Modifier.padding(
                        horizontal = 9.dp,
                        vertical = 5.dp
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = difference,
                        color = if (positive) {
                            AutoPulseSuccess
                        } else {
                            AutoPulseError
                        },
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = percentage,
                        color = if (positive) {
                            AutoPulseSuccess
                        } else {
                            AutoPulseError
                        },
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}



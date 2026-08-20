package com.example.autopulse_poe.ui.screens.performance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun AccelerationTimerScreen(
    onBack: () -> Unit
) {
    var isRacing by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        // ====================================================
        // HEADER
        // ====================================================

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
                    text = "ACCELERATION TIMER",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Performance testing",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp
                )
            }
        }

        // ====================================================
        // SCROLLABLE CONTENT
        // ====================================================

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            // =================================================
            // TEST STATUS / MAIN TIMER
            // =================================================

            NeonCard(
                borderColor = if (isRacing) {
                    AutoPulseSuccess.copy(alpha = 0.65f)
                } else {
                    AutoPulseCyan.copy(alpha = 0.55f)
                }
            ) {

                // Status header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {

                        Text(
                            text = "TEST STATUS",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(9.dp)
                                    .background(
                                        color = if (isRacing) {
                                            AutoPulseSuccess
                                        } else {
                                            AutoPulseCyan
                                        },
                                        shape = RoundedCornerShape(50)
                                    )
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = if (isRacing) {
                                    "TEST IN PROGRESS"
                                } else {
                                    "READY"
                                },
                                color = if (isRacing) {
                                    AutoPulseSuccess
                                } else {
                                    AutoPulseCyan
                                },
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.Default.Timer,
                        contentDescription = null,
                        tint = if (isRacing) {
                            AutoPulseSuccess
                        } else {
                            AutoPulseCyan
                        },
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                // Main timer
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "06.42",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 72.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = (-2).sp
                    )

                    Text(
                        text = "SECONDS",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "0 → 100 km/h",
                        color = AutoPulseCyan,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Progress
                LinearProgressIndicator(
                    progress = { if (isRacing) 0.68f else 1f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp),
                    color = if (isRacing) {
                        AutoPulseSuccess
                    } else {
                        AutoPulseCyan
                    },
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // =================================================
            // ACTION BUTTON
            // =================================================

            Button(
                onClick = {
                    isRacing = !isRacing
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isRacing) {
                        AutoPulseError
                    } else {
                        AutoPulseCyan
                    },
                    contentColor = if (isRacing) {
                        MaterialTheme.colorScheme.onSurface
                    } else {
                        Color.Black
                    }
                )
            ) {

                Text(
                    text = if (isRacing) {
                        "STOP TEST"
                    } else {
                        "START ACCELERATION"
                    },
                    fontWeight = FontWeight.Black,
                    fontSize = 15.sp
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // =================================================
            // PERFORMANCE SPLITS
            // =================================================

            Text(
                text = "PERFORMANCE SPLITS",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                AccelerationSplitCard(
                    modifier = Modifier.weight(1f),
                    label = "0–60 KM/H",
                    value = "2.81 s",
                    accent = AutoPulseCyan
                )

                AccelerationSplitCard(
                    modifier = Modifier.weight(1f),
                    label = "0–100 KM/H",
                    value = "6.42 s",
                    accent = AutoPulseSuccess
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // =================================================
            // QUARTER MILE
            // =================================================

            NeonCard(
                borderColor = AutoPulsePurple.copy(alpha = 0.4f)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "1/4 MILE",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "14.50 s",
                            color = AutoPulsePurple,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Black
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.End
                    ) {

                        Text(
                            text = "TRAP SPEED",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "158 km/h",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // =================================================
            // TEST CONDITIONS
            // =================================================

            Text(
                text = "TEST CONDITIONS",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            NeonCard {

                AccelerationConditionRow(
                    label = "Starting Speed",
                    value = "0 km/h"
                )

                AccelerationConditionRow(
                    label = "Peak Speed",
                    value = "158 km/h"
                )

                AccelerationConditionRow(
                    label = "Peak RPM",
                    value = "6,420 RPM"
                )

                AccelerationConditionRow(
                    label = "Surface",
                    value = "Dry"
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}


// ============================================================
// PERFORMANCE SPLIT CARD
// ============================================================

@Composable
private fun AccelerationSplitCard(
    label: String,
    value: String,
    accent: Color,
    modifier: Modifier = Modifier
) {
    NeonCard(
        modifier = modifier,
        borderColor = accent.copy(alpha = 0.35f)
    ) {

        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = value,
            color = accent,
            fontSize = 24.sp,
            fontWeight = FontWeight.Black
        )
    }
}


// ============================================================
// TEST CONDITION ROW
// ============================================================

@Composable
private fun AccelerationConditionRow(
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
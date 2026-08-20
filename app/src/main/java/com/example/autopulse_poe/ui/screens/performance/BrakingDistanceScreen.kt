package com.example.autopulse_poe.ui.screens.performance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Speed
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
fun BrakingDistanceScreen(
    onBack: () -> Unit
) {

    var isMonitoring by remember { mutableStateOf(false) }

    // --------------------------------------------------------
    // STATE COLOURS
    // --------------------------------------------------------

    val statusColor = if (isMonitoring) {
        AutoPulseError
    } else {
        AutoPulseCyan
    }

    val statusText = if (isMonitoring) {
        "BRAKING TEST ACTIVE"
    } else {
        "READY TO TEST"
    }

    val buttonText = if (isMonitoring) {
        "CANCEL BRAKING TEST"
    } else {
        "START BRAKING TEST"
    }

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
                    text = "BRAKING DISTANCE",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Deceleration testing",
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
            // TEST STATUS + MAIN RESULT
            // ------------------------------------------------

            NeonCard(
                borderColor = statusColor.copy(alpha = 0.55f)
            ) {

                // --------------------------------------------
                // STATUS HEADER
                // --------------------------------------------

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

                            // Status indicator
                            Box(
                                modifier = Modifier
                                    .size(9.dp)
                                    .background(
                                        color = statusColor,
                                        shape = RoundedCornerShape(50)
                                    )
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = statusText,
                                color = statusColor,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.Default.Speed,
                        contentDescription = null,
                        tint = statusColor,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                // --------------------------------------------
                // MAIN BRAKING DISTANCE
                // --------------------------------------------

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "BRAKING DISTANCE",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.5.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {

                        Text(
                            text = "38.5",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 72.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = (-2).sp
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "m",
                            color = AutoPulseCyan,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "100 → 0 KM/H",
                        color = AutoPulseCyan,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // --------------------------------------------
                // DECELERATION
                // --------------------------------------------

                Text(
                    text = "DECELERATION",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                LinearProgressIndicator(
                    progress = {
                        if (isMonitoring) {
                            0.78f
                        } else {
                            1f
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp),
                    color = statusColor,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "0 G",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 10.sp
                    )

                    Text(
                        text = "1.12 G PEAK",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ------------------------------------------------
            // ACTION BUTTON
            // ------------------------------------------------

            Button(
                onClick = {
                    isMonitoring = !isMonitoring
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isMonitoring) {
                        AutoPulseError
                    } else {
                        AutoPulseCyan
                    },
                    contentColor = if (isMonitoring) {
                        MaterialTheme.colorScheme.onSurface
                    } else {
                        Color.Black
                    }
                )
            ) {

                Text(
                    text = buttonText,
                    fontWeight = FontWeight.Black,
                    fontSize = 15.sp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // ------------------------------------------------
            // PERFORMANCE RESULTS
            // ------------------------------------------------

            Text(
                text = "PERFORMANCE RESULTS",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                BrakingResultCard(
                    modifier = Modifier.weight(1f),
                    label = "100–0 KM/H",
                    value = "38.5 m",
                    accent = AutoPulseCyan
                )

                BrakingResultCard(
                    modifier = Modifier.weight(1f),
                    label = "PEAK DECEL",
                    value = "1.12 G",
                    accent = AutoPulseError
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                BrakingResultCard(
                    modifier = Modifier.weight(1f),
                    label = "80–0 KM/H",
                    value = "24.1 m",
                    accent = AutoPulseSuccess
                )

                BrakingResultCard(
                    modifier = Modifier.weight(1f),
                    label = "STOP TIME",
                    value = "2.91 s",
                    accent = AutoPulsePurple
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ------------------------------------------------
            // TEST CONDITIONS
            // ------------------------------------------------

            Text(
                text = "TEST CONDITIONS",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            NeonCard {

                BrakingConditionRow(
                    label = "Starting Speed",
                    value = "100 km/h"
                )

                BrakingConditionRow(
                    label = "Final Speed",
                    value = "0 km/h"
                )

                BrakingConditionRow(
                    label = "Peak Deceleration",
                    value = "1.12 G"
                )

                BrakingConditionRow(
                    label = "Surface",
                    value = "Dry"
                )

                BrakingConditionRow(
                    label = "Test Status",
                    value = if (isMonitoring) {
                        "In Progress"
                    } else {
                        "Ready"
                    }
                )
            }

            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}


// ============================================================
// RESULT CARD
// ============================================================

@Composable
private fun BrakingResultCard(
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
            fontSize = 23.sp,
            fontWeight = FontWeight.Black
        )
    }
}


// ============================================================
// CONDITION ROW
// ============================================================

@Composable
private fun BrakingConditionRow(
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
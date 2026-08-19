package com.example.autopulse_poe.ui.screens.diagnostics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun DtcDetailScreen(onBack: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AutoPulseBackground)
            .verticalScroll(rememberScrollState())
    ) {

        // HEADER
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = AutoPulseText
                )
            }

            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    text = "Fault Analysis",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = AutoPulseText
                )

                Text(
                    text = "Diagnostic trouble code",
                    color = AutoPulseTextMuted,
                    fontSize = 10.sp
                )
            }
        }

        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {

            // FAULT
            NeonCard(
                borderColor = AutoPulseError.copy(alpha = 0.65f)
            ) {

                Text(
                    text = "P0300",
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Black,
                    color = AutoPulseError
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Random or Multiple Cylinder Misfire Detected",
                    fontSize = 17.sp,
                    color = AutoPulseText,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    DiagnosticBadge(
                        text = "STORED",
                        color = AutoPulseError
                    )

                    DiagnosticBadge(
                        text = "CRITICAL",
                        color = AutoPulseWarning
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // AI
            NeonCard(
                borderColor = AutoPulseCyanDark.copy(alpha = 0.55f)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = AutoPulseCyanDark
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "AI Mechanic Assistant",
                        color = AutoPulseText,
                        fontWeight = FontWeight.Black
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Surface(
                    color = AutoPulseSurfaceElevated,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(
                        modifier = Modifier.padding(15.dp)
                    ) {

                        Text(
                            text = "AutoPulse analysis suggests checking the ignition system and spark plugs first.",
                            color = AutoPulseText,
                            fontSize = 13.sp,
                            lineHeight = 19.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = AutoPulseCyanDark
                            )
                        ) {
                            Text(
                                text = "Start Repair Chat",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // COMMON CAUSES
            SectionHeading("COMMON CAUSES")

            Spacer(modifier = Modifier.height(10.dp))

            CauseItem("Worn spark plugs or ignition coils")
            CauseItem("Vacuum leak in intake system")
            CauseItem("Low fuel pressure")
            CauseItem("Fuel injector issue")

            Spacer(modifier = Modifier.height(24.dp))

            // ACTIONS
            SectionHeading("RECOMMENDED ACTIONS")

            Spacer(modifier = Modifier.height(10.dp))

            NeonCard(
                borderColor = AutoPulseCyan.copy(alpha = 0.25f)
            ) {
                ActionItem(
                    "Check spark plug condition",
                    true
                )

                ActionItem(
                    "Inspect ignition coil packs",
                    false
                )

                ActionItem(
                    "Check fuel pressure",
                    false
                )

                ActionItem(
                    "Inspect intake for vacuum leaks",
                    false
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun DiagnosticBadge(
    text: String,
    color: Color
) {
    Surface(
        color = color.copy(alpha = 0.12f),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = text,
            color = color,
            fontSize = 9.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(
                horizontal = 9.dp,
                vertical = 5.dp
            )
        )
    }
}

@Composable
private fun SectionHeading(text: String) {
    Text(
        text = text,
        color = AutoPulseCyan,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
    )
}

@Composable
fun CauseItem(text: String) {
    Row(
        modifier = Modifier.padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Default.Info,
            contentDescription = null,
            tint = AutoPulseCyan.copy(alpha = 0.6f),
            modifier = Modifier.size(16.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = text,
            color = AutoPulseText,
            fontSize = 13.sp
        )
    }
}

@Composable
fun ActionItem(
    text: String,
    isPrimary: Boolean
) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            Icons.Default.Build,
            contentDescription = null,
            tint = if (isPrimary) {
                AutoPulseCyan
            } else {
                AutoPulseTextMuted
            },
            modifier = Modifier.size(18.dp)
        )

        Spacer(modifier = Modifier.width(14.dp))

        Text(
            text = text,
            color = AutoPulseText,
            fontSize = 13.sp
        )
    }
}

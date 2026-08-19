package com.example.autopulse_poe.ui.screens.diagnostics

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.BluetoothSearching
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.ClearDtcDialog
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun DiagnosticsMainScreen(
    onNavigateToDtcList: () -> Unit,
    onNavigateToAdvanced: () -> Unit,
    onNavigateToFreezeFrame: () -> Unit,
    onNavigateToVehicleInfo: () -> Unit,
    onNavigateToHistory: () -> Unit,
    onClearCodes: () -> Unit = {}
) {

    var showClearDialog by remember { mutableStateOf(false) }
    var isScanning by remember { mutableStateOf(false) }
    var scanProgress by remember { mutableFloatStateOf(0f) }

    /*
     * Simulated ECU scan.
     *
     * Later this can be replaced with the real OBD-II
     * communication logic.
     */
    LaunchedEffect(isScanning) {

        if (isScanning) {

            scanProgress = 0f

            val scanDuration = 3000L
            val steps = 30

            repeat(steps) {

                delay(scanDuration / steps)

                scanProgress = (it + 1) / steps.toFloat()
            }

            delay(300)

            isScanning = false

            // Scan completed.
            // Navigate to the detected fault.
            onNavigateToDtcList()
        }
    }

    if (showClearDialog) {
        ClearDtcDialog(
            onConfirm = {
                showClearDialog = false
                onClearCodes()
            },
            onDismiss = {
                showClearDialog = false
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AutoPulseBackground)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
            .padding(bottom = 32.dp)
    ) {

        Spacer(modifier = Modifier.height(12.dp))

        // ---------------------------------------------------------
        // HEADER
        // ---------------------------------------------------------

        Text(
            text = "Diagnostics",
            color = AutoPulseText,
            fontSize = 28.sp,
            fontWeight = FontWeight.Black
        )

        Text(
            text = "Vehicle health & fault analysis",
            color = AutoPulseTextSecondary,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ---------------------------------------------------------
        // CONNECTION STATUS
        // ---------------------------------------------------------

        NeonCard(
            borderColor = AutoPulseCyan.copy(alpha = 0.55f)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(
                            AutoPulseCyan.copy(alpha = 0.10f)
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.BluetoothSearching,
                        contentDescription = null,
                        tint = AutoPulseCyan,
                        modifier = Modifier.size(26.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "ADAPTER CONNECTED",
                        color = AutoPulseCyan,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black
                    )

                    Text(
                        text = "OBDLink MX+",
                        color = AutoPulseText,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 3.dp)
                    )

                    Text(
                        text = "Latency 42ms • Stable connection",
                        color = AutoPulseTextMuted,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(9.dp)
                        .clip(CircleShape)
                        .background(AutoPulseSuccess)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ---------------------------------------------------------
        // FAULT SUMMARY
        // ---------------------------------------------------------

        Text(
            text = "FAULT SUMMARY",
            color = AutoPulseTextSecondary,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            FaultSummaryItem(
                label = "Stored",
                count = "1",
                color = AutoPulseError,
                modifier = Modifier.weight(1f)
            )

            FaultSummaryItem(
                label = "Pending",
                count = "2",
                color = AutoPulseWarning,
                modifier = Modifier.weight(1f)
            )

            FaultSummaryItem(
                label = "Permanent",
                count = "0",
                color = AutoPulseTextMuted,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        // ---------------------------------------------------------
        // DIAGNOSTIC ACTIONS
        // ---------------------------------------------------------

        Text(
            text = "DIAGNOSTIC ACTIONS",
            color = AutoPulseTextSecondary,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        /*
         * FULL ECU SCAN
         */

        ScanButton(
            isScanning = isScanning,
            progress = scanProgress,
            onClick = {
                if (!isScanning) {
                    isScanning = true
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        /*
         * CLEAR CODES
         */

        Button(
            onClick = {
                if (!isScanning) {
                    showClearDialog = true
                }
            },
            enabled = !isScanning,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AutoPulseSurface,
                contentColor = AutoPulseError,
                disabledContainerColor = AutoPulseSurface.copy(alpha = 0.4f)
            ),
            shape = MaterialTheme.shapes.medium,
            border = androidx.compose.foundation.BorderStroke(
                width = 1.dp,
                color = AutoPulseError.copy(alpha = 0.35f)
            )
        ) {

            Icon(
                imageVector = Icons.Default.DeleteForever,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Clear All Codes",
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        // ---------------------------------------------------------
        // TECHNICAL DATA
        // ---------------------------------------------------------

        Text(
            text = "TECHNICAL DATA",
            color = AutoPulseTextSecondary,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        /*
         * Freeze Frame
         */

        DiagnosticMenuCard(
            title = "Freeze Frame",
            description = "View vehicle data captured when a fault occurred.",
            icon = Icons.Default.CameraAlt,
            color = AutoPulseMagenta,
            onClick = onNavigateToFreezeFrame
        )

        Spacer(modifier = Modifier.height(10.dp))

        /*
         * Advanced Tests
         */

        DiagnosticMenuCard(
            title = "Advanced Tests",
            description = "Run advanced ECU and component diagnostics.",
            icon = Icons.Default.SettingsInputComponent,
            color = AutoPulsePurple,
            onClick = onNavigateToAdvanced
        )

        Spacer(modifier = Modifier.height(10.dp))

        /*
         * Vehicle Information
         */

        DiagnosticMenuCard(
            title = "Vehicle Information",
            description = "View VIN, ECU information and vehicle identifiers.",
            icon = Icons.Default.Info,
            color = AutoPulseCyan,
            onClick = onNavigateToVehicleInfo
        )

        Spacer(modifier = Modifier.height(10.dp))

        /*
         * History
         */

        DiagnosticMenuCard(
            title = "Diagnostic History",
            description = "Review previous scans and recorded fault codes.",
            icon = Icons.Default.History,
            color = AutoPulseWarning,
            onClick = onNavigateToHistory
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}


// ================================================================
// ECU SCAN BUTTON
// ================================================================

@Composable
private fun ScanButton(
    isScanning: Boolean,
    progress: Float,
    onClick: () -> Unit
) {

    val infiniteTransition = rememberInfiniteTransition(
        label = "scanAnimation"
    )

    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.35f,
        targetValue = 0.85f,
        animationSpec = infiniteRepeatable(
            animation = tween(700),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scanPulse"
    )

    Button(
        onClick = onClick,
        enabled = !isScanning,
        modifier = Modifier
            .fillMaxWidth()
            .height(if (isScanning) 86.dp else 58.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isScanning) {
                AutoPulseCyan.copy(alpha = 0.12f)
            } else {
                AutoPulseCyan
            },
            contentColor = if (isScanning) {
                AutoPulseCyan
            } else {
                AutoPulseBackground
            },
            disabledContainerColor = if (isScanning) {
                AutoPulseCyan.copy(alpha = 0.12f)
            } else {
                AutoPulseCyan
            }
        ),
        border = if (isScanning) {
            androidx.compose.foundation.BorderStroke(
                1.dp,
                AutoPulseCyan.copy(alpha = pulseAlpha)
            )
        } else {
            null
        }
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = if (isScanning) {
                        Icons.Default.Sync
                    } else {
                        Icons.Default.Search
                    },
                    contentDescription = null,
                    modifier = Modifier.size(22.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = if (isScanning) {
                        "SCANNING ECU..."
                    } else {
                        "FULL ECU SCAN"
                    },
                    fontWeight = FontWeight.Black,
                    fontSize = 14.sp
                )
            }

            if (isScanning) {

                Spacer(modifier = Modifier.height(8.dp))

                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp),
                    color = AutoPulseCyan,
                    trackColor = AutoPulseCyan.copy(alpha = 0.15f)
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = "${(progress * 100).toInt()}% • Communicating with ECU",
                    color = AutoPulseTextMuted,
                    fontSize = 9.sp
                )
            }
        }
    }
}


// ================================================================
// DIAGNOSTIC MENU CARD
// ================================================================

@Composable
private fun DiagnosticMenuCard(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(76.dp),
        colors = CardDefaults.cardColors(
            containerColor = AutoPulseSurface
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            color.copy(alpha = 0.25f)
        ),
        shape = MaterialTheme.shapes.medium
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(color.copy(alpha = 0.10f)),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(21.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    color = AutoPulseText,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = description,
                    color = AutoPulseTextMuted,
                    fontSize = 10.sp,
                    maxLines = 2
                )
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = AutoPulseTextMuted,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}


// ================================================================
// FAULT SUMMARY
// ================================================================

@Composable
fun FaultSummaryItem(
    label: String,
    count: String,
    color: Color,
    modifier: Modifier = Modifier
) {

    NeonCard(
        borderColor = color.copy(alpha = 0.35f),
        modifier = modifier
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = label,
                color = AutoPulseTextMuted,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = count,
                color = color,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black
            )
        }
    }
}

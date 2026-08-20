package com.example.autopulse_poe.ui.screens.diagnostics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun AdvancedDiagnosticsScreen(
    onBack: () -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    val tabs = listOf(
        "Emissions",
        "O2 Sensors",
        "Monitoring"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        // =========================================================
        // HEADER
        // =========================================================

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
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
                modifier = Modifier.padding(start = 8.dp)
            ) {

                Text(
                    text = "Advanced Diagnostics",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black
                )

                Text(
                    text = "ECU tests & live monitoring",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 10.sp
                )
            }
        }

        // =========================================================
        // TABS
        // =========================================================

        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = AutoPulseCyan,
            indicator = { positions ->

                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(
                        positions[selectedTab]
                    ),
                    color = AutoPulseCyan
                )
            },
            divider = {
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.outline
                )
            }
        ) {

            tabs.forEachIndexed { index, title ->

                Tab(
                    selected = selectedTab == index,
                    onClick = {
                        selectedTab = index
                    },
                    text = {

                        Text(
                            text = title,
                            color = if (selectedTab == index) {
                                AutoPulseCyan
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            },
                            fontWeight = if (selectedTab == index) {
                                FontWeight.Bold
                            } else {
                                FontWeight.Normal
                            },
                            fontSize = 12.sp
                        )
                    }
                )
            }
        }

        // =========================================================
        // CONTENT
        // =========================================================

        Box(
            modifier = Modifier.weight(1f)
        ) {

            when (selectedTab) {

                0 -> EmissionsView()

                1 -> OxygenSensorsView()

                2 -> MonitorView()
            }
        }
    }
}


// =================================================================
// EMISSIONS
// =================================================================

@Composable
fun EmissionsView() {

    val monitors = listOf(
        "Misfire" to true,
        "Fuel System" to true,
        "Components" to true,
        "Catalyst" to true,
        "EVAP System" to false,
        "Oxygen Sensor" to true,
        "O2 Heater" to true,
        "EGR System" to true
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        DiagnosticSectionHeader(
            title = "Emissions Readiness",
            subtitle = "OBD Mode 01 • Monitor status"
        )

        Spacer(modifier = Modifier.height(18.dp))

        // STATUS CARD

        NeonCard(
            borderColor = AutoPulseWarning.copy(alpha = 0.55f)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(
                            AutoPulseWarning.copy(alpha = 0.10f)
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        Icons.Default.Warning,
                        contentDescription = null,
                        tint = AutoPulseWarning,
                        modifier = Modifier.size(23.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "NOT READY",
                        color = AutoPulseWarning,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black
                    )

                    Text(
                        text = "Vehicle is not ready for inspection",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "1 monitor requires attention",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 10.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "READINESS MONITORS",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        NeonCard(
            borderColor = AutoPulseCyan.copy(alpha = 0.30f)
        ) {

            monitors.forEachIndexed { index, (name, ready) ->

                MonitorItem(
                    name = name,
                    ready = ready
                )

                if (index < monitors.lastIndex) {

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 11.dp),
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}


// =================================================================
// OXYGEN SENSORS
// =================================================================

@Composable
fun OxygenSensorsView() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        DiagnosticSectionHeader(
            title = "Oxygen Sensors",
            subtitle = "OBD Mode 05 • Sensor response"
        )

        Spacer(modifier = Modifier.height(18.dp))

        OxygenSensorCard(
            title = "Bank 1 Sensor 1",
            voltage = "0.450V – 0.900V",
            progress = 0.65f,
            status = "SWITCHING",
            color = AutoPulseMagenta
        )

        Spacer(modifier = Modifier.height(12.dp))

        OxygenSensorCard(
            title = "Bank 1 Sensor 2",
            voltage = "0.620V",
            progress = 0.48f,
            status = "STABLE",
            color = AutoPulseCyan
        )

        Spacer(modifier = Modifier.height(24.dp))

        NeonCard(
            borderColor = AutoPulseMagenta.copy(alpha = 0.25f)
        ) {

            Text(
                text = "Sensor interpretation",
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "AutoPulse monitors oxygen sensor voltage to identify abnormal fuel mixture behaviour and catalytic converter performance.",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 11.sp,
                lineHeight = 17.sp
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}


// =================================================================
// MONITORING
// =================================================================

@Composable
fun MonitorView() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        DiagnosticSectionHeader(
            title = "On-Board Monitoring",
            subtitle = "OBD Mode 06 • Component test results"
        )

        Spacer(modifier = Modifier.height(18.dp))

        Mode06Item(
            title = "Catalyst Monitor Bank 1",
            value = "0.45",
            limit = "0.60",
            passed = true
        )

        Mode06Item(
            title = "O2 Sensor Bank 1 Sensor 1",
            value = "1.20",
            limit = "1.50",
            passed = true
        )

        Mode06Item(
            title = "Evaporative System",
            value = "0.08",
            limit = "0.05",
            passed = false
        )

        Spacer(modifier = Modifier.height(20.dp))

        NeonCard(
            borderColor = AutoPulsePurple.copy(alpha = 0.30f)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    Icons.Default.Info,
                    contentDescription = null,
                    tint = AutoPulsePurple
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Mode 06 results are ECU self-test measurements.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 11.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}


// =================================================================
// SECTION HEADER
// =================================================================

@Composable
private fun DiagnosticSectionHeader(
    title: String,
    subtitle: String
) {

    Column {

        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 20.sp,
            fontWeight = FontWeight.Black
        )

        Text(
            text = subtitle,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 11.sp,
            modifier = Modifier.padding(top = 3.dp)
        )
    }
}


// =================================================================
// MONITOR ITEM
// =================================================================

@Composable
fun MonitorItem(
    name: String,
    ready: Boolean
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = if (ready) {
                Icons.Default.CheckCircle
            } else {
                Icons.Default.Cancel
            },
            contentDescription = null,
            tint = if (ready) {
                AutoPulseSuccess
            } else {
                AutoPulseError
            },
            modifier = Modifier.size(19.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = name,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 13.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = if (ready) "READY" else "NOT READY",
            color = if (ready) {
                AutoPulseSuccess
            } else {
                AutoPulseError
            },
            fontSize = 9.sp,
            fontWeight = FontWeight.Black
        )
    }
}


// =================================================================
// OXYGEN SENSOR CARD
// =================================================================

@Composable
private fun OxygenSensorCard(
    title: String,
    voltage: String,
    progress: Float,
    status: String,
    color: Color
) {

    NeonCard(
        borderColor = color.copy(alpha = 0.35f)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = voltage,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 11.sp
                )
            }

            Surface(
                color = color.copy(alpha = 0.10f),
                shape = MaterialTheme.shapes.small
            ) {

                Text(
                    text = status,
                    color = color,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier.padding(
                        horizontal = 8.dp,
                        vertical = 5.dp
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .clip(CircleShape),
            color = color,
            trackColor = AutoPulseSurfaceElevated
        )
    }
}


// =================================================================
// MODE 06
// =================================================================

@Composable
fun Mode06Item(
    title: String,
    value: String,
    limit: String,
    passed: Boolean
) {

    val statusColor = if (passed) {
        AutoPulseSuccess
    } else {
        AutoPulseError
    }

    NeonCard(
        borderColor = statusColor.copy(alpha = 0.30f),
        modifier = Modifier.padding(bottom = 12.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(30.dp)
                ) {

                    DiagnosticValue(
                        label = "VALUE",
                        value = value
                    )

                    DiagnosticValue(
                        label = "LIMIT",
                        value = limit
                    )
                }
            }

            Surface(
                color = statusColor.copy(alpha = 0.10f),
                shape = CircleShape
            ) {

                Text(
                    text = if (passed) "PASS" else "FAIL",
                    color = statusColor,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier.padding(
                        horizontal = 9.dp,
                        vertical = 6.dp
                    )
                )
            }
        }
    }
}


// =================================================================
// VALUE
// =================================================================

@Composable
private fun DiagnosticValue(
    label: String,
    value: String
) {

    Column {

        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = value,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 15.sp,
            fontWeight = FontWeight.Black
        )
    }
}
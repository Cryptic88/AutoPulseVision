package com.example.autopulse_poe.ui.screens.diagnostics

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
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
fun DtcListScreen(
    onBack: () -> Unit,
    onNavigateToDetail: () -> Unit
) {
    var selectedFilter by remember {
        mutableStateOf("All")
    }

    val mockDtcs = listOf(
        DtcItem(
            "P0300",
            "Random / Multiple Cylinder Misfire",
            AutoPulseError,
            "Stored"
        ),
        DtcItem(
            "P0171",
            "System Too Lean",
            AutoPulseWarning,
            "Pending"
        ),
        DtcItem(
            "P0442",
            "EVAP Leak Detected",
            AutoPulseWarning,
            "Pending"
        ),
        DtcItem(
            "P0420",
            "Catalyst Efficiency Below Threshold",
            AutoPulseTextMuted,
            "Permanent"
        )
    )

    val filteredDtcs = when (selectedFilter) {
        "Critical" -> mockDtcs.filter {
            it.status == "Stored"
        }

        "Pending" -> mockDtcs.filter {
            it.status == "Pending"
        }

        else -> mockDtcs
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AutoPulseBackground)
    ) {

        // HEADER
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
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
                    text = "Fault Codes",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = AutoPulseText
                )

                Text(
                    text = "${filteredDtcs.size} diagnostic codes",
                    color = AutoPulseTextMuted,
                    fontSize = 10.sp
                )
            }
        }

        // FILTERS
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 8.dp
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DiagnosticFilterChip(
                label = "All",
                selected = selectedFilter == "All"
            ) {
                selectedFilter = "All"
            }

            DiagnosticFilterChip(
                label = "Critical",
                selected = selectedFilter == "Critical"
            ) {
                selectedFilter = "Critical"
            }

            DiagnosticFilterChip(
                label = "Pending",
                selected = selectedFilter == "Pending"
            ) {
                selectedFilter = "Pending"
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredDtcs) { dtc ->

                NeonCard(
                    borderColor = dtc.color.copy(alpha = 0.4f),
                    modifier = Modifier.clickable {
                        onNavigateToDetail()
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .background(
                                    dtc.color.copy(alpha = 0.10f),
                                    MaterialTheme.shapes.small
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Warning,
                                contentDescription = null,
                                tint = dtc.color,
                                modifier = Modifier.size(21.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = dtc.code,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Black,
                                color = dtc.color
                            )

                            Text(
                                text = dtc.description,
                                color = AutoPulseText,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }

                        Surface(
                            color = dtc.color.copy(alpha = 0.10f),
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(
                                text = dtc.status.uppercase(),
                                color = dtc.color,
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Black,
                                modifier = Modifier.padding(
                                    horizontal = 7.dp,
                                    vertical = 5.dp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DiagnosticFilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        color = if (selected) {
            AutoPulseCyanDark.copy(alpha = 0.14f)
        } else {
            AutoPulseSurfaceElevated
        },
        shape = MaterialTheme.shapes.medium,
        border = if (selected) {
            androidx.compose.foundation.BorderStroke(
                1.dp,
                AutoPulseCyanDark
            )
        } else {
            null
        }
    ) {
        Text(
            text = label,
            color = if (selected) {
                AutoPulseCyanDark
            } else {
                AutoPulseTextSecondary
            },
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                horizontal = 15.dp,
                vertical = 8.dp
            )
        )
    }
}

data class DtcItem(
    val code: String,
    val description: String,
    val color: Color,
    val status: String
)
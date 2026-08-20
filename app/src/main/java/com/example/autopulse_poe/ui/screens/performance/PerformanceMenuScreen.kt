package com.example.autopulse_poe.ui.screens.performance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
fun PerformanceMenuScreen(
    onNavigateToMetrics: () -> Unit,
    onNavigateToAcceleration: () -> Unit,
    onNavigateToBraking: () -> Unit,
    onNavigateToCustomPids: () -> Unit,
    onNavigateToStock: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        // ----------------------------------------------------
        // HEADER
        // ----------------------------------------------------

        Text(
            text = "Performance",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Monitor and measure your vehicle's performance",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(28.dp))


        // ----------------------------------------------------
        // PERFORMANCE METRICS
        // ----------------------------------------------------

        PerformanceMenuCard(
            title = "Performance Metrics",
            subtitle = "Live horsepower, torque and engine data",
            icon = Icons.Default.BarChart,
            accent = AutoPulsePurple,
            onClick = onNavigateToMetrics
        )

        Spacer(modifier = Modifier.height(14.dp))


        // ----------------------------------------------------
        // ACCELERATION
        // ----------------------------------------------------

        PerformanceMenuCard(
            title = "Acceleration Timer",
            subtitle = "Measure 0–100 km/h and quarter mile times",
            icon = Icons.Default.Timer,
            accent = AutoPulseCyan,
            onClick = onNavigateToAcceleration
        )

        Spacer(modifier = Modifier.height(14.dp))


        // ----------------------------------------------------
        // BRAKING
        // ----------------------------------------------------

        PerformanceMenuCard(
            title = "Braking Distance",
            subtitle = "Measure stopping distance and deceleration",
            icon = Icons.Default.Warning,
            accent = AutoPulseWarning,
            onClick = onNavigateToBraking
        )

        Spacer(modifier = Modifier.height(14.dp))


        // ----------------------------------------------------
        // CUSTOM PIDS
        // ----------------------------------------------------

        PerformanceMenuCard(
            title = "Custom PIDs",
            subtitle = "Configure advanced ECU parameters",
            icon = Icons.Default.SettingsInputComponent,
            accent = AutoPulseBlue,
            onClick = onNavigateToCustomPids
        )

        Spacer(modifier = Modifier.height(14.dp))


        // ----------------------------------------------------
        // STOCK COMPARISON
        // ----------------------------------------------------

        PerformanceMenuCard(
            title = "Comparison to Stock",
            subtitle = "Compare your vehicle against factory specifications",
            icon = Icons.Default.CompareArrows,
            accent = AutoPulseSuccess,
            onClick = onNavigateToStock
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}


@Composable
private fun PerformanceMenuCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    accent: Color,
    onClick: () -> Unit
) {

    NeonCard(
        modifier = Modifier
            .fillMaxWidth(),
        borderColor = accent.copy(alpha = 0.45f)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 76.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // ------------------------------------------------
            // ICON CONTAINER
            // ------------------------------------------------

            Box(
                modifier = Modifier
                    .size(54.dp)
                    .background(
                        color = accent.copy(alpha = 0.12f),
                        shape = RoundedCornerShape(14.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = accent,
                    modifier = Modifier.size(27.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))


            // ------------------------------------------------
            // TEXT
            // ------------------------------------------------

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = subtitle,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )
            }

            Spacer(modifier = Modifier.width(8.dp))


            // ------------------------------------------------
            // ARROW
            // ------------------------------------------------

            IconButton(
                onClick = onClick
            ) {

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "Open $title",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
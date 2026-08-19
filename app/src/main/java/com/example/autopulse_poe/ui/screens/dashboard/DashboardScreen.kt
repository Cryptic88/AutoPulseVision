package com.example.autopulse_poe.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.Gauge
import com.example.autopulse_poe.ui.theme.AutoPulseBackground
import com.example.autopulse_poe.ui.theme.AutoPulseBorder
import com.example.autopulse_poe.ui.theme.AutoPulseBorderStrong
import com.example.autopulse_poe.ui.theme.AutoPulseCyan
import com.example.autopulse_poe.ui.theme.AutoPulseError
import com.example.autopulse_poe.ui.theme.AutoPulsePurple
import com.example.autopulse_poe.ui.theme.AutoPulseSuccess
import com.example.autopulse_poe.ui.theme.AutoPulseSurface
import com.example.autopulse_poe.ui.theme.AutoPulseSurfaceElevated
import com.example.autopulse_poe.ui.theme.AutoPulseText
import com.example.autopulse_poe.ui.theme.AutoPulseTextMuted
import com.example.autopulse_poe.ui.theme.AutoPulseTextSecondary
import com.example.autopulse_poe.ui.theme.AutoPulseWarning

@Composable
fun DashboardScreen(
    onNavigateToHUD: () -> Unit = {},
    onNavigateToAi: () -> Unit = {},
    onNavigateToDiagnostics: () -> Unit = {},
    onNavigateToTrips: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onNavigateToAchievements: () -> Unit = {},
    onNavigateToPerformance: () -> Unit = {},
    onNavigateToFuel: () -> Unit = {},
    onNavigateToMaintenance: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(AutoPulseBackground)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        // ----------------------------------------------------
        // HEADER
        // ----------------------------------------------------

        item {
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(AutoPulseSurfaceElevated),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.DirectionsCar,
                            contentDescription = "AutoPulse",
                            tint = AutoPulseCyan,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "AutoPulse",
                            color = AutoPulseText,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "VEHICLE TELEMETRY",
                            color = AutoPulseTextMuted,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.2.sp
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // Connection indicator
                    Row(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(
                                AutoPulseSuccess.copy(alpha = 0.10f)
                            )
                            .padding(
                                horizontal = 10.dp,
                                vertical = 6.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(7.dp)
                                .clip(CircleShape)
                                .background(AutoPulseSuccess)
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = "CONNECTED",
                            color = AutoPulseSuccess,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    IconButton(
                        onClick = onNavigateToSettings
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = AutoPulseTextSecondary
                        )
                    }
                }
            }
        }


        // ----------------------------------------------------
        // WELCOME
        // ----------------------------------------------------

        item {
            Column {
                Text(
                    text = "Welcome back, Alex",
                    color = AutoPulseText,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Your vehicle is running smoothly.",
                    color = AutoPulseTextSecondary,
                    fontSize = 14.sp
                )
            }
        }

        // ----------------------------------------------------
        // HUD
        // ----------------------------------------------------

        item {

            AutoPulseOutlineButton(
                modifier = Modifier.fillMaxWidth(),
                text = "OPEN LIVE HUD",
                icon = Icons.Default.Visibility,
                onClick = onNavigateToHUD
            )

            Spacer(modifier = Modifier.height(20.dp))
        }

        // ----------------------------------------------------
        // QUICK ACTIONS
        // ----------------------------------------------------

        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = "QUICK ACTIONS",
                    color = AutoPulseTextMuted,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    AutoPulseQuickAction(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.Navigation,
                        title = "Start Trip",
                        subtitle = "Track journey",
                        accent = AutoPulseCyan,
                        onClick = {}
                    )


                    AutoPulseQuickAction(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.Build,
                        title = "Diagnostics",
                        subtitle = "Scan vehicle",
                        accent = AutoPulseWarning,
                        onClick = onNavigateToDiagnostics
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    AutoPulseQuickAction(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.Speed,
                        title = "Performance",
                        subtitle = "Live metrics",
                        accent = Color(0xFF3B82F6),
                        onClick = onNavigateToPerformance
                    )

                    AutoPulseQuickAction(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.History,
                        title = "Trip History",
                        subtitle = "Past drives",
                        accent = AutoPulsePurple,
                        onClick = onNavigateToTrips
                    )
                }
            }
        }

        // ----------------------------------------------------
        // CURRENT TRIP
        // ----------------------------------------------------

        item {
            Column {
                SectionTitle("CURRENT TRIP")

                Spacer(modifier = Modifier.height(10.dp))

                AutoPulseTripCard(
                    duration = "00:32:15",
                    distance = "12.5 km",
                    averageSpeed = "45 km/h"
                )
            }
        }

        // ----------------------------------------------------
        // LIVE TELEMETRY
        // ----------------------------------------------------

        /*item {
            Column {

                SectionTitle("LIVE TELEMETRY")

                Spacer(modifier = Modifier.height(10.dp))

                // ---------------------------------------------
                // HERO RPM CARD
                // ---------------------------------------------

                AutoPulseRpmCard()

                Spacer(modifier = Modifier.height(12.dp))

                // ---------------------------------------------
                // SUPPORTING METRICS
                // ---------------------------------------------

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    AutoPulseMetricCard(
                        modifier = Modifier.weight(1f),
                        label = "COOLANT",
                        value = "89",
                        unit = "°C",
                        accent = AutoPulseSuccess
                    )

                    AutoPulseMetricCard(
                        modifier = Modifier.weight(1f),
                        label = "THROTTLE",
                        value = "42",
                        unit = "%",
                        accent = Color(0xFF3B82F6)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    AutoPulseMetricCard(
                        modifier = Modifier.weight(1f),
                        label = "ENGINE LOAD",
                        value = "58",
                        unit = "%",
                        accent = AutoPulseWarning
                    )

                    AutoPulseMetricCard(
                        modifier = Modifier.weight(1f),
                        label = "SPEED",
                        value = "65",
                        unit = "km/h",
                        accent = AutoPulseCyan
                    )
                }
            }
        }*/

        // ----------------------------------------------------
        // AI MECHANIC
        // ----------------------------------------------------

        item {
            AutoPulseAiCard(
                onClick = onNavigateToAi
            )
        }

        // ----------------------------------------------------
        // FUEL
        // ----------------------------------------------------

        item {
            AutoPulseFuelCard(
                mpg = "32",
                fuelLevel = "78%",
                onClick = onNavigateToFuel
            )
        }

        // ----------------------------------------------------
        // MAINTENANCE + SAFETY
        // ----------------------------------------------------

        item {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                AutoPulseMaintenanceCard(
                    modifier = Modifier.weight(1.2f),
                    onClick = onNavigateToMaintenance
                )

                AutoPulseSafetyCard(
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateToAchievements
                )
            }
        }
    }
}


// ============================================================
// SECTION TITLE
// ============================================================

@Composable
private fun SectionTitle(
    text: String
) {
    Text(
        text = text,
        color = AutoPulseTextMuted,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.2.sp
    )
}


// ============================================================
// QUICK ACTION
// ============================================================

@Composable
private fun AutoPulseQuickAction(
    modifier: Modifier = Modifier,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    accent: Color,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(accent.copy(alpha = 0.075f))
            .border(
                width = 1.dp,
                color = accent.copy(alpha = 0.30f),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {

        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(accent.copy(alpha = 0.14f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = accent,
                modifier = Modifier.size(21.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = title,
            color = AutoPulseText,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = subtitle,
            color = AutoPulseTextSecondary,
            fontSize = 11.sp
        )
    }
}

// ============================================================
// TRIP CARD
// ============================================================

@Composable
private fun AutoPulseTripCard(
    duration: String,
    distance: String,
    averageSpeed: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(AutoPulseSurface)
            .border(
                width = 1.dp,
                color = AutoPulseCyan.copy(alpha = 0.22f),
                shape = RoundedCornerShape(18.dp)
            )
            .padding(18.dp)
    ) {

        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(AutoPulseSuccess)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "RECORDING",
                    color = AutoPulseSuccess,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.8.sp
                )
            }

            Text(
                text = duration,
                color = AutoPulseText,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Main distance
        Text(
            text = distance,
            color = AutoPulseText,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "DISTANCE",
            color = AutoPulseTextMuted,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Trip progress line
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .clip(CircleShape)
                .background(AutoPulseSurfaceElevated)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.55f)
                    .fillMaxHeight()
                    .clip(CircleShape)
                    .background(AutoPulseCyan)
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            TripMetric(
                modifier = Modifier.weight(1f),
                label = "AVG. SPEED",
                value = averageSpeed,
                accent = AutoPulseCyan
            )

            TripMetric(
                modifier = Modifier.weight(1f),
                label = "MAX SPEED",
                value = "72 km/h",
                accent = AutoPulsePurple
            )

            TripMetric(
                modifier = Modifier.weight(1f),
                label = "TRIP TIME",
                value = duration,
                accent = AutoPulseSuccess
            )
        }
    }
}

@Composable
private fun TripMetric(
    modifier: Modifier,
    label: String,
    value: String,
    accent: Color
) {
    Column(
        modifier = modifier
    ) {

        Box(
            modifier = Modifier
                .width(3.dp)
                .height(14.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(accent)
        )

        Spacer(modifier = Modifier.height(7.dp))

        Text(
            text = value,
            color = AutoPulseText,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = label,
            color = AutoPulseTextMuted,
            fontSize = 8.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
        )
    }
}

@Composable
private fun TripValue(
    modifier: Modifier,
    value: String,
    label: String
) {
    Column(modifier = modifier) {

        Text(
            text = value,
            color = AutoPulseText,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = label,
            color = AutoPulseTextMuted,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.8.sp
        )
    }
}


// ============================================================
// TELEMETRY METRIC
// ============================================================

@Composable
private fun AutoPulseMetricCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    unit: String,
    accent: Color
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(AutoPulseSurface)
            .border(
                width = 1.dp,
                color = AutoPulseBorder,
                shape = RoundedCornerShape(14.dp)
            )
            .padding(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(accent)
            )

            Spacer(modifier = Modifier.width(7.dp))

            Text(
                text = label,
                color = AutoPulseTextSecondary,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.Bottom
        ) {

            Text(
                text = value,
                color = AutoPulseText,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = unit,
                color = AutoPulseTextMuted,
                fontSize = 10.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    }
}


// ============================================================
// AI MECHANIC
// ============================================================

@Composable
private fun AutoPulseAiCard(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(AutoPulseSurface)
            .border(
                width = 1.dp,
                color = AutoPulsePurple.copy(alpha = 0.35f),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(AutoPulsePurple.copy(alpha = 0.10f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.AutoAwesome,
                contentDescription = null,
                tint = AutoPulsePurple,
                modifier = Modifier.size(21.dp)
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = "AI Mechanic",
                color = AutoPulseText,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = "Ask me about your vehicle",
                color = AutoPulseTextSecondary,
                fontSize = 11.sp
            )
        }

        Text(
            text = "›",
            color = AutoPulsePurple,
            fontSize = 28.sp,
            fontWeight = FontWeight.Light
        )
    }
}


// ============================================================
// FUEL
// ============================================================

@Composable
private fun AutoPulseFuelCard(
    mpg: String,
    fuelLevel: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(AutoPulseSurface)
            .border(
                width = 1.dp,
                color = AutoPulseCyan.copy(alpha = 0.25f),
                shape = RoundedCornerShape(18.dp)
            )
            .clickable(onClick = onClick)
            .padding(18.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(
                    text = "FUEL EFFICIENCY",
                    color = AutoPulseText,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.8.sp
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = "Current driving efficiency",
                    color = AutoPulseTextSecondary,
                    fontSize = 11.sp
                )
            }

            Text(
                text = "VIEW",
                color = AutoPulseCyan,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Existing gauge component
            Gauge(
                value = 32f,
                maxValue = 50f,
                label = "",
                unit = "",
                modifier = Modifier.size(125.dp),
                color = AutoPulseSuccess
            )

            Spacer(modifier = Modifier.width(22.dp))

            Column {

                Row(
                    verticalAlignment = Alignment.Bottom
                ) {

                    Text(
                        text = mpg,
                        color = AutoPulseSuccess,
                        fontSize = 44.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = "MPG",
                        color = AutoPulseTextSecondary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 7.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Fuel Level",
                    color = AutoPulseTextMuted,
                    fontSize = 10.sp
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = fuelLevel,
                    color = AutoPulseCyan,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}


// ============================================================
// MAINTENANCE
// ============================================================

@Composable
private fun AutoPulseMaintenanceCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(AutoPulseSurface)
            .border(
                width = 1.dp,
                color = AutoPulseError.copy(alpha = 0.30f),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {

        Text(
            text = "MAINTENANCE",
            color = AutoPulseTextSecondary,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.8.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.Build,
                contentDescription = null,
                tint = AutoPulseError,
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Oil change",
                color = AutoPulseText,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Overdue by 250 mi",
            color = AutoPulseError,
            fontSize = 10.sp
        )
    }
}


// ============================================================
// SAFETY
// ============================================================

@Composable
private fun AutoPulseSafetyCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(AutoPulseSurface)
            .border(
                width = 1.dp,
                color = AutoPulseBorder,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {

        Text(
            text = "SAFETY SCORE",
            color = AutoPulseTextSecondary,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.8.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            verticalAlignment = Alignment.Bottom
        ) {

            Text(
                text = "88",
                color = AutoPulseSuccess,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = "/ 100",
                color = AutoPulseTextMuted,
                fontSize = 11.sp,
                modifier = Modifier.padding(bottom = 5.dp)
            )
        }

        Text(
            text = "GOOD",
            color = AutoPulseSuccess,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


// ============================================================
// OUTLINE BUTTON
// ============================================================

@Composable
private fun AutoPulseOutlineButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(AutoPulseSurface)
            .border(
                width = 1.dp,
                color = AutoPulseBorderStrong,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
            .padding(vertical = 14.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = AutoPulseCyan,
            modifier = Modifier.size(18.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            color = AutoPulseCyan,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.8.sp
        )
    }
}

//RPM Card
@Composable
private fun AutoPulseRpmCard() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(
                AutoPulseCyan.copy(alpha = 0.045f)
            )
            .border(
                width = 1.dp,
                color = AutoPulseCyan.copy(alpha = 0.25f),
                shape = RoundedCornerShape(18.dp)
            )
            .padding(20.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "ENGINE RPM",
                color = AutoPulseTextSecondary,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(AutoPulseSuccess)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "LIVE",
                    color = AutoPulseSuccess,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            verticalAlignment = Alignment.Bottom
        ) {

            Text(
                text = "4,250",
                color = AutoPulseText,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "RPM",
                color = AutoPulseCyan,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // RPM range
        Column {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(CircleShape)
                    .background(AutoPulseSurfaceElevated)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.61f)
                        .fillMaxHeight()
                        .clip(CircleShape)
                        .background(AutoPulseCyan)
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "0",
                    color = AutoPulseTextMuted,
                    fontSize = 8.sp
                )

                Text(
                    text = "3,000",
                    color = AutoPulseTextMuted,
                    fontSize = 8.sp
                )

                Text(
                    text = "5,000",
                    color = AutoPulseTextMuted,
                    fontSize = 8.sp
                )

                Text(
                    text = "7,000 RPM",
                    color = AutoPulseTextMuted,
                    fontSize = 8.sp
                )
            }
        }
    }
}
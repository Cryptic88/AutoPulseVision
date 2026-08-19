package com.example.autopulse_poe.ui.screens.hud

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Tune
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
fun HudCustomizationScreen(
    onBack: () -> Unit
) {
    var mirrorImage by remember { mutableStateOf(true) }
    var highContrast by remember { mutableStateOf(true) }
    var showRpm by remember { mutableStateOf(true) }
    var showTemperature by remember { mutableStateOf(true) }
    var showGear by remember { mutableStateOf(true) }
    var showTripInfo by remember { mutableStateOf(false) }

    var speedWarning by remember { mutableStateOf(true) }
    var rpmWarning by remember { mutableStateOf(false) }

    var speedLimit by remember { mutableFloatStateOf(120f) }
    var shiftRpm by remember { mutableFloatStateOf(5500f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AutoPulseBackground)
            .verticalScroll(rememberScrollState())
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
                    text = "HUD Settings",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = AutoPulseText
                )

                Text(
                    text = "Customize your driving display",
                    color = AutoPulseTextMuted,
                    fontSize = 10.sp
                )
            }
        }

        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {

            // HUD PREVIEW
            Text(
                text = "LIVE PREVIEW",
                color = AutoPulseTextSecondary,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            NeonCard(
                borderColor = AutoPulseMagenta.copy(alpha = 0.55f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp)
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "85",
                            color = AutoPulseText,
                            fontSize = 72.sp,
                            fontWeight = FontWeight.Black
                        )

                        Text(
                            text = "km/h",
                            color = AutoPulseMagenta,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(28.dp)
                        ) {

                            if (showRpm) {
                                HudPreviewStat(
                                    label = "RPM",
                                    value = "2450"
                                )
                            }

                            if (showTemperature) {
                                HudPreviewStat(
                                    label = "TEMP",
                                    value = "92°C"
                                )
                            }

                            if (showGear) {
                                HudPreviewStat(
                                    label = "GEAR",
                                    value = "D"
                                )
                            }
                        }
                    }

                    // Connection indicator
                    Row(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(7.dp)
                                .background(
                                    AutoPulseSuccess,
                                    MaterialTheme.shapes.small
                                )
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "CONNECTED",
                            color = AutoPulseSuccess,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // DISPLAY OPTIONS
            Text(
                text = "DISPLAY OPTIONS",
                color = AutoPulseCyan,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            NeonCard(
                borderColor = AutoPulseCyan.copy(alpha = 0.4f)
            ) {

                HudSwitchRow(
                    label = "Mirror Image",
                    description = "Flip display for windshield projection",
                    checked = mirrorImage,
                    onCheckedChange = { mirrorImage = it }
                )

                HorizontalDivider(
                    color = AutoPulseText.copy(alpha = 0.05f),
                    modifier = Modifier.padding(vertical = 10.dp)
                )

                HudSwitchRow(
                    label = "High Contrast Mode",
                    description = "Improve visibility in bright conditions",
                    checked = highContrast,
                    onCheckedChange = { highContrast = it }
                )

                HorizontalDivider(
                    color = AutoPulseText.copy(alpha = 0.05f),
                    modifier = Modifier.padding(vertical = 10.dp)
                )

                HudSwitchRow(
                    label = "Show RPM",
                    description = "Display engine revolutions",
                    checked = showRpm,
                    onCheckedChange = { showRpm = it }
                )

                HorizontalDivider(
                    color = AutoPulseText.copy(alpha = 0.05f),
                    modifier = Modifier.padding(vertical = 10.dp)
                )

                HudSwitchRow(
                    label = "Show Temperature",
                    description = "Display coolant temperature",
                    checked = showTemperature,
                    onCheckedChange = { showTemperature = it }
                )

                HorizontalDivider(
                    color = AutoPulseText.copy(alpha = 0.05f),
                    modifier = Modifier.padding(vertical = 10.dp)
                )

                HudSwitchRow(
                    label = "Show Gear",
                    description = "Display current transmission gear",
                    checked = showGear,
                    onCheckedChange = { showGear = it }
                )

                HorizontalDivider(
                    color = AutoPulseText.copy(alpha = 0.05f),
                    modifier = Modifier.padding(vertical = 10.dp)
                )

                HudSwitchRow(
                    label = "Trip Information",
                    description = "Show distance and trip statistics",
                    checked = showTripInfo,
                    onCheckedChange = { showTripInfo = it }
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ALERTS
            Text(
                text = "ALERTS & WARNINGS",
                color = AutoPulseError,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            NeonCard(
                borderColor = AutoPulseError.copy(alpha = 0.45f)
            ) {

                HudSwitchRow(
                    label = "Speeding Alert",
                    description = "Warn when your configured speed is exceeded",
                    checked = speedWarning,
                    onCheckedChange = { speedWarning = it },
                    accentColor = AutoPulseError
                )

                if (speedWarning) {

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Speed Warning: ${speedLimit.toInt()} km/h",
                        color = AutoPulseText,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Slider(
                        value = speedLimit,
                        onValueChange = { speedLimit = it },
                        valueRange = 60f..180f,
                        steps = 11,
                        colors = SliderDefaults.colors(
                            thumbColor = AutoPulseError,
                            activeTrackColor = AutoPulseError
                        )
                    )
                }

                HorizontalDivider(
                    color = AutoPulseText.copy(alpha = 0.05f),
                    modifier = Modifier.padding(vertical = 12.dp)
                )

                HudSwitchRow(
                    label = "Shift Point Warning",
                    description = "Warn when engine RPM reaches shift point",
                    checked = rpmWarning,
                    onCheckedChange = { rpmWarning = it },
                    accentColor = AutoPulseWarning
                )

                if (rpmWarning) {

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Shift RPM: ${shiftRpm.toInt()} RPM",
                        color = AutoPulseText,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Slider(
                        value = shiftRpm,
                        onValueChange = { shiftRpm = it },
                        valueRange = 3000f..7000f,
                        steps = 7,
                        colors = SliderDefaults.colors(
                            thumbColor = AutoPulseWarning,
                            activeTrackColor = AutoPulseWarning
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // SAFETY INFORMATION
            NeonCard(
                borderColor = AutoPulseWarning.copy(alpha = 0.3f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Warning,
                        contentDescription = null,
                        tint = AutoPulseWarning
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "HUD Safety",
                            color = AutoPulseText,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Keep your attention on the road. HUD information is provided for assistance and should not replace safe driving.",
                            color = AutoPulseTextMuted,
                            fontSize = 11.sp,
                            lineHeight = 16.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun HudSwitchRow(
    label: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    accentColor: Color = AutoPulseCyan
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = label,
                color = AutoPulseText,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = description,
                color = AutoPulseTextMuted,
                fontSize = 10.sp,
                modifier = Modifier.padding(top = 3.dp)
            )
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = accentColor,
                uncheckedThumbColor = AutoPulseTextMuted,
                uncheckedTrackColor = AutoPulseSurfaceElevated
            )
        )
    }
}

@Composable
private fun HudPreviewStat(
    label: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            color = AutoPulseTextMuted,
            fontSize = 8.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = value,
            color = AutoPulseText,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

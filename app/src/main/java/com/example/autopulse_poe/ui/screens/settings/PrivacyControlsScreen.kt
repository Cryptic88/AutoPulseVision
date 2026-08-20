package com.example.autopulse_poe.ui.screens.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
fun PrivacyControlsScreen(
    onBack: () -> Unit
) {

    var analyticsEnabled by remember { mutableStateOf(true) }
    var locationSharing by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        // ------------------------------------------------
        // HEADER
        // ------------------------------------------------

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 12.dp,
                    end = 20.dp,
                    top = 12.dp,
                    bottom = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onBack
            ) {
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
                    text = "Privacy Controls",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Manage your data and privacy",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp
                )
            }
        }

        // ------------------------------------------------
        // CONTENT
        // ------------------------------------------------

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            // ------------------------------------------------
            // PRIVACY
            // ------------------------------------------------

            Text(
                text = "PRIVACY",
                color = AutoPulseCyan.copy(alpha = 0.75f),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            NeonCard(
                borderColor = AutoPulseCyan.copy(alpha = 0.45f)
            ) {

                PrivacyOption(
                    title = "Anonymous Analytics",
                    description = "Help improve AutoPulse using anonymous usage data.",
                    checked = analyticsEnabled,
                    accent = AutoPulseCyan,
                    onCheckedChange = {
                        analyticsEnabled = it
                    }
                )

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 16.dp),
                    color = MaterialTheme.colorScheme.outline
                )

                PrivacyOption(
                    title = "Route History",
                    description = "Store previous journeys for trip analysis and reports.",
                    checked = locationSharing,
                    accent = AutoPulseCyan,
                    onCheckedChange = {
                        locationSharing = it
                    }
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ------------------------------------------------
            // DATA MANAGEMENT
            // ------------------------------------------------

            Text(
                text = "DATA MANAGEMENT",
                color = AutoPulseCyanDark.copy(alpha = 0.75f),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            NeonCard(
                borderColor = AutoPulseCyanDark.copy(alpha = 0.45f)
            ) {

                Text(
                    text = "Your Data",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Manage stored diagnostic data, trip history and vehicle information.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 10.sp,
                    lineHeight = 14.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(
                    onClick = { /* Export data */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = AutoPulseCyan
                    ),
                    border = BorderStroke(
                        width = 1.dp,
                        color = AutoPulseCyan.copy(alpha = 0.6f)
                    )
                ) {
                    Text(
                        text = "Export My Data",
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedButton(
                    onClick = { /* Clear history */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = AutoPulseError
                    ),
                    border = BorderStroke(
                        width = 1.dp,
                        color = AutoPulseError.copy(alpha = 0.6f)
                    )
                ) {
                    Text(
                        text = "Clear Trip History",
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // ------------------------------------------------
            // PRIVACY INFORMATION
            // ------------------------------------------------

            NeonCard(
                borderColor = AutoPulsePurple.copy(alpha = 0.35f)
            ) {

                Text(
                    text = "Privacy Information",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "AutoPulse only uses vehicle and trip information required to provide diagnostics, performance analysis and reporting features.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 10.sp,
                    lineHeight = 15.sp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}


// ------------------------------------------------------------
// PRIVACY OPTION
// ------------------------------------------------------------

@Composable
private fun PrivacyOption(
    title: String,
    description: String,
    checked: Boolean,
    accent: Color,
    onCheckedChange: (Boolean) -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
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

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = description,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 10.sp,
                lineHeight = 14.sp
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                // ON
                checkedThumbColor = MaterialTheme.colorScheme.surface,
                checkedTrackColor = accent.copy(alpha = 0.45f),
                checkedBorderColor = accent,

                // OFF
                uncheckedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant,
                uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant,
                uncheckedBorderColor = MaterialTheme.colorScheme.outline,

                // Disabled states
                disabledCheckedThumbColor = MaterialTheme.colorScheme.outline,
                disabledCheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant,
                disabledUncheckedThumbColor = MaterialTheme.colorScheme.outline,
                disabledUncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        )
    }
}
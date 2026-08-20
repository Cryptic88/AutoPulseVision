package com.example.autopulse_poe.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun SettingsScreen(
    darkMode: Boolean = true,
    onDarkModeChanged: (Boolean) -> Unit = {},
    onNavigateToVehicleEditor: () -> Unit = {},
    onNavigateToBluetooth: () -> Unit = {},
    onNavigateToVehicleProfiles: () -> Unit = {},
    onNavigateToEditProfile: () -> Unit = {},
    onNavigateToPrivacy: () -> Unit = {},
    onNavigateToBackup: () -> Unit = {}
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
            text = "Settings",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 28.sp,
            fontWeight = FontWeight.Black
        )

        Text(
            text = "Manage your AutoPulse experience",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))


        // ----------------------------------------------------
        // PROFILE
        // ----------------------------------------------------

        NeonCard(
            borderColor = AutoPulsePurple.copy(alpha = 0.55f),
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onNavigateToEditProfile)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(62.dp)
                        .clip(CircleShape)
                        .background(
                            AutoPulsePurple.copy(alpha = 0.14f)
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = AutoPulsePurple,
                        modifier = Modifier.size(34.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Alex Smith",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Manage your profile",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(top = 3.dp)
                    )
                }

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }


        Spacer(modifier = Modifier.height(28.dp))


        // ----------------------------------------------------
        // VEHICLE
        // ----------------------------------------------------

        SettingsSectionTitle(
            title = "VEHICLE",
            color = AutoPulseCyan
        )

        Spacer(modifier = Modifier.height(8.dp))

        NeonSettingsSection(
            title = "",
            borderColor = AutoPulseCyan
        ) {

            SettingsItem(
                icon = Icons.Default.DirectionsCar,
                label = "Vehicle Profiles",
                description = "Manage your saved vehicles",
                onClick = onNavigateToVehicleProfiles
            )

            SettingsItem(
                icon = Icons.Default.Edit,
                label = "Active Vehicle",
                description = "Tesla Model S • 2024",
                onClick = onNavigateToVehicleEditor
            )

            SettingsItem(
                icon = Icons.Default.Bluetooth,
                label = "OBD-II Adapter",
                description = "Connected • OBDLink MX+",
                onClick = onNavigateToBluetooth
            )
        }


        Spacer(modifier = Modifier.height(20.dp))


        // ----------------------------------------------------
        // APP PREFERENCES
        // ----------------------------------------------------

        SettingsSectionTitle(
            title = "APP PREFERENCES",
            color = AutoPulseCyan
        )

        Spacer(modifier = Modifier.height(8.dp))

        NeonSettingsSection(
            title = "",
            borderColor = AutoPulseCyan
        ) {

            SettingsToggleItem(
                icon = Icons.Default.Palette,
                label = "Dark Mode",
                description = if (darkMode) {
                    "Dark theme enabled"
                } else {
                    "Light theme enabled"
                },
                checked = darkMode,
                onCheckedChange = onDarkModeChanged
            )

            SettingsItem(
                icon = Icons.Default.Language,
                label = "Language",
                description = "English"
            )

            SettingsItem(
                icon = Icons.Default.Notifications,
                label = "Notifications",
                description = "Alerts and vehicle warnings"
            )
        }


        Spacer(modifier = Modifier.height(20.dp))


        // ----------------------------------------------------
        // DATA & PRIVACY
        // ----------------------------------------------------

        SettingsSectionTitle(
            title = "DATA & PRIVACY",
            color = AutoPulseWarning
        )

        Spacer(modifier = Modifier.height(8.dp))

        NeonSettingsSection(
            title = "",
            borderColor = AutoPulseWarning
        ) {

            SettingsItem(
                icon = Icons.Default.PrivacyTip,
                label = "Privacy Controls",
                description = "Manage data collection and sharing",
                onClick = onNavigateToPrivacy
            )

            SettingsItem(
                icon = Icons.Default.CloudSync,
                label = "Backup & Restore",
                description = "Sync your vehicle data",
                onClick = onNavigateToBackup
            )
        }


        Spacer(modifier = Modifier.height(20.dp))


        // ----------------------------------------------------
        // SECURITY
        // ----------------------------------------------------

        SettingsSectionTitle(
            title = "SECURITY",
            color = AutoPulsePurple
        )

        Spacer(modifier = Modifier.height(8.dp))

        NeonSettingsSection(
            title = "",
            borderColor = AutoPulsePurple
        ) {

            SettingsItem(
                icon = Icons.Default.Fingerprint,
                label = "Biometric Access",
                description = "Use fingerprint or face authentication"
            )

            SettingsItem(
                icon = Icons.Default.Lock,
                label = "App Security",
                description = "Authentication and account security"
            )
        }


        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = "AUTOPULSE",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            text = "Version 1.0.0",
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
            fontSize = 9.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 3.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}


// ------------------------------------------------------------
// SECTION TITLE
// ------------------------------------------------------------

@Composable
fun SettingsSectionTitle(
    title: String,
    color: Color
) {
    Text(
        text = title,
        color = color.copy(alpha = 0.75f),
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
    )
}


// ------------------------------------------------------------
// SETTINGS SECTION
// ------------------------------------------------------------

@Composable
fun NeonSettingsSection(
    title: String,
    borderColor: Color,
    content: @Composable ColumnScope.() -> Unit
) {

    Column {

        if (title.isNotEmpty()) {
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = borderColor.copy(alpha = 0.7f),
                modifier = Modifier.padding(
                    start = 4.dp,
                    bottom = 8.dp
                )
            )
        }

        NeonCard(
            borderColor = borderColor.copy(alpha = 0.45f)
        ) {
            content()
        }
    }
}


// ------------------------------------------------------------
// SETTINGS ITEM
// ------------------------------------------------------------

@Composable
fun SettingsItem(
    icon: ImageVector,
    label: String,
    description: String = "",
    onClick: () -> Unit = {}
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    MaterialTheme.colorScheme.surfaceVariant,
                    RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(19.dp)
            )
        }

        Spacer(modifier = Modifier.width(13.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = label,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            if (description.isNotEmpty()) {

                Text(
                    text = description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(18.dp)
        )
    }
}


// ------------------------------------------------------------
// SETTINGS TOGGLE ITEM
// ------------------------------------------------------------

@Composable
fun SettingsToggleItem(
    icon: ImageVector,
    label: String,
    description: String = "",
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onCheckedChange(!checked)
            }
            .padding(vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    MaterialTheme.colorScheme.surfaceVariant,
                    RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(19.dp)
            )
        }

        Spacer(modifier = Modifier.width(13.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = label,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            if (description.isNotEmpty()) {

                Text(
                    text = description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = AutoPulseCyan,
                checkedTrackColor = AutoPulseCyan.copy(alpha = 0.4f),
                uncheckedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant,
                uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant,
                uncheckedBorderColor = MaterialTheme.colorScheme.outline
            )
        )
    }
}
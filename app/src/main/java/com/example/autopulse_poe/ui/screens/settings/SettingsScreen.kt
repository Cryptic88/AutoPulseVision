package com.example.autopulse_poe.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
    onNavigateToVehicleEditor: () -> Unit = {},
    onNavigateToBluetooth: () -> Unit = {},
    onNavigateToVehicleProfiles: () -> Unit = {},
    onNavigateToEditProfile: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(NeonPurple.copy(alpha = 0.2f))
                    .padding(4.dp)
            ) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    tint = NeonPurple,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(text = "Alex Smith", fontSize = 24.sp, fontWeight = FontWeight.Black, color = Color.White)
                Text(text = "Premium Member", color = NeonCyan, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = onNavigateToEditProfile) {
                Icon(Icons.Default.Edit, contentDescription = null, tint = Color.LightGray)
            }
        }

        Text(
            text = "User Profile",
            fontSize = 28.sp,
            fontWeight = FontWeight.Black,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        NeonSettingsSection(title = "Vehicle Management", borderColor = NeonCyan) {
            SettingsItem(icon = Icons.Default.DirectionsCar, label = "Vehicle Profiles", onClick = onNavigateToVehicleProfiles)
            SettingsItem(icon = Icons.Default.Bluetooth, label = "OBD-II Adapter Setup", onClick = onNavigateToBluetooth)
            SettingsItem(icon = Icons.Default.Edit, label = "Last Active Vehicle", onClick = onNavigateToVehicleEditor)
        }

        Spacer(modifier = Modifier.height(16.dp))

        NeonSettingsSection(title = "Preferences", borderColor = NeonMagenta) {
            SettingsItem(icon = Icons.Default.Palette, label = "App Theme")
            SettingsItem(icon = Icons.Default.Language, label = "Language Settings")
            SettingsItem(icon = Icons.Default.Notifications, label = "Notification Alerts")
        }

        Spacer(modifier = Modifier.height(16.dp))

        NeonSettingsSection(title = "Security", borderColor = NeonOrange) {
            SettingsItem(icon = Icons.Default.Fingerprint, label = "Biometric Access")
            SettingsItem(icon = Icons.Default.PrivacyTip, label = "Privacy & Data")
        }
        
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun NeonSettingsSection(title: String, borderColor: Color, content: @Composable ColumnScope.() -> Unit) {
    Column {
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = borderColor.copy(alpha = 0.7f),
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
        )
        NeonCard(borderColor = borderColor) {
            content()
        }
    }
}

@Composable
fun SettingsItem(icon: ImageVector, label: String, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = Color.White.copy(alpha = 0.8f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            fontSize = 15.sp,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )
        Icon(
            Icons.Default.ChevronRight,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Color.White.copy(alpha = 0.3f)
        )
    }
}

package com.example.autopulse_poe.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
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
fun BackupRestoreScreen(
    onBack: () -> Unit
) {

    var isBackingUp by remember { mutableStateOf(false) }
    var lastBackup by remember { mutableStateOf("Today, 10:42 AM") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AutoPulseBackground)
    ) {

        // ----------------------------------------------------
        // HEADER
        // ----------------------------------------------------

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onBack
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = AutoPulseText
                )
            }

            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {

                Text(
                    text = "Backup & Restore",
                    color = AutoPulseText,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black
                )

                Text(
                    text = "Protect your AutoPulse data",
                    color = AutoPulseTextMuted,
                    fontSize = 11.sp
                )
            }
        }


        // ----------------------------------------------------
        // CONTENT
        // ----------------------------------------------------

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(12.dp))


            // ------------------------------------------------
            // CLOUD SYNC STATUS
            // ------------------------------------------------

            NeonCard(
                borderColor = AutoPulseCyan.copy(alpha = 0.55f),
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .background(
                                AutoPulseCyan.copy(alpha = 0.10f),
                                RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.Cloud,
                            contentDescription = null,
                            tint = AutoPulseCyan,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "Cloud Sync",
                            color = AutoPulseText,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(3.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(7.dp)
                                    .background(
                                        AutoPulseSuccess,
                                        RoundedCornerShape(50)
                                    )
                            )

                            Spacer(modifier = Modifier.width(6.dp))

                            Text(
                                text = "Sync available",
                                color = AutoPulseSuccess,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                HorizontalDivider(
                    color = AutoPulseBorder
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Last backup",
                    color = AutoPulseTextMuted,
                    fontSize = 10.sp
                )

                Text(
                    text = lastBackup,
                    color = AutoPulseText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 3.dp)
                )

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    onClick = {
                        isBackingUp = true

                        // Placeholder for actual backup logic
                        lastBackup = "Just now"
                        isBackingUp = false
                    },
                    enabled = !isBackingUp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AutoPulseCyan,
                        contentColor = AutoPulseBackground
                    )
                ) {

                    Icon(
                        imageVector = Icons.Default.CloudUpload,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = if (isBackingUp) {
                            "Backing Up..."
                        } else {
                            "Backup Now"
                        },
                        fontWeight = FontWeight.Bold
                    )
                }
            }


            Spacer(modifier = Modifier.height(22.dp))


            // ------------------------------------------------
            // RESTORE
            // ------------------------------------------------

            NeonCard(
                borderColor = AutoPulsePurple.copy(alpha = 0.60f),
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .background(
                                AutoPulsePurple.copy(alpha = 0.10f),
                                RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.CloudDownload,
                            contentDescription = null,
                            tint = AutoPulsePurple,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {

                        Text(
                            text = "Restore Data",
                            color = AutoPulseText,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Recover your AutoPulse data",
                            color = AutoPulseTextMuted,
                            fontSize = 10.sp,
                            modifier = Modifier.padding(top = 3.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Restore your saved vehicle profiles, trip history, diagnostic records and application preferences from your latest cloud backup.",
                    color = AutoPulseTextSecondary,
                    fontSize = 12.sp,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(18.dp))

                OutlinedButton(
                    onClick = {
                        // TODO: Implement cloud restore
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        AutoPulsePurple.copy(alpha = 0.7f)
                    ),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = AutoPulsePurple
                    )
                ) {

                    Icon(
                        imageVector = Icons.Default.CloudDownload,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Restore from Cloud",
                        fontWeight = FontWeight.Bold
                    )
                }
            }


            Spacer(modifier = Modifier.height(22.dp))


            // ------------------------------------------------
            // DATA INCLUDED
            // ------------------------------------------------

            Text(
                text = "BACKUP CONTENT",
                color = AutoPulseTextSecondary,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            NeonCard(
                borderColor = AutoPulseBorder
            ) {

                BackupItem(
                    icon = Icons.Default.DirectionsCar,
                    title = "Vehicle Profiles",
                    description = "Saved vehicle information"
                )

                BackupItem(
                    icon = Icons.Default.Route,
                    title = "Trip History",
                    description = "Recorded journeys and statistics"
                )

                BackupItem(
                    icon = Icons.Default.Build,
                    title = "Diagnostic Data",
                    description = "Diagnostic history and vehicle information"
                )

                BackupItem(
                    icon = Icons.Default.Settings,
                    title = "App Preferences",
                    description = "Your AutoPulse settings"
                )
            }


            Spacer(modifier = Modifier.height(32.dp))


            // ------------------------------------------------
            // INFORMATION
            // ------------------------------------------------

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = AutoPulseTextMuted,
                    modifier = Modifier.size(14.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "Your data will only be restored when you choose to do so.",
                    color = AutoPulseTextMuted,
                    fontSize = 9.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}


// ------------------------------------------------------------
// BACKUP ITEM
// ------------------------------------------------------------

@Composable
private fun BackupItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(36.dp)
                .background(
                    AutoPulseSurfaceElevated,
                    RoundedCornerShape(9.dp)
                ),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = AutoPulseTextSecondary,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = title,
                color = AutoPulseText,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = description,
                color = AutoPulseTextMuted,
                fontSize = 10.sp,
                modifier = Modifier.padding(top = 2.dp)
            )
        }

        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = AutoPulseSuccess.copy(alpha = 0.7f),
            modifier = Modifier.size(17.dp)
        )
    }
}
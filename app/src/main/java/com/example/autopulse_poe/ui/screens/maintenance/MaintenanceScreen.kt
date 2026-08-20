package com.example.autopulse_poe.ui.screens.maintenance

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun MaintenanceScreen(
    onBack: () -> Unit
) {
    var showCompleted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {

        // ----------------------------------------------------
        // HEADER
        // ----------------------------------------------------

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 20.dp,
                    top = 16.dp,
                    bottom = 8.dp
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
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    text = "Maintenance",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Keep your vehicle running reliably",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(18.dp))

            // ----------------------------------------------------
            // VEHICLE MAINTENANCE STATUS
            // ----------------------------------------------------

            NeonCard(
                borderColor = AutoPulseCyan.copy(alpha = 0.45f)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(
                                AutoPulseCyan.copy(alpha = 0.10f)
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.Build,
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
                            text = "MAINTENANCE STATUS",
                            color = AutoPulseCyan,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.sp
                        )

                        Text(
                            text = "2 services upcoming",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 3.dp)
                        )

                        Text(
                            text = "1 service requires attention",
                            color = AutoPulseWarning,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(9.dp)
                            .clip(CircleShape)
                            .background(AutoPulseWarning)
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ----------------------------------------------------
            // PRIORITY ALERTS
            // ----------------------------------------------------

            SectionTitle(
                title = "PRIORITY ALERTS",
                color = AutoPulseError
            )

            Spacer(modifier = Modifier.height(12.dp))

            MaintenanceAlertItem(
                title = "Oil Change Required",
                subtitle = "Overdue by 400 km",
                detail = "Engine oil service is recommended as soon as possible.",
                icon = Icons.Default.NotificationsActive,
                color = AutoPulseError
            )

            Spacer(modifier = Modifier.height(28.dp))

            // ----------------------------------------------------
            // UPCOMING SERVICE
            // ----------------------------------------------------

            SectionTitle(
                title = "UPCOMING SERVICE",
                color = AutoPulseCyan
            )

            Spacer(modifier = Modifier.height(12.dp))

            MaintenanceServiceItem(
                title = "Tyre Rotation",
                dueIn = "Due in approximately 1,900 km",
                progress = 0.80f,
                icon = Icons.Default.DirectionsCar,
                color = AutoPulseCyan,
                onClick = {
                    // Future: Open service details
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            MaintenanceServiceItem(
                title = "Brake Fluid Flush",
                dueIn = "Due in approximately 3 months",
                progress = 0.40f,
                icon = Icons.Default.Build,
                color = AutoPulsePurple,
                onClick = {
                    // Future: Open service details
                }
            )

            Spacer(modifier = Modifier.height(28.dp))

            // ----------------------------------------------------
            // SERVICE TRACKING
            // ----------------------------------------------------

            SectionTitle(
                title = "SERVICE TRACKING",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))

            NeonCard(
                borderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = AutoPulseSuccess,
                        modifier = Modifier.size(22.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "Track completed services",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Keep a digital record of your vehicle maintenance.",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(top = 3.dp)
                        )
                    }

                    Switch(
                        checked = showCompleted,
                        onCheckedChange = {
                            showCompleted = it
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
                            checkedTrackColor = AutoPulseCyan.copy(alpha = 0.45f),
                            uncheckedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            uncheckedTrackColor =
                                MaterialTheme.colorScheme.surfaceVariant,
                            uncheckedBorderColor =
                                MaterialTheme.colorScheme.outline
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ----------------------------------------------------
            // SERVICE HISTORY
            // ----------------------------------------------------

            SectionTitle(
                title = "SERVICE HISTORY",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))

            NeonCard(
                borderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
            ) {

                HistoryItem(
                    title = "Brake Pads Replaced",
                    date = "12 May 2026",
                    mileage = "67,600 km",
                    color = AutoPulseSuccess
                )

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 14.dp),
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
                )

                HistoryItem(
                    title = "Annual Inspection",
                    date = "10 January 2026",
                    mileage = "61,900 km",
                    color = AutoPulseCyan
                )

                if (showCompleted) {

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 14.dp),
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
                    )

                    HistoryItem(
                        title = "Engine Oil & Filter",
                        date = "18 October 2025",
                        mileage = "57,200 km",
                        color = AutoPulseSuccess
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // ----------------------------------------------------
            // MAINTENANCE TIP
            // ----------------------------------------------------

            NeonCard(
                borderColor = AutoPulseWarning.copy(alpha = 0.35f)
            ) {

                Row(
                    verticalAlignment = Alignment.Top
                ) {

                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = null,
                        tint = AutoPulseWarning,
                        modifier = Modifier.size(22.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {

                        Text(
                            text = "MAINTENANCE TIP",
                            color = AutoPulseWarning,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.sp
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Regular maintenance can help prevent unexpected faults and keep your vehicle operating efficiently.",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 12.sp,
                            lineHeight = 18.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}


// ============================================================
// SECTION TITLE
// ============================================================

@Composable
private fun SectionTitle(
    title: String,
    color: Color
) {
    Text(
        text = title,
        color = color,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
    )
}


// ============================================================
// PRIORITY ALERT
// ============================================================

@Composable
fun MaintenanceAlertItem(
    title: String,
    subtitle: String,
    detail: String,
    icon: ImageVector,
    color: Color
) {

    NeonCard(
        borderColor = color.copy(alpha = 0.65f)
    ) {

        Row(
            verticalAlignment = Alignment.Top
        ) {

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(
                        color.copy(alpha = 0.12f)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )

                Text(
                    text = subtitle,
                    color = color,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 3.dp)
                )

                Text(
                    text = detail,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 11.sp,
                    lineHeight = 16.sp,
                    modifier = Modifier.padding(top = 7.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = {
                        // Future: Open maintenance details
                    },
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        color.copy(alpha = 0.5f)
                    ),
                    contentPadding = PaddingValues(
                        horizontal = 14.dp,
                        vertical = 4.dp
                    ),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = color
                    )
                ) {

                    Text(
                        text = "VIEW SERVICE",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


// ============================================================
// UPCOMING SERVICE
// ============================================================

@Composable
fun MaintenanceServiceItem(
    title: String,
    dueIn: String,
    progress: Float,
    icon: ImageVector,
    color: Color,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            color.copy(alpha = 0.25f)
        ),
        shape = MaterialTheme.shapes.medium
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(
                            color.copy(alpha = 0.10f)
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = color,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = title,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )

                    Text(
                        text = dueIn,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(top = 3.dp)
                    )
                }

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(CircleShape),
                color = color,
                trackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "SERVICE INTERVAL",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "${(progress * 100).toInt()}%",
                    color = color,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


// ============================================================
// HISTORY ITEM
// ============================================================

@Composable
private fun HistoryItem(
    title: String,
    date: String,
    mileage: String,
    color: Color
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(9.dp)
                .clip(CircleShape)
                .background(color)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = date,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 10.sp,
                modifier = Modifier.padding(top = 3.dp)
            )
        }

        Text(
            text = mileage,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
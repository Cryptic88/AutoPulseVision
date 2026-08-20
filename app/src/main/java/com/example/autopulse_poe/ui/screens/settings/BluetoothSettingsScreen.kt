package com.example.autopulse_poe.ui.screens.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Speed
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
fun BluetoothSettingsScreen(
    onBack: () -> Unit
) {

    var isScanning by remember { mutableStateOf(false) }

    val devices = listOf(
        OBDDevice(
            name = "OBDLink MX+",
            address = "AA:BB:CC:DD:EE:FF",
            isConnected = true
        ),
        OBDDevice(
            name = "Vgate iCar Pro",
            address = "11:22:33:44:55:66",
            isConnected = false
        ),
        OBDDevice(
            name = "ELM327 Interface",
            address = "77:88:99:00:11:22",
            isConnected = false
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        // ----------------------------------------------------
        // HEADER
        // ----------------------------------------------------

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
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
                    text = "OBD-II Adapter",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black
                )

                Text(
                    text = "Manage your vehicle connection",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 10.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = {
                    isScanning = !isScanning
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Scan for devices",
                    tint = AutoPulseCyan
                )
            }
        }

        // ----------------------------------------------------
        // CONTENT
        // ----------------------------------------------------

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 12.dp
            ),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            // ------------------------------------------------
            // SCANNING
            // ------------------------------------------------

            if (isScanning) {

                item {

                    Column {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            CircularProgressIndicator(
                                modifier = Modifier.size(18.dp),
                                color = AutoPulseCyan,
                                strokeWidth = 2.dp
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = "Scanning for nearby adapters...",
                                color = AutoPulseCyan,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        LinearProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp),
                            color = AutoPulseCyan,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }
                }
            }

            // ------------------------------------------------
            // CONNECTED DEVICE
            // ------------------------------------------------

            item {

                SettingsSectionTitle(
                    title = "CONNECTED DEVICE",
                    color = AutoPulseCyan
                )

                Spacer(modifier = Modifier.height(8.dp))

                NeonCard(
                    borderColor = AutoPulseCyan.copy(alpha = 0.6f)
                ) {

                    DeviceItem(
                        device = devices[0],
                        isConnected = true
                    )
                }
            }

            // ------------------------------------------------
            // AVAILABLE DEVICES
            // ------------------------------------------------

            item {

                SettingsSectionTitle(
                    title = "AVAILABLE DEVICES",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

            items(devices.drop(1)) { device ->

                NeonCard(
                    borderColor = MaterialTheme.colorScheme.outline
                ) {

                    DeviceItem(
                        device = device,
                        isConnected = false
                    )
                }
            }

            // ------------------------------------------------
            // ADAPTER DIAGNOSTICS
            // ------------------------------------------------

            item {

                SettingsSectionTitle(
                    title = "ADAPTER DIAGNOSTICS",
                    color = AutoPulsePurple
                )

                Spacer(modifier = Modifier.height(8.dp))

                NeonCard(
                    borderColor = AutoPulsePurple.copy(alpha = 0.5f)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .background(
                                    AutoPulsePurple.copy(alpha = 0.12f),
                                    RoundedCornerShape(10.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = null,
                                tint = AutoPulsePurple,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                text = "Adapter Diagnostics",
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Check communication and adapter compatibility",
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 10.sp,
                                lineHeight = 14.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        DiagnosticValue(
                            label = "Firmware",
                            value = "v5.6.1"
                        )

                        DiagnosticValue(
                            label = "Protocol",
                            value = "ELM327"
                        )

                        DiagnosticValue(
                            label = "Status",
                            value = "Ready",
                            valueColor = AutoPulseSuccess
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AutoPulsePurple,
                            contentColor = MaterialTheme.colorScheme.background
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {

                        Icon(
                            imageVector = Icons.Default.Speed,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Run Interface Test",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // ------------------------------------------------
            // FOOTER
            // ------------------------------------------------

            item {

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "AutoPulse Bluetooth Connection",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 9.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Text(
                    text = "Only pair with adapters you trust.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                        alpha = 0.6f
                    ),
                    fontSize = 9.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 3.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}


// ------------------------------------------------------------
// DEVICE ITEM
// ------------------------------------------------------------

@Composable
fun DeviceItem(
    device: OBDDevice,
    isConnected: Boolean
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(44.dp)
                .background(
                    if (isConnected) {
                        AutoPulseCyan.copy(alpha = 0.12f)
                    } else {
                        MaterialTheme.colorScheme.surfaceVariant
                    },
                    RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = Icons.Default.Bluetooth,
                contentDescription = null,
                tint = if (isConnected) {
                    AutoPulseCyan
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                },
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = device.name,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = device.address,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 10.sp
            )
        }

        if (isConnected) {

            Column(
                horizontalAlignment = Alignment.End
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = AutoPulseSuccess,
                        modifier = Modifier.size(14.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "CONNECTED",
                        color = AutoPulseSuccess,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Black
                    )
                }

                Text(
                    text = "Active",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 9.sp
                )
            }

        } else {

            OutlinedButton(
                onClick = { },
                modifier = Modifier.height(36.dp),
                contentPadding = PaddingValues(
                    horizontal = 12.dp
                ),
                border = BorderStroke(
                    1.dp,
                    AutoPulseCyan.copy(alpha = 0.5f)
                ),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = AutoPulseCyan
                ),
                shape = RoundedCornerShape(8.dp)
            ) {

                Text(
                    text = "Connect",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


// ------------------------------------------------------------
// DIAGNOSTIC VALUE
// ------------------------------------------------------------

@Composable
private fun DiagnosticValue(
    label: String,
    value: String,
    valueColor: Color = MaterialTheme.colorScheme.onSurface
) {

    Column {

        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 9.sp
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = value,
            color = valueColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


// ------------------------------------------------------------
// DATA MODEL
// ------------------------------------------------------------

data class OBDDevice(
    val name: String,
    val address: String,
    val isConnected: Boolean
)
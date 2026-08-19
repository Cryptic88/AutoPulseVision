package com.example.autopulse_poe.ui.screens.performance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
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
fun CustomPidsScreen(
    onBack: () -> Unit
) {

    var showAddPid by remember { mutableStateOf(false) }

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
                .padding(
                    horizontal = 12.dp,
                    vertical = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = onBack) {

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = AutoPulseText
                )
            }

            Column(
                modifier = Modifier.padding(start = 4.dp)
            ) {

                Text(
                    text = "CUSTOM PID SETUP",
                    color = AutoPulseText,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Advanced vehicle parameters",
                    color = AutoPulseTextSecondary,
                    fontSize = 12.sp
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
            // DESCRIPTION
            // ------------------------------------------------

            Text(
                text = "Configure custom OBD-II parameters for sensors not included in the standard vehicle data set.",
                color = AutoPulseTextSecondary,
                fontSize = 13.sp,
                lineHeight = 19.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ------------------------------------------------
            // CONFIGURED PIDS
            // ------------------------------------------------

            Text(
                text = "CONFIGURED PIDS",
                color = AutoPulseTextSecondary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Transmission Temperature

            CustomPidCard(
                label = "Transmission Temp",
                pid = "22 11 66",
                formula = "((A × 256) + B) / 100",
                unit = "°C",
                accent = AutoPulseCyan,
                onEdit = {
                    // TODO: Edit PID
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Exhaust Gas Temperature

            CustomPidCard(
                label = "Exhaust Gas Temp",
                pid = "22 F4 3B",
                formula = "(A × 1.5) - 40",
                unit = "°F",
                accent = AutoPulsePurple,
                onEdit = {
                    // TODO: Edit PID
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ------------------------------------------------
            // ADD PID
            // ------------------------------------------------

            Button(
                onClick = {
                    showAddPid = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AutoPulseCyan,
                    contentColor = Color.Black
                )
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "DEFINE NEW PID",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ------------------------------------------------
            // PID INFORMATION
            // ------------------------------------------------

            Text(
                text = "ABOUT CUSTOM PIDS",
                color = AutoPulseTextSecondary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            NeonCard(
                borderColor = AutoPulsePurple.copy(alpha = 0.35f)
            ) {

                Row(
                    verticalAlignment = Alignment.Top
                ) {

                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = AutoPulsePurple,
                        modifier = Modifier.size(22.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {

                        Text(
                            text = "Advanced Parameters",
                            color = AutoPulseText,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Custom PIDs allow AutoPulse to read additional vehicle parameters using manufacturer-specific OBD-II commands and formulas.",
                            color = AutoPulseTextSecondary,
                            fontSize = 12.sp,
                            lineHeight = 18.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))
        }
    }

    // --------------------------------------------------------
    // ADD PID DIALOG
    // --------------------------------------------------------

    if (showAddPid) {

        AddPidDialog(
            onDismiss = {
                showAddPid = false
            },
            onSave = {
                showAddPid = false
            }
        )
    }
}


@Composable
private fun CustomPidCard(
    label: String,
    pid: String,
    formula: String,
    unit: String,
    accent: Color,
    onEdit: () -> Unit
) {

    NeonCard(
        borderColor = accent.copy(alpha = 0.35f)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(42.dp)
                    .background(
                        accent.copy(alpha = 0.10f),
                        RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.Code,
                    contentDescription = null,
                    tint = accent,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = label,
                    color = AutoPulseText,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = "PID  $pid",
                    color = accent,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            IconButton(
                onClick = onEdit
            ) {

                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit PID",
                    tint = AutoPulseTextSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Formula

        Text(
            text = "FORMULA",
            color = AutoPulseTextMuted,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = AutoPulseBackground,
            shape = RoundedCornerShape(8.dp)
        ) {

            Text(
                text = formula,
                color = AutoPulseText,
                fontSize = 13.sp,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                modifier = Modifier.padding(12.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "OUTPUT UNIT",
                color = AutoPulseTextMuted,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = unit,
                color = accent,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun AddPidDialog(
    onDismiss: () -> Unit,
    onSave: () -> Unit
) {

    var name by remember { mutableStateOf("") }
    var pid by remember { mutableStateOf("") }
    var formula by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,

        containerColor = AutoPulseSurface,

        title = {

            Text(
                text = "DEFINE NEW PID",
                color = AutoPulseText,
                fontWeight = FontWeight.Black
            )
        },

        text = {

            Column {

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Parameter Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = pid,
                    onValueChange = { pid = it },
                    label = { Text("PID Code") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = formula,
                    onValueChange = { formula = it },
                    label = { Text("Conversion Formula") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = unit,
                    onValueChange = { unit = it },
                    label = { Text("Output Unit") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },

        confirmButton = {

            TextButton(
                onClick = onSave
            ) {

                Text(
                    text = "SAVE",
                    color = AutoPulseCyan,
                    fontWeight = FontWeight.Bold
                )
            }
        },

        dismissButton = {

            TextButton(
                onClick = onDismiss
            ) {

                Text(
                    text = "CANCEL",
                    color = AutoPulseTextSecondary
                )
            }
        }
    )
}
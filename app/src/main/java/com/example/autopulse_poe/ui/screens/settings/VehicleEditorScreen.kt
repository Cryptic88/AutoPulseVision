package com.example.autopulse_poe.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.theme.*

@Composable
fun VehicleEditorScreen(
    onBack: () -> Unit
) {

    var make by remember { mutableStateOf("Tesla") }
    var model by remember { mutableStateOf("Model S") }
    var year by remember { mutableStateOf("2024") }
    var engineSize by remember { mutableStateOf("Electric") }
    var vin by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AutoPulseBackground)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {

            IconButton(onClick = onBack) {

                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = AutoPulseText
                )
            }

            Text(
                text = "Vehicle Profile",
                color = AutoPulseText,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 4.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { }) {

                Icon(
                    Icons.Default.Save,
                    contentDescription = "Save",
                    tint = AutoPulseCyan
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {

            Text(
                text = "VEHICLE INFORMATION",
                color = AutoPulseCyan,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            AutoPulseTextField(
                label = "Make",
                value = make,
                onValueChange = { make = it }
            )

            AutoPulseTextField(
                label = "Model",
                value = model,
                onValueChange = { model = it }
            )

            AutoPulseTextField(
                label = "Year",
                value = year,
                onValueChange = { year = it }
            )

            AutoPulseTextField(
                label = "Engine / Powertrain",
                value = engineSize,
                onValueChange = { engineSize = it }
            )

            AutoPulseTextField(
                label = "VIN",
                value = vin,
                onValueChange = { vin = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AutoPulseCyan,
                    contentColor = AutoPulseBackground
                )
            ) {

                Icon(
                    Icons.Default.Save,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    "SAVE VEHICLE",
                    fontWeight = FontWeight.Black
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    AutoPulseWarning
                ),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = AutoPulseWarning
                )
            ) {

                Icon(
                    Icons.Default.Delete,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    "DELETE VEHICLE",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}


@Composable
private fun AutoPulseTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {

    Column(
        modifier = Modifier.padding(bottom = 16.dp)
    ) {

        Text(
            text = label,
            color = AutoPulseTextSecondary,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = AutoPulseText,
                unfocusedTextColor = AutoPulseText,
                focusedBorderColor = AutoPulseCyan,
                unfocusedBorderColor = AutoPulseBorder,
                cursorColor = AutoPulseCyan
            )
        )
    }
}

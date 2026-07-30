package com.example.autopulse_poe.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.theme.*

@Composable
fun VehicleEditorScreen(onBack: () -> Unit) {
    var make by remember { mutableStateOf("Tesla") }
    var model by remember { mutableStateOf("Model S") }
    var year by remember { mutableStateOf("2024") }
    var engineSize by remember { mutableStateOf("Electric") }
    var vin by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.White)
            }
            Text(
                text = "Edit Vehicle",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /* Save */ }) {
                Icon(Icons.Default.Save, contentDescription = null, tint = NeonCyan)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            Text(text = "Vehicle Information", color = NeonCyan, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(20.dp))

            NeonTextField(label = "Make", value = make, onValueChange = { make = it })
            NeonTextField(label = "Model", value = model, onValueChange = { model = it })
            NeonTextField(label = "Year", value = year, onValueChange = { year = it })
            NeonTextField(label = "Engine Size", value = engineSize, onValueChange = { engineSize = it })
            NeonTextField(label = "VIN (Optional)", value = vin, onValueChange = { vin = it })

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { /* Save */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NeonPurple)
            ) {
                Text("Save Profile", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            OutlinedButton(
                onClick = { /* Delete */ },
                modifier = Modifier.fillMaxWidth(),
                border = androidx.compose.foundation.BorderStroke(1.dp, NeonRed)
            ) {
                Text("Delete Vehicle Profile", color = NeonRed)
            }
        }
    }
}

@Composable
fun NeonTextField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        Text(text = label, color = Color.White.copy(alpha = 0.5f), fontSize = 12.sp, modifier = Modifier.padding(start = 4.dp, bottom = 4.dp))
        Surface(
            color = Color.White.copy(alpha = 0.05f),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp)) {
                if (value.isEmpty()) {
                    Text(text = "Enter $label", color = Color.White.copy(alpha = 0.2f))
                }
                // Using a Box instead of actual TextField for UI demonstration, 
                // in a real app this would be a BasicTextField or OutlinedTextField
                Text(text = value, color = Color.White)
            }
        }
    }
}

package com.example.autopulse_poe.ui.screens.reports

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun ReportViewerScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.White)
            }
            Text(
                text = "Health Report Preview",
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { }) {
                Icon(Icons.Default.Share, contentDescription = null, tint = NeonCyan)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(32.dp)
        ) {
            Text(text = "AUTOPULSE VEHICLE HEALTH REPORT", color = Color.Black, fontWeight = FontWeight.Black, fontSize = 18.sp)
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), thickness = 2.dp, color = Color.Black)
            
            Text(text = "Vehicle: 2024 Tesla Model 3", color = Color.Black, fontSize = 14.sp)
            Text(text = "VIN: 5YJ3E1EA5KF123456", color = Color.Black, fontSize = 14.sp)
            Text(text = "Date: July 30, 2026", color = Color.Black, fontSize = 14.sp)

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "DIAGNOSTIC SUMMARY", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Total DTCs Found: 0", color = NeonGreen, fontSize = 14.sp)
            Text(text = "Emissions Readiness: PASS", color = NeonGreen, fontSize = 14.sp)

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "SENSOR READINGS SNAPSHOT", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(12.dp))
            
            ReportTableItem("Engine RPM", "750 RPM")
            ReportTableItem("Coolant Temp", "92°C")
            ReportTableItem("Battery Voltage", "14.2V")
            ReportTableItem("Fuel Trim", "+1.2%")
            
            Spacer(modifier = Modifier.height(48.dp))
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.LightGray.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "[ GRAPH: SENSOR TRENDS ]", color = Color.Gray, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun ReportTableItem(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = Color.Black, fontSize = 13.sp)
        Text(text = value, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 13.sp)
    }
}

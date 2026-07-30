package com.example.autopulse_poe.ui.screens.reports

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsHubScreen(
    onNavigateToMechanic: () -> Unit = {},
    onNavigateToViewer: () -> Unit = {}
) {
    val reports = listOf(
        VehicleReport("Full Diagnostic Scan", "July 28, 2026", 92),
        VehicleReport("Emissions Compliance", "July 15, 2026", 98),
        VehicleReport("Pre-Trip Inspection", "June 20, 2026", 85)
    )

    var showShareSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    if (showShareSheet) {
        ModalBottomSheet(
            onDismissRequest = { showShareSheet = false },
            sheetState = sheetState,
            containerColor = DarkSurface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .padding(bottom = 32.dp)
            ) {
                Text(text = "Share Health Report", color = Color.White, fontWeight = FontWeight.Black, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(24.dp))
                
                ShareOptionItem(icon = Icons.Default.Email, label = "Email PDF", color = NeonCyan)
                ShareOptionItem(icon = Icons.Default.Chat, label = "Send via Messages", color = NeonGreen)
                ShareOptionItem(icon = Icons.Default.QrCode, label = "Generate QR Code", color = NeonMagenta)
                ShareOptionItem(icon = Icons.Default.CloudUpload, label = "Upload to Cloud", color = NeonPurple)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(20.dp)
    ) {
        Text(
            text = "Health Reports",
            fontSize = 28.sp,
            fontWeight = FontWeight.Black,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Button(
            onClick = onNavigateToViewer,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = NeonMagenta)
        ) {
            Icon(Icons.Default.PictureAsPdf, contentDescription = null)
            Spacer(modifier = Modifier.width(12.dp))
            Text("Generate New Health PDF", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedButton(
            onClick = onNavigateToMechanic,
            modifier = Modifier.fillMaxWidth().height(48.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, NeonCyan)
        ) {
            Text("Switch to Mechanic View", color = NeonCyan)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Previous Reports", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(reports) { report ->
                ReportCard(report, onShare = { showShareSheet = true })
            }
        }
    }
}

data class VehicleReport(val title: String, val date: String, val score: Int)

@Composable
fun ReportCard(report: VehicleReport, onShare: () -> Unit) {
    NeonCard(borderColor = Color.White.copy(alpha = 0.1f)) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = report.title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = report.date, color = Color.White.copy(alpha = 0.5f), fontSize = 12.sp)
                Text(text = "Health Score: ${report.score}%", color = NeonGreen, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
            IconButton(onClick = onShare) {
                Icon(Icons.Default.Share, contentDescription = null, tint = NeonCyan)
            }
        }
    }
}

@Composable
fun ShareOptionItem(icon: ImageVector, label: String, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(color.copy(alpha = 0.1f), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = label, color = Color.White, fontSize = 16.sp)
    }
}

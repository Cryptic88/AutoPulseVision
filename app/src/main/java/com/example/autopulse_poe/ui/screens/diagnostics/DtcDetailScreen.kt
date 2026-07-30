package com.example.autopulse_poe.ui.screens.diagnostics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Info
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
fun DtcDetailScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.White)
            }
            Text(
                text = "Fault Analysis",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            // Main Code Card
            NeonCard(borderColor = NeonRed) {
                Text(text = "P0300", fontSize = 48.sp, fontWeight = FontWeight.Black, color = NeonRed)
                Text(text = "Random or Multiple Cylinder Misfire Detected", fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Priority: Critical", color = NeonRed, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // AI Explanation
            NeonCard(borderColor = NeonPurple) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = NeonPurple)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "AI Mechanic Assistant", color = Color.White, fontWeight = FontWeight.Black)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Surface(
                    color = Color.White.copy(alpha = 0.05f),
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Based on the P0300 code, I recommend checking your spark plugs first. Would you like a step-by-step guide?",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                        Button(
                            onClick = { /* Chat logic */ },
                            modifier = Modifier.padding(top = 12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = NeonPurple)
                        ) {
                            Text("Start Repair Chat")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Common Causes
            Text(text = "Common Causes", color = NeonCyan, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(12.dp))
            CauseItem("Worn Spark Plugs or Ignition Coils")
            CauseItem("Vacuum Leak in intake manifold")
            CauseItem("Low Fuel Pressure or clogged filter")

            Spacer(modifier = Modifier.height(32.dp))

            // Fix Suggestions
            Text(text = "Recommended Actions", color = NeonCyan, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(12.dp))
            NeonCard(borderColor = Color.White.copy(alpha = 0.1f)) {
                ActionItem("Step 1: Check spark plug condition", true)
                ActionItem("Step 2: Inspect ignition coil packs", false)
                ActionItem("Step 3: Perform smoke test for vacuum leaks", false)
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun CauseItem(text: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Default.Info, contentDescription = null, tint = NeonCyan.copy(alpha = 0.5f), modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = text, color = Color.White, fontSize = 14.sp)
    }
}

@Composable
fun ActionItem(text: String, isPrimary: Boolean) {
    Row(modifier = Modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            Icons.Default.Build,
            contentDescription = null,
            tint = if (isPrimary) NeonCyan else Color.White.copy(alpha = 0.3f),
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, color = Color.White, fontSize = 14.sp)
    }
}

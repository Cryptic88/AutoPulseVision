package com.example.autopulse_poe.ui.screens.performance

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun AdvancedPerformanceScreen(onBack: () -> Unit) {
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
                text = "Dyno Mode",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            // Dyno Graph
            NeonCard(borderColor = NeonPurple, modifier = Modifier.height(300.dp)) {
                Text(text = "Power & Torque Curve", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier.fillMaxSize()) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val powerPath = Path().apply {
                            moveTo(0f, size.height * 0.9f)
                            quadraticTo(size.width * 0.4f, size.height * 0.6f, size.width * 0.8f, size.height * 0.2f)
                            lineTo(size.width, size.height * 0.25f)
                        }
                        val torquePath = Path().apply {
                            moveTo(0f, size.height * 0.7f)
                            quadraticTo(size.width * 0.3f, size.height * 0.3f, size.width * 0.6f, size.height * 0.35f)
                            quadraticTo(size.width * 0.8f, size.height * 0.45f, size.width, size.height * 0.6f)
                        }
                        
                        drawPath(path = powerPath, color = NeonMagenta, style = Stroke(width = 3.dp.toPx()))
                        drawPath(path = torquePath, color = NeonCyan, style = Stroke(width = 3.dp.toPx()))
                    }
                    
                    // Legend
                    Column(modifier = Modifier.align(Alignment.TopEnd)) {
                        LegendItem(label = "Power (HP)", color = NeonMagenta)
                        LegendItem(label = "Torque (Nm)", color = NeonCyan)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Peak Values
            Row(modifier = Modifier.fillMaxWidth()) {
                PeakValueCard(label = "Peak Power", value = "285 HP", subValue = "@ 6,400 RPM", color = NeonMagenta, modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(16.dp))
                PeakValueCard(label = "Peak Torque", value = "420 Nm", subValue = "@ 3,200 RPM", color = NeonCyan, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Comparison to Stock
            NeonCard(borderColor = NeonOrange) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Info, contentDescription = null, tint = NeonOrange)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Comparison to Stock", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(16.dp))
                ComparisonRow(label = "Horsepower", current = "+15 HP", color = NeonGreen)
                ComparisonRow(label = "Torque", current = "+25 Nm", color = NeonGreen)
                ComparisonRow(label = "Weight Reduction", current = "-12 kg", color = NeonCyan)
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun LegendItem(label: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 4.dp)) {
        Box(modifier = Modifier.size(10.dp).background(color, shape = StrokeCap.Butt.let { androidx.compose.foundation.shape.CircleShape }))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = label, color = Color.White.copy(alpha = 0.7f), fontSize = 10.sp)
    }
}

@Composable
fun PeakValueCard(label: String, value: String, subValue: String, color: Color, modifier: Modifier = Modifier) {
    NeonCard(borderColor = color.copy(alpha = 0.4f), modifier = modifier) {
        Text(text = label, color = Color.White.copy(alpha = 0.6f), fontSize = 11.sp)
        Text(text = value, color = color, fontSize = 24.sp, fontWeight = FontWeight.Black)
        Text(text = subValue, color = Color.White.copy(alpha = 0.4f), fontSize = 10.sp)
    }
}

@Composable
fun ComparisonRow(label: String, current: String, color: Color) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = label, color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
        Text(text = current, color = color, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}

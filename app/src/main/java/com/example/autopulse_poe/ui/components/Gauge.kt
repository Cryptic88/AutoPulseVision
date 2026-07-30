package com.example.autopulse_poe.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.theme.NeonCyan

@Composable
fun Gauge(
    value: Float,
    maxValue: Float,
    label: String,
    unit: String,
    modifier: Modifier = Modifier,
    color: Color = NeonCyan
) {
    val sweepAngle = 240f
    val startAngle = 150f
    val progress = (value / maxValue).coerceIn(0f, 1f)

    Box(modifier = modifier.aspectRatio(1f), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            // Background arc with segments
            val segmentGap = 2f
            val totalSegments = 20
            val segmentAngle = (sweepAngle - (totalSegments - 1) * segmentGap) / totalSegments

            for (i in 0 until totalSegments) {
                val currentSegmentStart = startAngle + i * (segmentAngle + segmentGap)
                val isFilled = (i.toFloat() / totalSegments) < progress
                
                drawArc(
                    color = if (isFilled) color else Color.White.copy(alpha = 0.1f),
                    startAngle = currentSegmentStart,
                    sweepAngle = segmentAngle,
                    useCenter = false,
                    style = Stroke(width = 8.dp.toPx(), cap = StrokeCap.Butt)
                )
            }
            
            // Outer Glow (subtle)
            drawArc(
                color = color.copy(alpha = 0.2f),
                startAngle = startAngle,
                sweepAngle = sweepAngle * progress,
                useCenter = false,
                style = Stroke(width = 12.dp.toPx(), cap = StrokeCap.Round)
            )
        }
        
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (value > 0) {
                Text(
                    text = value.toInt().toString(),
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.Black,
                        fontSize = 24.sp
                    ),
                    color = Color.White
                )
                if (unit.isNotEmpty()) {
                    Text(
                        text = unit,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.6f)
                    )
                }
            }
            if (label.isNotEmpty()) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelLarge,
                    color = color,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

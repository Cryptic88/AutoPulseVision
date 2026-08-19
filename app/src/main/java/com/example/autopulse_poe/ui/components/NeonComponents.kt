package com.example.autopulse_poe.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.theme.AutoPulseBorder
import com.example.autopulse_poe.ui.theme.AutoPulseCyan
import com.example.autopulse_poe.ui.theme.AutoPulseSurface
import com.example.autopulse_poe.ui.theme.AutoPulseText
import com.example.autopulse_poe.ui.theme.AutoPulseTextMuted
import com.example.autopulse_poe.ui.theme.NeonCyan
import com.example.autopulse_poe.ui.theme.NeonMagenta
import com.example.autopulse_poe.ui.theme.NeonPurple

@Composable
fun NeonCard(
    modifier: Modifier = Modifier,
    borderColor: Color = AutoPulseBorder,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(AutoPulseSurface)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        content()
    }
}

@Composable
fun NeonButton(
    text: String,
    icon: ImageVector,
    gradientColors: List<Color>,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .height(60.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Brush.linearGradient(gradientColors))
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun CircularScoreIndicator(
    score: Int,
    label: String,
    modifier: Modifier = Modifier,
    color: Color = NeonMagenta
) {
    Box(modifier = modifier.size(120.dp), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawArc(
                color = Color.White.copy(alpha = 0.1f),
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = 8.dp.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = (score / 100f) * 360f,
                useCenter = false,
                style = Stroke(
                    width = 8.dp.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = score.toString(),
                fontSize = 36.sp,
                fontWeight = FontWeight.Black,
                color = Color.White
            )
            Text(
                text = label,
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
fun LiveTripSummaryCard(
    duration: String,
    distance: String,
    avgSpeed: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(AutoPulseSurface)
            .border(
                width = 1.dp,
                color = AutoPulseCyan.copy(alpha = 0.55f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(18.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "CURRENT TRIP",
                color = AutoPulseCyan,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = duration,
                color = AutoPulseCyan,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TripStatItem(
                label = "DISTANCE",
                value = distance
            )

            TripStatItem(
                label = "AVG SPEED",
                value = avgSpeed
            )
        }
    }
}

@Composable
private fun TripStatItem(
    label: String,
    value: String
) {
    Column {
        Text(
            text = label,
            color = AutoPulseTextMuted,
            style = MaterialTheme.typography.labelSmall
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = value,
            color = AutoPulseText,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

package com.example.autopulse_poe.ui.screens.gamification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.ElectricCar
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun WeeklyChallengesScreen(onBack: () -> Unit) {
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
                text = "Weekly Challenges",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "Complete these to earn massive XP!",
                color = NeonCyan,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            ChallengeCard(
                title = "Most Fuel-Efficient Drive",
                desc = "Maintain over 35 MPG for a single trip over 5 miles.",
                progress = 0.65f,
                icon = Icons.Default.Eco,
                color = NeonGreen,
                timeLeft = "2 days left"
            )

            Spacer(modifier = Modifier.height(16.dp))

            ChallengeCard(
                title = "Smooth Operator",
                desc = "Complete 3 trips with zero hard braking events.",
                progress = 0.33f,
                icon = Icons.Default.ElectricCar,
                color = NeonCyan,
                timeLeft = "4 days left"
            )

            Spacer(modifier = Modifier.height(16.dp))

            ChallengeCard(
                title = "Early Bird Commuter",
                desc = "Finish 5 trips before 8:00 AM this week.",
                progress = 0.8f,
                icon = Icons.Default.Timer,
                color = NeonOrange,
                timeLeft = "1 day left"
            )
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun ChallengeCard(
    title: String,
    desc: String,
    progress: Float,
    icon: ImageVector,
    color: Color,
    timeLeft: String
) {
    NeonCard(borderColor = color.copy(alpha = 0.4f)) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(color.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(text = timeLeft, color = color, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = desc,
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 13.sp,
                lineHeight = 18.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .weight(1f)
                        .height(8.dp)
                        .clip(CircleShape),
                    color = color,
                    trackColor = Color.White.copy(alpha = 0.1f)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "${(progress * 100).toInt()}%",
                    color = Color.White,
                    fontWeight = FontWeight.Black,
                    fontSize = 14.sp
                )
            }
        }
    }
}

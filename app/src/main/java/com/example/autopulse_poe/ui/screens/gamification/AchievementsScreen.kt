package com.example.autopulse_poe.ui.screens.gamification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
fun AchievementsScreen(onNavigateToLeaderboards: () -> Unit = {}, onNavigateToChallenges: () -> Unit = {}) {
    val badges = listOf(
        Badge("Eco Warrior", "Maintain >30 MPG for 5 trips", Icons.Default.Eco, NeonGreen, true),
        Badge("Steady Hand", "Zero hard braking for 100 miles", Icons.Default.PanTool, NeonCyan, true),
        Badge("Early Bird", "Complete 5 trips before 8 AM", Icons.Default.WbSunny, NeonOrange, true),
        Badge("Night Owl", "10 miles of night driving", Icons.Default.NightsStay, NeonPurple, false),
        Badge("Master Mechanic", "Diagnose and fix 3 DTCs", Icons.Default.Build, NeonMagenta, false),
        Badge("Long Hauler", "Single trip over 200 miles", Icons.Default.Route, NeonBlue, false)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(20.dp)
    ) {
        Text(
            text = "Driver Profile",
            fontSize = 28.sp,
            fontWeight = FontWeight.Black,
            color = Color.White,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Driving Scores
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            ScoreCard("Daily", 88, NeonCyan, Modifier.weight(1f))
            ScoreCard("Weekly", 92, NeonMagenta, Modifier.weight(1f))
            ScoreCard("Monthly", 85, NeonPurple, Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Level & Progress
        NeonCard(borderColor = NeonCyan) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "LEVEL 12", color = Color.White, fontWeight = FontWeight.Black, fontSize = 20.sp)
                    Text(text = "4,250 / 5,000 XP", color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp)
                }
                Text(text = "TOP 5%", color = NeonCyan, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))
            LinearProgressIndicator(
                progress = { 0.85f },
                modifier = Modifier.fillMaxWidth().height(8.dp).clip(CircleShape),
                color = NeonCyan,
                trackColor = Color.White.copy(alpha = 0.1f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                onClick = onNavigateToLeaderboards,
                modifier = Modifier.weight(1f).height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NeonOrange, contentColor = Color.Black)
            ) {
                Text("Leaderboards", fontWeight = FontWeight.Bold)
            }
            Button(
                onClick = onNavigateToChallenges,
                modifier = Modifier.weight(1f).height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NeonPurple)
            ) {
                Text("Challenges", fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Badge Gallery", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(badges) { badge ->
                BadgeItem(badge)
            }
        }
    }
}

data class Badge(val title: String, val desc: String, val icon: ImageVector, val color: Color, val unlocked: Boolean)

@Composable
fun ScoreCard(label: String, score: Int, color: Color, modifier: Modifier = Modifier) {
    NeonCard(borderColor = color.copy(alpha = 0.3f), modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Text(text = label, color = Color.White.copy(alpha = 0.6f), fontSize = 10.sp, fontWeight = FontWeight.Bold)
            Text(text = score.toString(), color = color, fontSize = 24.sp, fontWeight = FontWeight.Black)
        }
    }
}

@Composable
fun BadgeItem(badge: Badge) {
    val alpha = if (badge.unlocked) 1f else 0.3f
    NeonCard(
        borderColor = if (badge.unlocked) badge.color else Color.Gray,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(if (badge.unlocked) badge.color.copy(alpha = 0.2f) else Color.White.copy(alpha = 0.05f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    badge.icon,
                    contentDescription = null,
                    tint = if (badge.unlocked) badge.color else Color.White.copy(alpha = 0.2f),
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = badge.title,
                color = Color.White.copy(alpha = alpha),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text(
                text = badge.desc,
                color = Color.White.copy(alpha = alpha * 0.6f),
                fontSize = 10.sp,
                lineHeight = 14.sp,
                modifier = Modifier.padding(top = 4.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

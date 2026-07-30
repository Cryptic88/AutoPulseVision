package com.example.autopulse_poe.ui.screens.gamification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun LeaderboardsScreen(onBack: () -> Unit) {
    val rankingData = listOf(
        Ranking("Marco R.", 98, true),
        Ranking("Sarah K.", 95, false),
        Ranking("Alex Smith", 92, true), // Current User
        Ranking("John D.", 88, false),
        Ranking("Elena V.", 85, false),
        Ranking("Tom H.", 82, false)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
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
                text = "Global Leaderboard",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            // Top Challenge
            NeonCard(borderColor = NeonOrange) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.EmojiEvents, contentDescription = null, tint = NeonOrange, modifier = Modifier.size(32.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = "Weekly Efficiency Challenge", color = Color.White, fontWeight = FontWeight.Bold)
                        Text(text = "Ends in 2 days", color = NeonOrange, fontSize = 12.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Safety Rankings", color = NeonCyan, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                itemsIndexed(rankingData) { index, ranking ->
                    RankingItem(index + 1, ranking)
                }
            }
        }
    }
}

data class Ranking(val name: String, val score: Int, val isUser: Boolean)

@Composable
fun RankingItem(rank: Int, ranking: Ranking) {
    val borderColor = if (ranking.isUser) NeonCyan else Color.White.copy(alpha = 0.05f)
    val backgroundAlpha = if (ranking.isUser) 0.1f else 0.05f
    
    Surface(
        color = Color.White.copy(alpha = backgroundAlpha),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth().then(
            if (ranking.isUser) Modifier.background(NeonCyan.copy(alpha = 0.1f), MaterialTheme.shapes.medium) else Modifier
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "#$rank",
                fontSize = 18.sp,
                fontWeight = FontWeight.Black,
                color = if (rank <= 3) NeonOrange else Color.White.copy(alpha = 0.4f),
                modifier = Modifier.width(40.dp)
            )
            
            Box(
                modifier = Modifier.size(32.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = null, tint = Color.White.copy(alpha = 0.5f), modifier = Modifier.size(20.dp))
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Text(
                text = ranking.name,
                color = if (ranking.isUser) NeonCyan else Color.White,
                fontWeight = if (ranking.isUser) FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier.weight(1f)
            )
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = ranking.score.toString(), color = Color.White, fontWeight = FontWeight.Black)
                Spacer(modifier = Modifier.width(4.dp))
                Icon(Icons.Default.Star, contentDescription = null, tint = NeonCyan, modifier = Modifier.size(14.dp))
            }
        }
    }
}

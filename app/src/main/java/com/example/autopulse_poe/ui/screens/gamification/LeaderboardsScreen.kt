package com.example.autopulse_poe.ui.screens.gamification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
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
        Ranking("Marco R.", 98, false),
        Ranking("Sarah K.", 95, false),
        Ranking("Alex Smith", 92, true),
        Ranking("John D.", 88, false),
        Ranking("Elena V.", 85, false),
        Ranking("Tom H.", 82, false)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {

        // ----------------------------------------------------
        // HEADER
        // ----------------------------------------------------

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = onBack) {

                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {

                Text(
                    text = "Global Leaderboard",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )

                Text(
                    text = "Compete. Improve. Climb.",
                    color = Color.White.copy(alpha = 0.5f),
                    fontSize = 10.sp
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                bottom = 32.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // ------------------------------------------------
            // CURRENT CHALLENGE
            // ------------------------------------------------

            item {

                NeonCard(
                    borderColor = NeonOrange.copy(alpha = 0.7f)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .clip(CircleShape)
                                .background(NeonOrange.copy(alpha = 0.12f)),
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                Icons.Default.EmojiEvents,
                                contentDescription = null,
                                tint = NeonOrange,
                                modifier = Modifier.size(25.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                text = "WEEKLY EFFICIENCY CHALLENGE",
                                color = Color.White,
                                fontWeight = FontWeight.Black,
                                fontSize = 12.sp
                            )

                            Text(
                                text = "Ends in 2 days",
                                color = NeonOrange,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 3.dp)
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.End
                        ) {

                            Text(
                                text = "+500",
                                color = NeonOrange,
                                fontWeight = FontWeight.Black,
                                fontSize = 16.sp
                            )

                            Text(
                                text = "XP",
                                color = NeonOrange.copy(alpha = 0.6f),
                                fontSize = 8.sp
                            )
                        }
                    }
                }
            }

            // ------------------------------------------------
            // PODIUM
            // ------------------------------------------------

            item {

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "TOP DRIVERS",
                    color = NeonCyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.Bottom
                ) {

                    PodiumDriver(
                        name = rankingData[1].name,
                        score = rankingData[1].score,
                        rank = 2,
                        color = Color.LightGray,
                        modifier = Modifier.weight(1f)
                    )

                    PodiumDriver(
                        name = rankingData[0].name,
                        score = rankingData[0].score,
                        rank = 1,
                        color = NeonOrange,
                        modifier = Modifier
                            .weight(1f)
                            .height(145.dp)
                    )

                    PodiumDriver(
                        name = rankingData[2].name,
                        score = rankingData[2].score,
                        rank = 3,
                        color = Color(0xFFCD7F32),
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // ------------------------------------------------
            // RANKINGS
            // ------------------------------------------------

            item {

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "SAFETY RANKINGS",
                    color = NeonCyan,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            itemsIndexed(rankingData.drop(3)) { index, ranking ->

                RankingItem(
                    rank = index + 4,
                    ranking = ranking
                )
            }

            // ------------------------------------------------
            // CURRENT USER
            // ------------------------------------------------

            item {

                Spacer(modifier = Modifier.height(8.dp))

                NeonCard(
                    borderColor = NeonCyan.copy(alpha = 0.7f)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            Icons.Default.Person,
                            contentDescription = null,
                            tint = NeonCyan,
                            modifier = Modifier.size(22.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                text = "YOUR POSITION",
                                color = NeonCyan,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Black
                            )

                            Text(
                                text = "#3 • Alex Smith",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }

                        Text(
                            text = "92 ★",
                            color = NeonCyan,
                            fontWeight = FontWeight.Black,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}

data class Ranking(
    val name: String,
    val score: Int,
    val isUser: Boolean
)

@Composable
fun PodiumDriver(
    name: String,
    score: Int,
    rank: Int,
    color: Color,
    modifier: Modifier = Modifier
) {

    NeonCard(
        borderColor = color.copy(alpha = 0.5f),
        modifier = modifier.heightIn(min = 125.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(color.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "#$rank",
                    color = color,
                    fontWeight = FontWeight.Black,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = name,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "$score ★",
                color = color,
                fontSize = 16.sp,
                fontWeight = FontWeight.Black
            )
        }
    }
}

@Composable
fun RankingItem(
    rank: Int,
    ranking: Ranking
) {

    val isUser = ranking.isUser

    Surface(
        color = if (isUser) {
            NeonCyan.copy(alpha = 0.08f)
        } else {
            Color.White.copy(alpha = 0.035f)
        },
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "#$rank",
                fontSize = 15.sp,
                fontWeight = FontWeight.Black,
                color = if (rank <= 3) NeonOrange else Color.White.copy(alpha = 0.4f),
                modifier = Modifier.width(40.dp)
            )

            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(
                        if (isUser)
                            NeonCyan.copy(alpha = 0.15f)
                        else
                            Color.White.copy(alpha = 0.08f)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    tint = if (isUser) NeonCyan else Color.White.copy(alpha = 0.4f),
                    modifier = Modifier.size(19.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = ranking.name,
                color = if (isUser) NeonCyan else Color.White,
                fontWeight = if (isUser) FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "${ranking.score}",
                color = Color.White,
                fontWeight = FontWeight.Black
            )

            Spacer(modifier = Modifier.width(4.dp))

            Icon(
                Icons.Default.Star,
                contentDescription = null,
                tint = NeonCyan,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}
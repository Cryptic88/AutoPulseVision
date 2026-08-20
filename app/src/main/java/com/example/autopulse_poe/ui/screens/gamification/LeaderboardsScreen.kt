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
fun LeaderboardsScreen(
    onBack: () -> Unit
) {

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
            .background(MaterialTheme.colorScheme.background)
    ) {

        // ====================================================
        // HEADER
        // ====================================================

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 12.dp,
                    end = 20.dp,
                    top = 12.dp,
                    bottom = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onBack
            ) {

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Column(
                modifier = Modifier.padding(start = 4.dp)
            ) {

                Text(
                    text = "Global Leaderboard",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Compete. Improve. Climb.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 10.sp
                )
            }
        }

        // ====================================================
        // SCROLLABLE CONTENT
        // ====================================================

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                bottom = 32.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // ====================================================
            // CURRENT CHALLENGE
            // ====================================================

            item {

                NeonCard(
                    borderColor = AutoPulseWarning.copy(alpha = 0.55f)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .clip(CircleShape)
                                .background(
                                    AutoPulseWarning.copy(alpha = 0.10f)
                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = Icons.Default.EmojiEvents,
                                contentDescription = null,
                                tint = AutoPulseWarning,
                                modifier = Modifier.size(25.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                text = "WEEKLY EFFICIENCY CHALLENGE",
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Black,
                                fontSize = 12.sp
                            )

                            Text(
                                text = "Ends in 2 days",
                                color = AutoPulseWarning,
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
                                color = AutoPulseWarning,
                                fontWeight = FontWeight.Black,
                                fontSize = 16.sp
                            )

                            Text(
                                text = "XP",
                                color = AutoPulseWarning.copy(alpha = 0.60f),
                                fontSize = 8.sp
                            )
                        }
                    }
                }
            }

            // ====================================================
            // TOP DRIVERS
            // ====================================================

            item {

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "TOP DRIVERS",
                    color = AutoPulseWarning,
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

                    // ------------------------------------------------
                    // 2ND PLACE
                    // ------------------------------------------------

                    PodiumDriver(
                        name = rankingData[1].name,
                        score = rankingData[1].score,
                        rank = 2,
                        color = Color.LightGray,
                        modifier = Modifier
                            .weight(1f)
                            .height(135.dp)
                    )

                    // ------------------------------------------------
                    // 1ST PLACE
                    // ------------------------------------------------

                    PodiumDriver(
                        name = rankingData[0].name,
                        score = rankingData[0].score,
                        rank = 1,
                        color = AutoPulseWarning,
                        modifier = Modifier
                            .weight(1f)
                            .height(175.dp)
                    )

                    // ------------------------------------------------
                    // 3RD PLACE
                    // ------------------------------------------------

                    PodiumDriver(
                        name = rankingData[2].name,
                        score = rankingData[2].score,
                        rank = 3,
                        color = Color(0xFFCD7F32),
                        modifier = Modifier
                            .weight(1f)
                            .height(135.dp)
                    )
                }
            }

            // ====================================================
            // RANKINGS
            // ====================================================

            item {

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "SAFETY RANKINGS",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            itemsIndexed(
                rankingData.drop(3)
            ) { index, ranking ->

                RankingItem(
                    rank = index + 4,
                    ranking = ranking
                )
            }

            // ====================================================
            // CURRENT USER
            // ====================================================

            item {

                Spacer(modifier = Modifier.height(8.dp))

                NeonCard(
                    borderColor = AutoPulseCyan.copy(alpha = 0.60f)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(
                                    AutoPulseCyan.copy(alpha = 0.10f)
                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = AutoPulseCyan,
                                modifier = Modifier.size(21.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                text = "YOUR POSITION",
                                color = AutoPulseCyan,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Black
                            )

                            Text(
                                text = "#3 • Alex Smith",
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.End
                        ) {

                            Text(
                                text = "92",
                                color = AutoPulseCyan,
                                fontWeight = FontWeight.Black,
                                fontSize = 17.sp
                            )

                            Text(
                                text = "SCORE",
                                color = AutoPulseCyan.copy(alpha = 0.55f),
                                fontSize = 7.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}


// ============================================================
// DATA
// ============================================================

data class Ranking(
    val name: String,
    val score: Int,
    val isUser: Boolean
)


// ============================================================
// PODIUM DRIVER
// ============================================================

@Composable
fun PodiumDriver(
    name: String,
    score: Int,
    rank: Int,
    color: Color,
    modifier: Modifier = Modifier
) {

    NeonCard(
        borderColor = color.copy(
            alpha = if (rank == 1) 0.70f else 0.45f
        ),
        modifier = modifier
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            // ====================================================
            // RANK CIRCLE
            // ====================================================

            Box(
                modifier = Modifier
                    .size(
                        if (rank == 1) 52.dp else 42.dp
                    )
                    .clip(CircleShape)
                    .background(
                        color.copy(
                            alpha = if (rank == 1) 0.16f else 0.12f
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {

                if (rank == 1) {

                    Icon(
                        imageVector = Icons.Default.EmojiEvents,
                        contentDescription = null,
                        tint = color,
                        modifier = Modifier.size(27.dp)
                    )

                } else {

                    Text(
                        text = "#$rank",
                        color = color,
                        fontWeight = FontWeight.Black,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(
                    if (rank == 1) 10.dp else 8.dp
                )
            )

            // ====================================================
            // NAME
            // ====================================================

            Text(
                text = name,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = if (rank == 1) 14.sp else 12.sp,
                fontWeight = if (rank == 1) {
                    FontWeight.Black
                } else {
                    FontWeight.Bold
                }
            )

            Spacer(modifier = Modifier.height(5.dp))

            // ====================================================
            // SCORE
            // ====================================================

            Text(
                text = "$score",
                color = color,
                fontSize = if (rank == 1) 21.sp else 16.sp,
                fontWeight = FontWeight.Black
            )

            Text(
                text = "SCORE",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 7.sp,
                fontWeight = FontWeight.Bold
            )

            // ====================================================
            // WINNER LABEL
            // ====================================================

            if (rank == 1) {

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "CHAMPION",
                    color = AutoPulseWarning,
                    fontSize = 7.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.8.sp
                )
            }
        }
    }
}


// ============================================================
// RANKING ITEM
// ============================================================

@Composable
fun RankingItem(
    rank: Int,
    ranking: Ranking
) {

    val isUser = ranking.isUser

    Surface(
        color = if (isUser) {
            AutoPulseCyan.copy(alpha = 0.08f)
        } else {
            MaterialTheme.colorScheme.surfaceVariant.copy(
                alpha = 0.35f
            )
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
                color = when {
                    rank == 1 -> AutoPulseWarning
                    rank == 2 -> Color.LightGray
                    rank == 3 -> Color(0xFFCD7F32)
                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                },
                modifier = Modifier.width(40.dp)
            )

            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(
                        if (isUser) {
                            AutoPulseCyan.copy(alpha = 0.12f)
                        } else {
                            MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.06f
                            )
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = if (isUser) {
                        AutoPulseCyan
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
                    modifier = Modifier.size(19.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = ranking.name,
                color = if (isUser) {
                    AutoPulseCyan
                } else {
                    MaterialTheme.colorScheme.onSurface
                },
                fontWeight = if (isUser) {
                    FontWeight.Bold
                } else {
                    FontWeight.Normal
                },
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "${ranking.score}",
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Black
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = "PTS",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 7.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
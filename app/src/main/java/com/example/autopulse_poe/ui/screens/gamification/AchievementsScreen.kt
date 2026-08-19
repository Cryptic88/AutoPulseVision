package com.example.autopulse_poe.ui.screens.gamification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun AchievementsScreen(
    onNavigateToLeaderboards: () -> Unit = {},
    onNavigateToChallenges: () -> Unit = {}
) {

    val badges = listOf(
        Badge(
            "Eco Warrior",
            "Maintain >30 MPG for 5 trips",
            Icons.Default.Eco,
            NeonGreen,
            true
        ),
        Badge(
            "Steady Hand",
            "Zero hard braking for 100 miles",
            Icons.Default.PanTool,
            NeonCyan,
            true
        ),
        Badge(
            "Early Bird",
            "Complete 5 trips before 8 AM",
            Icons.Default.WbSunny,
            NeonOrange,
            true
        ),
        Badge(
            "Night Owl",
            "Drive 10 miles at night",
            Icons.Default.NightsStay,
            NeonPurple,
            false
        ),
        Badge(
            "Master Mechanic",
            "Diagnose and resolve 3 DTCs",
            Icons.Default.Build,
            NeonMagenta,
            false
        ),
        Badge(
            "Long Hauler",
            "Complete a single trip over 200 miles",
            Icons.Default.Route,
            NeonBlue,
            false
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(horizontal = 20.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        // ----------------------------------------------------
        // HEADER
        // ----------------------------------------------------

        Text(
            text = "Driver Profile",
            fontSize = 28.sp,
            fontWeight = FontWeight.Black,
            color = Color.White
        )

        Text(
            text = "Your AutoPulse driving journey",
            color = Color.White.copy(alpha = 0.55f),
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // ----------------------------------------------------
        // DRIVER LEVEL
        // ----------------------------------------------------

        NeonCard(
            borderColor = NeonCyan.copy(alpha = 0.7f)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(NeonCyan.copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "12",
                            color = NeonCyan,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Black
                        )

                        Text(
                            text = "LVL",
                            color = NeonCyan.copy(alpha = 0.6f),
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "ROAD MASTER",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Black
                    )

                    Text(
                        text = "4,250 / 5,000 XP",
                        color = Color.White.copy(alpha = 0.55f),
                        fontSize = 11.sp,
                        modifier = Modifier.padding(top = 3.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LinearProgressIndicator(
                        progress = { 0.85f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(7.dp)
                            .clip(CircleShape),
                        color = NeonCyan,
                        trackColor = Color.White.copy(alpha = 0.08f)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Default.EmojiEvents,
                        contentDescription = null,
                        tint = NeonOrange,
                        modifier = Modifier.size(25.dp)
                    )

                    Text(
                        text = "TOP 5%",
                        color = NeonOrange,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ----------------------------------------------------
        // DRIVING SCORES
        // ----------------------------------------------------

        Text(
            text = "DRIVING PERFORMANCE",
            color = NeonCyan,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            ScoreCard(
                label = "DAILY",
                score = 88,
                color = NeonCyan,
                modifier = Modifier.weight(1f)
            )

            ScoreCard(
                label = "WEEKLY",
                score = 92,
                color = AutoPulseMagenta,
                modifier = Modifier.weight(1f)
            )

            ScoreCard(
                label = "MONTHLY",
                score = 85,
                color = NeonPurple,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // ----------------------------------------------------
        // STREAK
        // ----------------------------------------------------

        NeonCard(
            borderColor = NeonOrange.copy(alpha = 0.5f)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(NeonOrange.copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.LocalFireDepartment,
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
                        text = "7 DAY STREAK",
                        color = Color.White,
                        fontWeight = FontWeight.Black,
                        fontSize = 14.sp
                    )

                    Text(
                        text = "Keep driving safely to extend your streak",
                        color = Color.White.copy(alpha = 0.5f),
                        fontSize = 11.sp,
                        modifier = Modifier.padding(top = 3.dp)
                    )
                }

                Text(
                    text = "+150 XP",
                    color = NeonOrange,
                    fontWeight = FontWeight.Black,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ----------------------------------------------------
        // NAVIGATION
        // ----------------------------------------------------

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Button(
                onClick = onNavigateToLeaderboards,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = NeonOrange,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    Icons.Default.EmojiEvents,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.width(7.dp))

                Text(
                    "Leaderboard",
                    fontWeight = FontWeight.Bold
                )
            }

            Button(
                onClick = onNavigateToChallenges,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = NeonPurple
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    Icons.Default.Flag,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.width(7.dp))

                Text(
                    "Challenges",
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ----------------------------------------------------
        // BADGES
        // ----------------------------------------------------

        Text(
            text = "BADGE GALLERY",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 30.dp)
        ) {

            items(badges) { badge ->
                BadgeItem(badge)
            }
        }
    }
}

data class Badge(
    val title: String,
    val desc: String,
    val icon: ImageVector,
    val color: Color,
    val unlocked: Boolean
)

@Composable
fun ScoreCard(
    label: String,
    score: Int,
    color: Color,
    modifier: Modifier = Modifier
) {

    NeonCard(
        borderColor = color.copy(alpha = 0.35f),
        modifier = modifier
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = label,
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = score.toString(),
                color = color,
                fontSize = 26.sp,
                fontWeight = FontWeight.Black
            )

            Text(
                text = "/100",
                color = Color.White.copy(alpha = 0.35f),
                fontSize = 9.sp
            )
        }
    }
}

@Composable
fun BadgeItem(badge: Badge) {

    val alpha = if (badge.unlocked) 1f else 0.35f

    NeonCard(
        borderColor = if (badge.unlocked) {
            badge.color.copy(alpha = 0.6f)
        } else {
            Color.White.copy(alpha = 0.08f)
        },
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            Box(
                modifier = Modifier
                    .size(58.dp)
                    .clip(CircleShape)
                    .background(
                        if (badge.unlocked)
                            badge.color.copy(alpha = 0.15f)
                        else
                            Color.White.copy(alpha = 0.04f)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = badge.icon,
                    contentDescription = null,
                    tint = if (badge.unlocked) {
                        badge.color
                    } else {
                        Color.White.copy(alpha = 0.2f)
                    },
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = badge.title,
                color = Color.White.copy(alpha = alpha),
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )

            Text(
                text = badge.desc,
                color = Color.White.copy(alpha = alpha * 0.6f),
                fontSize = 10.sp,
                lineHeight = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 5.dp)
            )

            if (badge.unlocked) {

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = badge.color,
                        modifier = Modifier.size(13.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "UNLOCKED",
                        color = badge.color,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            } else {

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        Icons.Default.Lock,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.25f),
                        modifier = Modifier.size(12.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "LOCKED",
                        color = Color.White.copy(alpha = 0.25f),
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }
    }
}
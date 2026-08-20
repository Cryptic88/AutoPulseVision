package com.example.autopulse_poe.ui.screens.gamification

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.autopulse_poe.ui.theme.AutoPulseCyan
import com.example.autopulse_poe.ui.theme.AutoPulsePurple
import com.example.autopulse_poe.ui.theme.AutoPulseSuccess
import com.example.autopulse_poe.ui.theme.AutoPulseWarning

@Composable
fun AchievementsScreen(
    onNavigateToLeaderboards: () -> Unit = {},
    onNavigateToChallenges: () -> Unit = {},
    onNavigateToBadgeGallery: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        // ====================================================
        // SCROLLABLE CONTENT
        // ====================================================

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 20.dp,
                    bottom = 40.dp
                )
        ) {

            // ====================================================
            // HEADER
            // ====================================================

            Text(
                text = "Driver Profile",
                fontSize = 28.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Your AutoPulse driving journey",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // ====================================================
            // DRIVER LEVEL
            // ====================================================

            NeonCard(
                borderColor = AutoPulseCyan.copy(alpha = 0.55f)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // LEVEL CIRCLE

                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(
                                AutoPulseCyan.copy(alpha = 0.12f)
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = "12",
                                color = AutoPulseCyan,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Black
                            )

                            Text(
                                text = "LVL",
                                color = AutoPulseCyan.copy(alpha = 0.65f),
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // XP INFORMATION

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "ROAD MASTER",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Black
                        )

                        Text(
                            text = "4,250 / 5,000 XP",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
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
                            color = AutoPulseCyan,
                            trackColor = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.08f
                            )
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // RANK

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Icon(
                            imageVector = Icons.Default.EmojiEvents,
                            contentDescription = null,
                            tint = AutoPulseWarning,
                            modifier = Modifier.size(25.dp)
                        )

                        Text(
                            text = "TOP 5%",
                            color = AutoPulseWarning,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ====================================================
            // DRIVING PERFORMANCE
            // ====================================================

            Text(
                text = "DRIVING PERFORMANCE",
                color = AutoPulseCyan,
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
                    color = AutoPulseCyan,
                    modifier = Modifier.weight(1f)
                )

                ScoreCard(
                    label = "WEEKLY",
                    score = 92,
                    color = AutoPulseSuccess,
                    modifier = Modifier.weight(1f)
                )

                ScoreCard(
                    label = "MONTHLY",
                    score = 85,
                    color = AutoPulsePurple,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // ====================================================
            // STREAK
            // ====================================================

            NeonCard(
                borderColor = AutoPulseWarning.copy(alpha = 0.50f)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // FIRE ICON

                    Box(
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape)
                            .background(
                                AutoPulseWarning.copy(alpha = 0.12f)
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.LocalFireDepartment,
                            contentDescription = null,
                            tint = AutoPulseWarning,
                            modifier = Modifier.size(25.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    // STREAK INFORMATION

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "7 DAY STREAK",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Black,
                            fontSize = 14.sp
                        )

                        Text(
                            text = "Keep driving safely to extend your streak",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(top = 3.dp)
                        )
                    }

                    // BONUS

                    Column(
                        horizontalAlignment = Alignment.End
                    ) {

                        Text(
                            text = "+150 XP",
                            color = AutoPulseWarning,
                            fontWeight = FontWeight.Black,
                            fontSize = 12.sp
                        )

                        Text(
                            text = "STREAK BONUS",
                            color = AutoPulseWarning.copy(alpha = 0.60f),
                            fontSize = 7.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            // ====================================================
            // GAMIFICATION NAVIGATION
            // ====================================================

            Text(
                text = "EXPLORE GAMIFICATION",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // ====================================================
            // LEADERBOARDS + CHALLENGES
            // ====================================================

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                // ------------------------------------------------
                // LEADERBOARDS
                // ------------------------------------------------

                GamificationNavButton(
                    icon = Icons.Default.EmojiEvents,
                    title = "Leaderboards",
                    subtitle = "See how you rank",
                    color = AutoPulseWarning,
                    onClick = onNavigateToLeaderboards,
                    modifier = Modifier.weight(1f)
                )

                // ------------------------------------------------
                // CHALLENGES
                // ------------------------------------------------

                GamificationNavButton(
                    icon = Icons.Default.Flag,
                    title = "Challenges",
                    subtitle = "Weekly goals",
                    color = AutoPulsePurple,
                    onClick = onNavigateToChallenges,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ====================================================
            // BADGE COLLECTION BUTTON
            // ====================================================

            Text(
                text = "BADGES",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = onNavigateToBadgeGallery,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(68.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = AutoPulseCyan.copy(alpha = 0.04f)
                ),
                border = BorderStroke(
                    width = 1.5.dp,
                    color = AutoPulseCyan.copy(alpha = 0.45f)
                ),
                contentPadding = PaddingValues(
                    horizontal = 16.dp
                )
            ) {

                // BADGE ICON

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(
                            AutoPulseCyan.copy(alpha = 0.12f)
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.MilitaryTech,
                        contentDescription = null,
                        tint = AutoPulseCyan,
                        modifier = Modifier.size(23.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                // BADGE INFORMATION

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {

                    Text(
                        text = "Badge Collection",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Black
                    )

                    Text(
                        text = "3 of 6 badges unlocked",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 9.sp,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }

                // ARROW

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "Open badge collection",
                    tint = AutoPulseCyan,
                    modifier = Modifier.size(24.dp)
                )
            }

            // ====================================================
            // BOTTOM SPACING
            // ====================================================

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}


// ============================================================
// SCORE CARD
// ============================================================

@Composable
private fun ScoreCard(
    label: String,
    score: Int,
    color: Color,
    modifier: Modifier = Modifier
) {

    NeonCard(
        borderColor = color.copy(alpha = 0.30f),
        modifier = modifier
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = label,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
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
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                    alpha = 0.65f
                ),
                fontSize = 9.sp
            )
        }
    }
}


// ============================================================
// GAMIFICATION NAVIGATION BUTTON
// ============================================================

@Composable
private fun GamificationNavButton(
    icon: ImageVector,
    title: String,
    subtitle: String,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .height(105.dp),
        shape = RoundedCornerShape(16.dp),
        contentPadding = PaddingValues(
            horizontal = 8.dp,
            vertical = 10.dp
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = color.copy(alpha = 0.04f),
            contentColor = color
        ),
        border = BorderStroke(
            width = 1.5.dp,
            color = color.copy(alpha = 0.50f)
        )
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ICON

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(
                        color.copy(alpha = 0.12f)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = color,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.height(7.dp))

            // TITLE

            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 11.sp,
                fontWeight = FontWeight.Black,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(2.dp))

            // SUBTITLE

            Text(
                text = subtitle,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 8.sp,
                maxLines = 1
            )
        }
    }
}
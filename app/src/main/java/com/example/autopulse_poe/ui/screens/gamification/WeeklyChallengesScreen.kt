package com.example.autopulse_poe.ui.screens.gamification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
fun WeeklyChallengesScreen(onBack: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
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
                    text = "Weekly Challenges",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )

                Text(
                    text = "Turn your driving into XP",
                    color = Color.White.copy(alpha = 0.5f),
                    fontSize = 10.sp
                )
            }
        }

        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {

            // ------------------------------------------------
            // WEEKLY XP SUMMARY
            // ------------------------------------------------

            NeonCard(
                borderColor = NeonPurple.copy(alpha = 0.7f)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(NeonPurple.copy(alpha = 0.12f)),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            Icons.Default.Bolt,
                            contentDescription = null,
                            tint = NeonPurple,
                            modifier = Modifier.size(27.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "THIS WEEK",
                            color = Color.White.copy(alpha = 0.5f),
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "1,250 XP EARNED",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Black
                        )

                        Text(
                            text = "2 challenges completed",
                            color = NeonPurple,
                            fontSize = 10.sp
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.End
                    ) {

                        Text(
                            text = "3",
                            color = NeonPurple,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Black
                        )

                        Text(
                            text = "ACTIVE",
                            color = Color.White.copy(alpha = 0.4f),
                            fontSize = 8.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "ACTIVE CHALLENGES",
                color = NeonCyan,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ------------------------------------------------
            // CHALLENGE 1
            // ------------------------------------------------

            ChallengeCard(
                title = "Most Fuel-Efficient Drive",
                desc = "Maintain over 35 MPG for a single trip over 5 miles.",
                progress = 0.65f,
                icon = Icons.Default.Eco,
                color = NeonGreen,
                timeLeft = "2 days left",
                currentValue = "3.2 / 5.0 miles",
                reward = "+500 XP"
            )

            Spacer(modifier = Modifier.height(14.dp))

            // ------------------------------------------------
            // CHALLENGE 2
            // ------------------------------------------------

            ChallengeCard(
                title = "Smooth Operator",
                desc = "Complete 3 trips with zero hard braking events.",
                progress = 0.33f,
                icon = Icons.Default.DirectionsCar,
                color = NeonCyan,
                timeLeft = "4 days left",
                currentValue = "1 / 3 trips",
                reward = "+350 XP"
            )

            Spacer(modifier = Modifier.height(14.dp))

            // ------------------------------------------------
            // CHALLENGE 3
            // ------------------------------------------------

            ChallengeCard(
                title = "Early Bird Commuter",
                desc = "Finish 5 trips before 08:00 AM this week.",
                progress = 0.8f,
                icon = Icons.Default.Timer,
                color = NeonOrange,
                timeLeft = "1 day left",
                currentValue = "4 / 5 trips",
                reward = "+400 XP"
            )

            Spacer(modifier = Modifier.height(28.dp))

            // ------------------------------------------------
            // TIP
            // ------------------------------------------------

            NeonCard(
                borderColor = NeonCyan.copy(alpha = 0.25f)
            ) {

                Row(
                    verticalAlignment = Alignment.Top
                ) {

                    Icon(
                        Icons.Default.Lightbulb,
                        contentDescription = null,
                        tint = NeonCyan,
                        modifier = Modifier.size(22.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {

                        Text(
                            text = "DRIVER TIP",
                            color = NeonCyan,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Black
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Challenges are automatically tracked using your vehicle telemetry. Keep AutoPulse connected while driving to make sure your progress is recorded.",
                            color = Color.White.copy(alpha = 0.65f),
                            fontSize = 12.sp,
                            lineHeight = 17.sp
                        )
                    }
                }
            }

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
    timeLeft: String,
    currentValue: String,
    reward: String
) {

    NeonCard(
        borderColor = color.copy(alpha = 0.45f)
    ) {

        Column {

            // ------------------------------------------------
            // TITLE
            // ------------------------------------------------

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(color.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = color,
                        modifier = Modifier.size(23.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = title,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )

                    Text(
                        text = timeLeft,
                        color = color,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 3.dp)
                    )
                }

                Surface(
                    color = color.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(8.dp)
                ) {

                    Text(
                        text = reward,
                        color = color,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.padding(
                            horizontal = 8.dp,
                            vertical = 6.dp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // ------------------------------------------------
            // DESCRIPTION
            // ------------------------------------------------

            Text(
                text = desc,
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 12.sp,
                lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            // ------------------------------------------------
            // PROGRESS
            // ------------------------------------------------

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .weight(1f)
                        .height(8.dp)
                        .clip(CircleShape),
                    color = color,
                    trackColor = Color.White.copy(alpha = 0.08f)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "${(progress * 100).toInt()}%",
                    color = Color.White,
                    fontWeight = FontWeight.Black,
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = currentValue,
                color = Color.White.copy(alpha = 0.4f),
                fontSize = 10.sp
            )
        }
    }
}
package com.example.autopulse_poe.ui.screens.gamification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun BadgeGalleryScreen(
    onBack: () -> Unit
) {

    val badges = listOf(

        Badge(
            title = "Eco Warrior",
            desc = "Maintain >30 MPG for 5 trips",
            icon = Icons.Default.Eco,
            unlocked = true
        ),

        Badge(
            title = "Steady Hand",
            desc = "Zero hard braking for 100 miles",
            icon = Icons.Default.PanTool,
            unlocked = true
        ),

        Badge(
            title = "Early Bird",
            desc = "Complete 5 trips before 8 AM",
            icon = Icons.Default.WbSunny,
            unlocked = true
        ),

        Badge(
            title = "Night Owl",
            desc = "Drive 10 miles at night",
            icon = Icons.Default.NightsStay,
            unlocked = false
        ),

        Badge(
            title = "Master Mechanic",
            desc = "Diagnose and resolve 3 DTCs",
            icon = Icons.Default.Build,
            unlocked = false
        ),

        Badge(
            title = "Long Hauler",
            desc = "Complete a single trip over 200 miles",
            icon = Icons.Default.Route,
            unlocked = false
        )
    )

    val unlockedCount = badges.count { it.unlocked }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        // ============================================================
        // HEADER
        // ============================================================

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
                    text = "Badge Gallery",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "$unlockedCount of ${badges.size} badges unlocked",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }

        // ============================================================
        // PROGRESS
        // ============================================================

        Column(
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 8.dp
            )
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "COLLECTION PROGRESS",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )

                Text(
                    text = "${(unlockedCount.toFloat() / badges.size * 100).toInt()}%",
                    color = AutoPulseCyan,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Black
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = {
                    unlockedCount.toFloat() / badges.size
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(CircleShape),
                color = AutoPulseCyan,
                trackColor = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.08f
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // ============================================================
        // BADGES
        // ============================================================

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = 8.dp,
                bottom = 32.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(badges) { badge ->

                BadgeItem(
                    badge = badge
                )
            }
        }
    }
}


// ================================================================
// BADGE MODEL
// ================================================================

data class Badge(
    val title: String,
    val desc: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val unlocked: Boolean
)


// ================================================================
// BADGE ITEM
// ================================================================

@Composable
private fun BadgeItem(
    badge: Badge
) {

    val iconColor =
        if (badge.unlocked) {
            AutoPulseCyan
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant.copy(
                alpha = 0.45f
            )
        }

    val iconBackground =
        if (badge.unlocked) {
            AutoPulseCyan.copy(alpha = 0.09f)
        } else {
            MaterialTheme.colorScheme.onSurface.copy(
                alpha = 0.04f
            )
        }

    NeonCard(
        borderColor =
            if (badge.unlocked) {
                AutoPulseCyan.copy(alpha = 0.30f)
            } else {
                MaterialTheme.colorScheme.outline.copy(
                    alpha = 0.45f
                )
            },

        // ========================================================
        // IMPORTANT:
        // Every badge is EXACTLY the same height.
        // ========================================================

        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ====================================================
            // ICON
            // ====================================================

            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(iconBackground),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = badge.icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // ====================================================
            // TITLE
            // ====================================================

            Text(
                text = badge.title,
                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = if (badge.unlocked) 1f else 0.55f
                ),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(6.dp))

            // ====================================================
            // DESCRIPTION
            // ====================================================

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                contentAlignment = Alignment.TopCenter
            ) {

                Text(
                    text = badge.desc,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                        alpha = if (badge.unlocked) 0.8f else 0.45f
                    ),
                    fontSize = 10.sp,
                    lineHeight = 14.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 3
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // ====================================================
            // STATUS
            // ====================================================

            if (badge.unlocked) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = AutoPulseSuccess,
                        modifier = Modifier.size(14.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "UNLOCKED",
                        color = AutoPulseSuccess,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 0.5.sp
                    )
                }

            } else {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                            alpha = 0.45f
                        ),
                        modifier = Modifier.size(13.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "LOCKED",
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                            alpha = 0.45f
                        ),
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 0.5.sp
                    )
                }
            }
        }
    }
}
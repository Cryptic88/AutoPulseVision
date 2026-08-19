package com.example.autopulse_poe.ui.screens.diagnostics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.NeonCard
import com.example.autopulse_poe.ui.theme.*

@Composable
fun DtcHistoryScreen(onBack: () -> Unit) {

    val historyItems = listOf(
        HistoryLogItem(
            "P0300",
            "Random Misfire",
            "Detected",
            "28 July 2026",
            AutoPulseError
        ),
        HistoryLogItem(
            "P0171",
            "System Too Lean",
            "Resolved",
            "15 July 2026",
            AutoPulseCyan
        ),
        HistoryLogItem(
            "P0420",
            "Catalyst Efficiency",
            "Pending",
            "30 June 2026",
            AutoPulseWarning
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AutoPulseBackground)
    ) {

        // HEADER
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = onBack) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = AutoPulseText
                )
            }

            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    text = "DTC History",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = AutoPulseText
                )

                Text(
                    text = "Previous diagnostic events",
                    color = AutoPulseTextMuted,
                    fontSize = 10.sp
                )
            }
        }

        // SUMMARY
        NeonCard(
            borderColor = AutoPulseCyanDark.copy(alpha = 0.35f),
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    Icons.Default.History,
                    contentDescription = null,
                    tint = AutoPulseCyanDark,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "DIAGNOSTIC HISTORY",
                        color = AutoPulseCyanDark,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Black
                    )

                    Text(
                        text = "${historyItems.size} recorded events",
                        color = AutoPulseTextSecondary,
                        fontSize = 11.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                bottom = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(historyItems) { item ->
                HistoryCard(item)
            }
        }
    }
}

data class HistoryLogItem(
    val code: String,
    val desc: String,
    val status: String,
    val date: String,
    val color: Color
)

@Composable
fun HistoryCard(item: HistoryLogItem) {

    NeonCard(
        borderColor = item.color.copy(alpha = 0.35f)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(42.dp)
                    .background(
                        item.color.copy(alpha = 0.10f),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.code.take(1),
                    color = item.color,
                    fontWeight = FontWeight.Black
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = item.code,
                        color = item.color,
                        fontWeight = FontWeight.Black,
                        fontSize = 17.sp
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Surface(
                        color = item.color.copy(alpha = 0.10f),
                        shape = CircleShape
                    ) {
                        Text(
                            text = item.status.uppercase(),
                            color = item.color,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Black,
                            modifier = Modifier.padding(
                                horizontal = 7.dp,
                                vertical = 4.dp
                            )
                        )
                    }
                }

                Text(
                    text = item.desc,
                    color = AutoPulseText,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 3.dp)
                )

                Text(
                    text = item.date,
                    color = AutoPulseTextMuted,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(top = 3.dp)
                )
            }
        }
    }
}
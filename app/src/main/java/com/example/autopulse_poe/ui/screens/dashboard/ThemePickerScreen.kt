package com.example.autopulse_poe.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.components.Gauge
import com.example.autopulse_poe.ui.theme.*

@Composable
fun ThemePickerScreen(
    onBack: () -> Unit,
    darkMode: Boolean,
    onDarkModeChanged: (Boolean) -> Unit
) {

    // --------------------------------------------------------
    // GAUGE ACCENT COLOURS
    // --------------------------------------------------------

    val themes = listOf(
        NeonCyan,
        NeonMagenta,
        NeonGreen,
        NeonRed,
        NeonOrange,
        NeonPurple,
        Color.Yellow,
        Color.White
    )

    var selectedColor by remember {
        mutableStateOf(NeonCyan)
    }

    // --------------------------------------------------------
    // THEME COLORS
    // --------------------------------------------------------

    val backgroundColor = MaterialTheme.colorScheme.background
    val surfaceColor = MaterialTheme.colorScheme.surface
    val textColor = MaterialTheme.colorScheme.onBackground
    val secondaryTextColor = MaterialTheme.colorScheme.onSurfaceVariant

    // --------------------------------------------------------
    // SCREEN
    // --------------------------------------------------------

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {

        // ====================================================
        // HEADER
        // ====================================================

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onBack
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = textColor
                )
            }

            Text(
                text = "Appearance",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = textColor,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // ====================================================
        // CONTENT
        // ====================================================

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // =================================================
            // DARK / LIGHT MODE
            // =================================================

            Text(
                text = "APP THEME",
                color = secondaryTextColor,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.2.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .background(surfaceColor)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Theme icon

                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(
                            AutoPulseCyan.copy(alpha = 0.12f)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (darkMode) {
                            Icons.Default.DarkMode
                        } else {
                            Icons.Default.LightMode
                        },
                        contentDescription = null,
                        tint = AutoPulseCyan,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(
                    modifier = Modifier.width(14.dp)
                )

                // Theme description

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = if (darkMode) {
                            "Dark Mode"
                        } else {
                            "Light Mode"
                        },
                        color = textColor,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(3.dp)
                    )

                    Text(
                        text = if (darkMode) {
                            "Optimised for low-light driving"
                        } else {
                            "Bright interface for daytime use"
                        },
                        color = secondaryTextColor,
                        fontSize = 11.sp
                    )
                }

                // Toggle

                Switch(
                    checked = darkMode,
                    onCheckedChange = onDarkModeChanged,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
                        checkedTrackColor = MaterialTheme.colorScheme.primary,
                        uncheckedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant,
                        uncheckedBorderColor = MaterialTheme.colorScheme.outline
                    )
                )
            }

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            // =================================================
            // GAUGE PREVIEW
            // =================================================

            Gauge(
                value = 75f,
                maxValue = 100f,
                label = "ENGINE LOAD",
                unit = "%",
                modifier = Modifier.size(200.dp),
                color = selectedColor
            )

            Spacer(
                modifier = Modifier.height(36.dp)
            )

            // =================================================
            // ACCENT COLOUR
            // =================================================

            Text(
                text = "GAUGE ACCENT COLOUR",
                color = secondaryTextColor,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                letterSpacing = 1.2.sp,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Adaptive(60.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {

                items(themes) { color ->

                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(color)
                            .clickable {
                                selectedColor = color
                            }
                            .padding(4.dp)
                    ) {

                        if (selectedColor == color) {

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape)
                                    .background(
                                        Color.Black.copy(alpha = 0.3f)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {

                                Text(
                                    text = "✓",
                                    color = Color.White,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier.weight(1f)
            )

            // =================================================
            // APPLY BUTTON
            // =================================================

            Button(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = selectedColor
                )
            ) {

                Text(
                    text = "APPLY THEME",
                    fontWeight = FontWeight.Bold,
                    color = if (selectedColor == Color.White) {
                        Color.Black
                    } else {
                        Color.Black
                    }
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )
        }
    }
}
package com.example.autopulse_poe.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val NeonDarkColorScheme = darkColorScheme(
    primary = NeonMagenta,
    secondary = NeonCyan,
    tertiary = NeonPurple,
    background = DarkBackground,
    surface = DarkSurface,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
    error = NeonRed
)

@Composable
fun AutoPulsePOETheme(
    darkTheme: Boolean = true, // Force Dark
    dynamicColor: Boolean = false, // Disable dynamic to keep neon branding
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = DarkBackground.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = NeonDarkColorScheme,
        typography = Typography,
        content = content
    )
}

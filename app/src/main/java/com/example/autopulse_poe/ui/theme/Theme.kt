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

private val AutoPulseDarkColorScheme = darkColorScheme(

    primary = AutoPulseCyan,
    onPrimary = Color.Black,

    primaryContainer = Color(0xFF003544),
    onPrimaryContainer = AutoPulseCyan,

    secondary = AutoPulsePurple,
    onSecondary = Color.White,

    secondaryContainer = Color(0xFF211D4A),
    onSecondaryContainer = Color(0xFFD8D4FF),

    tertiary = AutoPulseSuccess,
    onTertiary = Color.Black,

    background = AutoPulseBackground,
    onBackground = AutoPulseText,

    surface = AutoPulseSurface,
    onSurface = AutoPulseText,

    surfaceVariant = AutoPulseSurfaceVariant,
    onSurfaceVariant = AutoPulseTextSecondary,

    outline = AutoPulseBorder,
    outlineVariant = AutoPulseBorderStrong,

    error = AutoPulseError,
    onError = Color.White
)

@Composable
fun AutoPulsePOETheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            window.statusBarColor = AutoPulseBackground.toArgb()

            WindowCompat
                .getInsetsController(window, view)
                .isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = AutoPulseDarkColorScheme,
        typography = Typography,
        content = content
    )
}
package com.example.autopulse_poe.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
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


private val AutoPulseLightColorScheme = lightColorScheme(

    primary = AutoPulseLightCyan,
    onPrimary = Color.White,

    primaryContainer = Color(0xFFD7F5FC),
    onPrimaryContainer = Color(0xFF003544),

    secondary = AutoPulseLightPurple,
    onSecondary = Color.White,

    secondaryContainer = Color(0xFFE9E6FF),
    onSecondaryContainer = Color(0xFF2A245F),

    tertiary = AutoPulseLightGreen,
    onTertiary = Color.White,

    background = AutoPulseLightBackground,
    onBackground = AutoPulseLightText,

    surface = AutoPulseLightSurface,
    onSurface = AutoPulseLightText,

    surfaceVariant = AutoPulseLightSurfaceVariant,
    onSurfaceVariant = AutoPulseLightTextSecondary,

    outline = AutoPulseLightBorder,
    outlineVariant = AutoPulseLightBorderStrong,

    error = AutoPulseLightRed,
    onError = Color.White
)

@Composable
fun AutoPulsePOETheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        AutoPulseDarkColorScheme
    } else {
        AutoPulseLightColorScheme
    }

    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            window.statusBarColor =
                colorScheme.background.toArgb()

            window.navigationBarColor =
                colorScheme.background.toArgb()

            WindowCompat
                .getInsetsController(window, view)
                .isAppearanceLightStatusBars = !darkTheme

            WindowCompat
                .getInsetsController(window, view)
                .isAppearanceLightNavigationBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
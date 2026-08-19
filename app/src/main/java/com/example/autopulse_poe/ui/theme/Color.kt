package com.example.autopulse_poe.ui.theme

import androidx.compose.ui.graphics.Color

// ============================================================
// AutoPulse Design System
// Automotive ECU / Telemetry Theme
// ============================================================

// ---------- Backgrounds ----------

val AutoPulseBackground = Color(0xFF050B12)
val AutoPulseSurface = Color(0xFF0B1622)
val AutoPulseSurfaceVariant = Color(0xFF101E2C)
val AutoPulseSurfaceElevated = Color(0xFF142536)

// ---------- Borders ----------

val AutoPulseBorder = Color(0xFF1A3445)
val AutoPulseBorderStrong = Color(0xFF24465A)

// ---------- Brand ----------

val AutoPulseCyan = Color(0xFF00D4FF)
val AutoPulseCyanDark = Color(0xFF009CC2)
val AutoPulseBlue = Color(0xFF3B82F6)

val AutoPulseMagenta = Color(0xFFC2185B)

// ---------- Status ----------

val AutoPulseSuccess = Color(0xFF22C55E)
val AutoPulseWarning = Color(0xFFF59E0B)
val AutoPulseError = Color(0xFFEF4444)

// ---------- Secondary Accent ----------

val AutoPulsePurple = Color(0xFF7C6CFF)

// ---------- Typography ----------

val AutoPulseText = Color(0xFFF4F7FA)
val AutoPulseTextSecondary = Color(0xFF94A3B8)
val AutoPulseTextMuted = Color(0xFF64748B)

// ---------- Utility ----------

val AutoPulseOverlay = Color(0x66000000)

// ------------------------------------------------------------
// Compatibility aliases
// Keep these temporarily so existing screens don't break.
// We can remove them later once the screens are refactored.
// ------------------------------------------------------------

val DarkBackground = AutoPulseBackground
val DarkSurface = AutoPulseSurface
val GlassSurface = Color(0x141E3445)

val NeonCyan = AutoPulseCyan
val NeonPurple = AutoPulsePurple
val NeonGreen = AutoPulseSuccess
val NeonRed = AutoPulseError
val NeonOrange = AutoPulseWarning

val NeonBlue = Color(0xFF3B82F6)
val NeonMagenta = AutoPulsePurple

// Material defaults retained for compatibility
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

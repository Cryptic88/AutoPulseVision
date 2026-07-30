package com.example.autopulse_poe.ui.screens.onboarding

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNext: () -> Unit) {
    val scale = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1.2f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(1500)
        onNext()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.scale(scale.value)
        ) {
            Icon(
                Icons.Default.DirectionsCar,
                contentDescription = null,
                tint = NeonCyan,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "AutoPulse",
                fontSize = 42.sp,
                fontWeight = FontWeight.Black,
                color = Color.White
            )
            Text(
                text = "Intelligence in Motion",
                fontSize = 16.sp,
                color = NeonCyan,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// Simple OvershootInterpolator for Compose
class OvershootInterpolator(private val tension: Float) {
    fun getInterpolation(t: Float): Float {
        var time = t
        time -= 1.0f
        return time * time * ((tension + 1) * time + tension) + 1.0f
    }
}

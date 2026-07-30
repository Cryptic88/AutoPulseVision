package com.example.autopulse_poe.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopulse_poe.ui.theme.*

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, onNavigateToRegister: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo Section
        Icon(
            Icons.Default.DirectionsCar,
            contentDescription = null,
            tint = NeonCyan,
            modifier = Modifier.size(80.dp)
        )
        Text(
            text = "AutoPulse",
            fontSize = 40.sp,
            fontWeight = FontWeight.Black,
            color = Color.White
        )
        Text(
            text = "Vehicle Intelligence",
            fontSize = 14.sp,
            color = NeonCyan,
            modifier = Modifier.padding(bottom = 64.dp)
        )

        // Login Button
        Button(
            onClick = onLoginSuccess,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(listOf(NeonCyan, NeonPurple)),
                        shape = MaterialTheme.shapes.medium
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Continue with Google",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        TextButton(onClick = { /* Biometric Login */ }) {
            Text(text = "Use Biometric Login", color = Color.White.copy(alpha = 0.6f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onNavigateToRegister) {
            Text(text = "New to AutoPulse? Create Account", color = NeonCyan)
        }
    }
}

@Composable
fun RegisterScreen(onRegisterSuccess: () -> Unit, onBackToLogin: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Create Account",
            fontSize = 32.sp,
            fontWeight = FontWeight.Black,
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Join the future of driving",
            fontSize = 14.sp,
            color = NeonCyan,
            modifier = Modifier.padding(bottom = 48.dp)
        )

        // Using simple text fields for UI demo
        AuthTextField(label = "Full Name")
        AuthTextField(label = "Email Address")
        AuthTextField(label = "Password")

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onRegisterSuccess,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = NeonPurple)
        ) {
            Text("Create Account", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        TextButton(onClick = onBackToLogin) {
            Text(text = "Already have an account? Log In", color = Color.White.copy(alpha = 0.6f))
        }
    }
}

@Composable
fun AuthTextField(label: String) {
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        Surface(
            color = Color.White.copy(alpha = 0.05f),
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = label,
                color = Color.White.copy(alpha = 0.3f),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

package com.example.autopulse_poe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.autopulse_poe.ui.components.MainScaffold
import com.example.autopulse_poe.ui.theme.AutoPulsePOETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var darkMode by rememberSaveable { mutableStateOf(true) }

            AutoPulsePOETheme(darkTheme = darkMode) {
                MainScaffold(
                    darkMode = darkMode,
                    onDarkModeChanged = { enabled -> darkMode = enabled }
                )
            }
        }
    }
}

@Composable
fun MainPreview() {
    var darkMode by remember { mutableStateOf(true) }

    AutoPulsePOETheme(darkTheme = darkMode) {
        MainScaffold(
            darkMode = darkMode,
            onDarkModeChanged = { enabled -> darkMode = enabled }
        )
    }
}
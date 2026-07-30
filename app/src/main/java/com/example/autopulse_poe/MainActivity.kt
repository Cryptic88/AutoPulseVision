package com.example.autopulse_poe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.autopulse_poe.ui.components.MainScaffold
import com.example.autopulse_poe.ui.theme.AutoPulsePOETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AutoPulsePOETheme {
                MainScaffold()
            }
        }
    }
}

@Composable
fun MainPreview() {
    AutoPulsePOETheme {
        MainScaffold()
    }
}

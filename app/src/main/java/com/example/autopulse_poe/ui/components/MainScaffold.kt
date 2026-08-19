package com.example.autopulse_poe.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.autopulse_poe.ui.navigation.NavGraph
import com.example.autopulse_poe.ui.navigation.Screen
import com.example.autopulse_poe.ui.theme.AutoPulseBackground
import com.example.autopulse_poe.ui.theme.AutoPulseCyan
import com.example.autopulse_poe.ui.theme.AutoPulseText
import com.example.autopulse_poe.ui.theme.AutoPulseTextMuted
import com.example.autopulse_poe.ui.theme.DarkBackground
import com.example.autopulse_poe.ui.theme.NeonCyan
import com.example.autopulse_poe.ui.theme.NeonGreen

@Composable
fun MainScaffold() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomBar = Screen.bottomNavItems.any { it.route == currentDestination?.route }
    
    var isSyncing by remember { mutableStateOf(false) }
    var connectionStatus by remember { mutableStateOf("CONNECTED") }

    Scaffold(
        topBar = {
            if (showBottomBar) {
                ConnectionStatusBanner(status = "ADAPTER $connectionStatus", color = NeonGreen)
            }
        },
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = AutoPulseBackground,
                    contentColor = AutoPulseText,
                    tonalElevation = 0.dp
                ) {
                    Screen.bottomNavItems.forEach { screen ->

                        val selected =
                            currentDestination
                                ?.hierarchy
                                ?.any { it.route == screen.route } == true

                        NavigationBarItem(
                            icon = {
                                screen.icon?.let {
                                    Icon(
                                        imageVector = it,
                                        contentDescription = screen.title,
                                        tint = if (selected) {
                                            AutoPulseCyan
                                        } else {
                                            AutoPulseTextMuted
                                        }
                                    )
                                }
                            },

                            label = {
                                Text(
                                    text = screen.title,
                                    color = if (selected) {
                                        AutoPulseCyan
                                    } else {
                                        AutoPulseTextMuted
                                    }
                                )
                            },

                            selected = selected,

                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = AutoPulseCyan,
                                selectedTextColor = AutoPulseCyan,
                                unselectedIconColor = AutoPulseTextMuted,
                                unselectedTextColor = AutoPulseTextMuted,
                                indicatorColor = AutoPulseCyan.copy(alpha = 0.10f)
                            ),

                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(
                                        navController.graph.findStartDestination().id
                                    ) {
                                        saveState = true
                                    }

                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavGraph(navController = navController)
            
            if (isSyncing) {
                SyncOverlay()
            }
        }
    }
}

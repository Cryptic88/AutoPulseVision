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
                    containerColor = DarkBackground,
                    contentColor = Color.White,
                    tonalElevation = 8.dp
                ) {
                    Screen.bottomNavItems.forEach { screen ->
                        val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                        NavigationBarItem(
                            icon = { 
                                screen.icon?.let { 
                                    Icon(
                                        it, 
                                        contentDescription = screen.title,
                                        tint = if (selected) NeonCyan else Color.Gray
                                    ) 
                                } 
                            },
                            label = { 
                                Text(
                                    screen.title, 
                                    color = if (selected) NeonCyan else Color.Gray
                                ) 
                            },
                            selected = selected,
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = NeonCyan.copy(alpha = 0.1f)
                            ),
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
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

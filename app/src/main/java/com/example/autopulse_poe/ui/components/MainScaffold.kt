package com.example.autopulse_poe.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.autopulse_poe.ui.navigation.NavGraph
import com.example.autopulse_poe.ui.navigation.Screen
import com.example.autopulse_poe.ui.theme.AutoPulseCyan
import com.example.autopulse_poe.ui.theme.NeonGreen

@Composable
fun MainScaffold(
    darkMode: Boolean,
    onDarkModeChanged: (Boolean) -> Unit
) {

    val navController = rememberNavController()

    val navBackStackEntry by
    navController.currentBackStackEntryAsState()

    val currentDestination =
        navBackStackEntry?.destination

    val showBottomBar =
        Screen.bottomNavItems.any {
            it.route == currentDestination?.route
        }

    var isSyncing by remember {
        mutableStateOf(false)
    }

    var connectionStatus by remember {
        mutableStateOf("CONNECTED")
    }


    Scaffold(

        // ============================================================
        // TOP BAR
        // ============================================================

        topBar = {

            if (showBottomBar) {

                ConnectionStatusBanner(
                    status = "ADAPTER $connectionStatus",
                    color = NeonGreen
                )
            }
        },


        // ============================================================
        // BOTTOM NAVIGATION
        // ============================================================

        bottomBar = {

            if (showBottomBar) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.surface
                        )
                ) {

                    // ====================================================
                    // CYAN NEON GLOW
                    // ====================================================

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .drawBehind {

                                // Soft outer glow
                                drawRect(
                                    color = AutoPulseCyan.copy(
                                        alpha = 0.12f
                                    )
                                )

                                // Stronger inner glow
                                drawRect(
                                    color = AutoPulseCyan.copy(
                                        alpha = 0.18f
                                    ),
                                    size = size.copy(
                                        height = 2.dp.toPx()
                                    )
                                )
                            }
                    )


                    // ====================================================
                    // NAVIGATION BAR
                    // ====================================================

                    NavigationBar(

                        containerColor =
                            MaterialTheme.colorScheme.surface,

                        contentColor =
                            MaterialTheme.colorScheme.onSurface,

                        tonalElevation = 0.dp,

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 3.dp)
                    ) {

                        Screen.bottomNavItems.forEach { screen ->

                            val selected =
                                currentDestination
                                    ?.hierarchy
                                    ?.any {
                                        it.route == screen.route
                                    } == true


                            NavigationBarItem(

                                // ------------------------------------------------
                                // ICON
                                // ------------------------------------------------

                                icon = {

                                    screen.icon?.let {

                                        Icon(
                                            imageVector = it,
                                            contentDescription =
                                                screen.title,

                                            tint =
                                                if (selected) {

                                                    AutoPulseCyan

                                                } else {

                                                    MaterialTheme
                                                        .colorScheme
                                                        .onSurfaceVariant
                                                }
                                        )
                                    }
                                },


                                // ------------------------------------------------
                                // LABEL
                                // ------------------------------------------------

                                label = {

                                    Text(
                                        text = screen.title,

                                        color =
                                            if (selected) {

                                                AutoPulseCyan

                                            } else {

                                                MaterialTheme
                                                    .colorScheme
                                                    .onSurfaceVariant
                                            }
                                    )
                                },


                                selected = selected,


                                // ------------------------------------------------
                                // NAVIGATION ITEM COLOURS
                                // ------------------------------------------------

                                colors =
                                    NavigationBarItemDefaults.colors(

                                        selectedIconColor =
                                            AutoPulseCyan,

                                        selectedTextColor =
                                            AutoPulseCyan,

                                        unselectedIconColor =
                                            MaterialTheme
                                                .colorScheme
                                                .onSurfaceVariant,

                                        unselectedTextColor =
                                            MaterialTheme
                                                .colorScheme
                                                .onSurfaceVariant,

                                        indicatorColor =
                                            AutoPulseCyan.copy(
                                                alpha = 0.10f
                                            )
                                    ),


                                // ------------------------------------------------
                                // NAVIGATION
                                // ------------------------------------------------

                                onClick = {

                                    navController.navigate(
                                        screen.route
                                    ) {

                                        popUpTo(
                                            navController
                                                .graph
                                                .findStartDestination()
                                                .id
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
        }

    ) { innerPadding ->


        // ============================================================
        // MAIN CONTENT
        // ============================================================

        Box(
            modifier = Modifier
                .padding(innerPadding)
        ) {

            NavGraph(
                navController = navController,
                darkMode = darkMode,
                onDarkModeChanged = onDarkModeChanged
            )


            // ========================================================
            // SYNC OVERLAY
            // ========================================================

            if (isSyncing) {

                SyncOverlay()
            }
        }
    }
}
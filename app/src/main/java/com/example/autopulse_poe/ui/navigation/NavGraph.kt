package com.example.autopulse_poe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.autopulse_poe.ui.screens.ai.AiAssistantScreen
import com.example.autopulse_poe.ui.screens.analytics.*
import com.example.autopulse_poe.ui.screens.auth.LoginScreen
import com.example.autopulse_poe.ui.screens.auth.RegisterScreen
import com.example.autopulse_poe.ui.screens.dashboard.*
import com.example.autopulse_poe.ui.screens.diagnostics.*
import com.example.autopulse_poe.ui.screens.fuel.FuelManagementScreen
import com.example.autopulse_poe.ui.screens.gamification.*
import com.example.autopulse_poe.ui.screens.hud.*
import com.example.autopulse_poe.ui.screens.maintenance.MaintenanceScreen
import com.example.autopulse_poe.ui.screens.onboarding.SplashScreen
import com.example.autopulse_poe.ui.screens.onboarding.OnboardingScreen
import com.example.autopulse_poe.ui.screens.onboarding.PermissionsScreen
import com.example.autopulse_poe.ui.screens.performance.*
import com.example.autopulse_poe.ui.screens.reports.*
import com.example.autopulse_poe.ui.screens.settings.*
import com.example.autopulse_poe.ui.screens.trips.TripDetailsScreen
import com.example.autopulse_poe.ui.screens.trips.TripsScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(onNext = {
                navController.navigate(Screen.Onboarding.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }
        composable(Screen.Onboarding.route) {
            OnboardingScreen(onFinish = {
                navController.navigate(Screen.Permissions.route)
            })
        }
        composable(Screen.Permissions.route) {
            PermissionsScreen(onFinish = {
                navController.navigate(Screen.Auth.route) {
                    popUpTo(Screen.Onboarding.route) { inclusive = true }
                }
            })
        }
        composable(Screen.Home.route) { 
            DashboardScreen(
                onNavigateToHUD = { navController.navigate(Screen.HUD.route) },
                onNavigateToAi = { navController.navigate(Screen.AiAssistant.route) },
                onNavigateToDiagnostics = { navController.navigate(Screen.DiagnosticsMain.route) },
                onNavigateToTrips = { navController.navigate(Screen.TripHistory.route) },
                onNavigateToSettings = { navController.navigate(Screen.Profile.route) },
                onNavigateToAchievements = { navController.navigate(Screen.Achievements.route) },
                onNavigateToPerformance = { navController.navigate(Screen.Performance.route) },
                onNavigateToFuel = { navController.navigate(Screen.FuelHub.route) }
            ) 
        }
        composable(Screen.DashboardEditor.route) { 
            DashboardEditorScreen(
                onBack = { navController.popBackStack() },
                onNavigateToThemePicker = { navController.navigate(Screen.ThemePicker.route) }
            ) 
        }
        composable(Screen.ThemePicker.route) { ThemePickerScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.FuelHub.route) { FuelManagementScreen() }
        
        composable(Screen.DiagnosticsMain.route) {
            DiagnosticsMainScreen(
                onNavigateToDtcList = { navController.navigate(Screen.DtcList.route) },
                onNavigateToAdvanced = { navController.navigate(Screen.Emissions.route) },
                onNavigateToFreezeFrame = { navController.navigate(Screen.FreezeFrame.route) },
                onNavigateToVehicleInfo = { navController.navigate(Screen.VehicleInfo.route) },
                onNavigateToHistory = { navController.navigate(Screen.DtcHistory.route) }
            )
        }
        composable(Screen.DtcList.route) {
            DtcListScreen(
                onBack = { navController.popBackStack() },
                onNavigateToDetail = { navController.navigate(Screen.DtcDetail.route) }
            )
        }
        composable(Screen.Alerts.route) { 
            DiagnosticsMainScreen(
                onNavigateToDtcList = { navController.navigate(Screen.DtcList.route) },
                onNavigateToAdvanced = { navController.navigate(Screen.Emissions.route) },
                onNavigateToFreezeFrame = { navController.navigate(Screen.FreezeFrame.route) },
                onNavigateToVehicleInfo = { navController.navigate(Screen.VehicleInfo.route) },
                onNavigateToHistory = { navController.navigate(Screen.DtcHistory.route) },
                onClearCodes = { }
            ) 
        }
        composable(Screen.Performance.route) { 
            PerformanceMenuScreen(
                onNavigateToMetrics = { navController.navigate(Screen.PerformanceMetrics.route) },
                onNavigateToAcceleration = { navController.navigate(Screen.AccelerationTimer.route) },
                onNavigateToBraking = { navController.navigate(Screen.BrakingDistance.route) },
                onNavigateToCustomPids = { navController.navigate(Screen.CustomPids.route) },
                onNavigateToStock = { navController.navigate(Screen.StockComparison.route) }
            ) 
        }
        composable(Screen.PerformanceMetrics.route) {
            PerformanceMetricsHubScreen(
                onBack = { navController.popBackStack() },
                onNavigateToDyno = {
                    navController.navigate(Screen.Dyno.route)
                }
            )
        }
        composable(Screen.TripHistory.route) { 
            TripsScreen(onNavigateToDetails = { navController.navigate(Screen.TripDetails.route) }) 
        }
        composable(Screen.Profile.route) { 
            SettingsScreen(
                onNavigateToVehicleEditor = { navController.navigate(Screen.VehicleEditor.route) },
                onNavigateToBluetooth = { navController.navigate(Screen.BluetoothSettings.route) },
                onNavigateToVehicleProfiles = { navController.navigate(Screen.VehicleProfiles.route) },
                onNavigateToEditProfile = { navController.navigate(Screen.EditProfile.route) }
            ) 
        }
        composable(Screen.EditProfile.route) { EditProfileScreen(onBack = { navController.popBackStack() }) }
        
        composable(Screen.Auth.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Auth.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = { navController.navigate(Screen.Register.route) }
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                },
                onBackToLogin = { navController.popBackStack() }
            )
        }

        composable(Screen.AiAssistant.route) { AiAssistantScreen() }
        composable(Screen.DtcDetail.route) { DtcDetailScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.DtcHistory.route) { DtcHistoryScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.FreezeFrame.route) { FreezeFrameScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.VehicleInfo.route) { VehicleInfoScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.TripDetails.route) { TripDetailsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.VehicleEditor.route) { VehicleEditorScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.Achievements.route) { 
            AchievementsScreen(
                onNavigateToLeaderboards = { navController.navigate(Screen.Leaderboards.route) },
                onNavigateToChallenges = { navController.navigate(Screen.WeeklyChallenges.route) }
            )
        }
        
        composable(Screen.Dyno.route) { AdvancedPerformanceScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.AccelerationTimer.route) { AccelerationTimerScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.BrakingDistance.route) { BrakingDistanceScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.CustomPids.route) { CustomPidsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.StockComparison.route) { StockComparisonScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.Emissions.route) { AdvancedDiagnosticsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.Leaderboards.route) { LeaderboardsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.WeeklyChallenges.route) { WeeklyChallengesScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.Maintenance.route) { MaintenanceScreen(onBack = { navController.popBackStack() }) }

        composable(Screen.ReportsHub.route) { 
            ReportsHubScreen(
                onNavigateToMechanic = { navController.navigate(Screen.MechanicDashboard.route) },
                onNavigateToViewer = { navController.navigate(Screen.ReportViewer.route) }
            ) 
        }
        composable(Screen.ReportViewer.route) { ReportViewerScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.AnalyticsDashboard.route) { AnalyticsDashboardScreen() }
        composable(Screen.FleetDashboard.route) { FleetDashboardScreen() }
        composable(Screen.MechanicDashboard.route) { MechanicDashboardScreen(onBack = { navController.popBackStack() }) }
        
        composable(Screen.BluetoothSettings.route) { BluetoothSettingsScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.VehicleProfiles.route) { 
            VehicleProfilesScreen(
                onBack = { navController.popBackStack() },
                onAddVehicle = { navController.navigate(Screen.VehicleEditor.route) }
            ) 
        }

        composable(Screen.HudCustomization.route) { HudCustomizationScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.HUD.route) { HUDScreen() }
        composable(Screen.BackupRestore.route) { BackupRestoreScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.PrivacyControls.route) { PrivacyControlsScreen(onBack = { navController.popBackStack() }) }
    }
}

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
import com.example.autopulse_poe.ui.screens.onboarding.OnboardingScreen
import com.example.autopulse_poe.ui.screens.onboarding.PermissionsScreen
import com.example.autopulse_poe.ui.screens.onboarding.SplashScreen
import com.example.autopulse_poe.ui.screens.performance.*
import com.example.autopulse_poe.ui.screens.reports.*
import com.example.autopulse_poe.ui.screens.settings.*
import com.example.autopulse_poe.ui.screens.trips.TripDetailsScreen
import com.example.autopulse_poe.ui.screens.trips.TripsScreen

/**
 * Dedicated route for the badge gallery.
 *
 * If BadgeGallery already exists inside Screen.kt,
 * you can replace this constant with Screen.BadgeGallery.route.
 */
private const val BADGE_GALLERY_ROUTE = "badge_gallery"

@Composable
fun NavGraph(
    navController: NavHostController,

    // --------------------------------------------------------
    // THEME STATE
    // --------------------------------------------------------

    darkMode: Boolean,
    onDarkModeChanged: (Boolean) -> Unit
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        // ====================================================
        // SPLASH
        // ====================================================

        composable(Screen.Splash.route) {

            SplashScreen(
                onNext = {
                    navController.navigate(Screen.Onboarding.route) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // ====================================================
        // ONBOARDING
        // ====================================================

        composable(Screen.Onboarding.route) {

            OnboardingScreen(
                onFinish = {
                    navController.navigate(Screen.Permissions.route)
                }
            )
        }

        // ====================================================
        // PERMISSIONS
        // ====================================================

        composable(Screen.Permissions.route) {

            PermissionsScreen(
                onFinish = {
                    navController.navigate(Screen.Auth.route) {
                        popUpTo(Screen.Onboarding.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // ====================================================
        // HOME / DASHBOARD
        // ====================================================

        composable(Screen.Home.route) {

            DashboardScreen(

                onNavigateToHUD = {
                    navController.navigate(Screen.HUD.route)
                },

                onNavigateToAi = {
                    navController.navigate(Screen.AiAssistant.route)
                },

                onNavigateToDiagnostics = {
                    navController.navigate(Screen.DiagnosticsMain.route)
                },

                onNavigateToTrips = {
                    navController.navigate(Screen.TripHistory.route)
                },

                onNavigateToSettings = {
                    navController.navigate(Screen.Profile.route)
                },

                onNavigateToAchievements = {
                    navController.navigate(Screen.Achievements.route)
                },

                onNavigateToPerformance = {
                    navController.navigate(Screen.Performance.route)
                },

                onNavigateToFuel = {
                    navController.navigate(Screen.FuelHub.route)
                },

                onNavigateToMaintenance = {
                    navController.navigate(Screen.Maintenance.route)
                },

                onNavigateToReports = {
                    navController.navigate(Screen.ReportsHub.route)
                },

                onNavigateToAnalytics = {
                    navController.navigate(Screen.AnalyticsDashboard.route)
                },

                onNavigateToVehicleProfiles = {
                    navController.navigate(Screen.VehicleProfiles.route)
                },

                onNavigateToBackup = {
                    navController.navigate(Screen.BackupRestore.route)
                }
            )
        }

        // ====================================================
        // DASHBOARD EDITOR
        // ====================================================

        composable(Screen.DashboardEditor.route) {

            DashboardEditorScreen(

                onBack = {
                    navController.popBackStack()
                },

                onNavigateToThemePicker = {
                    navController.navigate(Screen.ThemePicker.route)
                }
            )
        }

        // ====================================================
        // THEME PICKER
        // ====================================================

        composable(Screen.ThemePicker.route) {

            ThemePickerScreen(

                darkMode = darkMode,

                onDarkModeChanged = { enabled ->
                    onDarkModeChanged(enabled)
                },

                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // FUEL
        // ====================================================

        composable(Screen.FuelHub.route) {

            FuelManagementScreen()
        }

        // ====================================================
        // DIAGNOSTICS
        // ====================================================

        composable(Screen.DiagnosticsMain.route) {

            DiagnosticsMainScreen(

                onNavigateToDtcList = {
                    navController.navigate(Screen.DtcDetail.route)
                },

                onNavigateToAdvanced = {
                    navController.navigate(Screen.Emissions.route)
                },

                onNavigateToFreezeFrame = {
                    navController.navigate(Screen.FreezeFrame.route)
                },

                onNavigateToVehicleInfo = {
                    navController.navigate(Screen.VehicleInfo.route)
                },

                onNavigateToHistory = {
                    navController.navigate(Screen.DtcHistory.route)
                },

                onClearCodes = {
                    // Clear DTC logic will eventually go here
                }
            )
        }

        // ====================================================
        // DTC LIST
        // ====================================================

        composable(Screen.DtcList.route) {

            DtcListScreen(

                onBack = {
                    navController.popBackStack()
                },

                onNavigateToDetail = {
                    navController.navigate(Screen.DtcDetail.route)
                }
            )
        }

        // ====================================================
        // ALERTS
        // ====================================================

        composable(Screen.Alerts.route) {

            DiagnosticsMainScreen(

                onNavigateToDtcList = {
                    navController.navigate(Screen.DtcList.route)
                },

                onNavigateToAdvanced = {
                    navController.navigate(Screen.Emissions.route)
                },

                onNavigateToFreezeFrame = {
                    navController.navigate(Screen.FreezeFrame.route)
                },

                onNavigateToVehicleInfo = {
                    navController.navigate(Screen.VehicleInfo.route)
                },

                onNavigateToHistory = {
                    navController.navigate(Screen.DtcHistory.route)
                },

                onClearCodes = {}
            )
        }

        // ====================================================
        // PERFORMANCE
        // ====================================================

        composable(Screen.Performance.route) {

            PerformanceMenuScreen(

                onNavigateToMetrics = {
                    navController.navigate(Screen.PerformanceMetrics.route)
                },

                onNavigateToAcceleration = {
                    navController.navigate(Screen.AccelerationTimer.route)
                },

                onNavigateToBraking = {
                    navController.navigate(Screen.BrakingDistance.route)
                },

                onNavigateToCustomPids = {
                    navController.navigate(Screen.CustomPids.route)
                },

                onNavigateToStock = {
                    navController.navigate(Screen.StockComparison.route)
                }
            )
        }

        // ====================================================
        // PERFORMANCE METRICS
        // ====================================================

        composable(Screen.PerformanceMetrics.route) {

            PerformanceMetricsHubScreen(

                onBack = {
                    navController.popBackStack()
                },

                onNavigateToDyno = {
                    navController.navigate(Screen.Dyno.route)
                }
            )
        }

        // ====================================================
        // TRIP HISTORY
        // ====================================================

        composable(Screen.TripHistory.route) {

            TripsScreen(
                onNavigateToDetails = {
                    navController.navigate(Screen.TripDetails.route)
                }
            )
        }

        // ====================================================
        // SETTINGS / PROFILE
        // ====================================================

        composable(Screen.Profile.route) {

            SettingsScreen(

                darkMode = darkMode,

                onDarkModeChanged = { enabled ->
                    onDarkModeChanged(enabled)
                },

                onNavigateToVehicleEditor = {
                    navController.navigate(Screen.VehicleEditor.route)
                },

                onNavigateToBluetooth = {
                    navController.navigate(Screen.BluetoothSettings.route)
                },

                onNavigateToVehicleProfiles = {
                    navController.navigate(Screen.VehicleProfiles.route)
                },

                onNavigateToEditProfile = {
                    navController.navigate(Screen.EditProfile.route)
                },

                onNavigateToPrivacy = {
                    navController.navigate(Screen.PrivacyControls.route)
                },

                onNavigateToBackup = {
                    navController.navigate(Screen.BackupRestore.route)
                }
            )
        }

        // ====================================================
        // EDIT PROFILE
        // ====================================================

        composable(Screen.EditProfile.route) {

            EditProfileScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // AUTH
        // ====================================================

        composable(Screen.Auth.route) {

            LoginScreen(

                onLoginSuccess = {

                    navController.navigate(Screen.Home.route) {

                        popUpTo(Screen.Auth.route) {
                            inclusive = true
                        }
                    }
                },

                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        // ====================================================
        // REGISTER
        // ====================================================

        composable(Screen.Register.route) {

            RegisterScreen(

                onRegisterSuccess = {

                    navController.navigate(Screen.Home.route) {

                        popUpTo(Screen.Register.route) {
                            inclusive = true
                        }
                    }
                },

                onBackToLogin = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // AI ASSISTANT
        // ====================================================

        composable(Screen.AiAssistant.route) {

            AiAssistantScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // DTC DETAIL
        // ====================================================

        composable(Screen.DtcDetail.route) {

            DtcDetailScreen(

                onBack = {
                    navController.popBackStack()
                },

                onNavigateToAiAssistant = {
                    navController.navigate(Screen.AiAssistant.route)
                }
            )
        }

        // ====================================================
        // DTC HISTORY
        // ====================================================

        composable(Screen.DtcHistory.route) {

            DtcHistoryScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // FREEZE FRAME
        // ====================================================

        composable(Screen.FreezeFrame.route) {

            FreezeFrameScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // VEHICLE INFO
        // ====================================================

        composable(Screen.VehicleInfo.route) {

            VehicleInfoScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // TRIP DETAILS
        // ====================================================

        composable(Screen.TripDetails.route) {

            TripDetailsScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // VEHICLE EDITOR
        // ====================================================

        composable(Screen.VehicleEditor.route) {

            VehicleEditorScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // GAMIFICATION / ACHIEVEMENTS
        // ====================================================

        composable(Screen.Achievements.route) {

            AchievementsScreen(

                // --------------------------------------------
                // LEADERBOARD
                // --------------------------------------------

                onNavigateToLeaderboards = {
                    navController.navigate(Screen.Leaderboards.route)
                },

                // --------------------------------------------
                // WEEKLY CHALLENGES
                // --------------------------------------------

                onNavigateToChallenges = {
                    navController.navigate(Screen.WeeklyChallenges.route)
                },

                // --------------------------------------------
                // BADGE / ACHIEVEMENT GALLERY
                // --------------------------------------------

                onNavigateToBadgeGallery = {
                    navController.navigate(BADGE_GALLERY_ROUTE)
                }
            )
        }

        // ====================================================
        // BADGE / ACHIEVEMENT GALLERY
        // ====================================================

        composable(BADGE_GALLERY_ROUTE) {

            BadgeGalleryScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // LEADERBOARDS
        // ====================================================

        composable(Screen.Leaderboards.route) {

            LeaderboardsScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // WEEKLY CHALLENGES
        // ====================================================

        composable(Screen.WeeklyChallenges.route) {

            WeeklyChallengesScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // ADVANCED PERFORMANCE
        // ====================================================

        composable(Screen.Dyno.route) {

            AdvancedPerformanceScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // ACCELERATION TIMER
        // ====================================================

        composable(Screen.AccelerationTimer.route) {

            AccelerationTimerScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // BRAKING DISTANCE
        // ====================================================

        composable(Screen.BrakingDistance.route) {

            BrakingDistanceScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // CUSTOM PIDS
        // ====================================================

        composable(Screen.CustomPids.route) {

            CustomPidsScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // STOCK COMPARISON
        // ====================================================

        composable(Screen.StockComparison.route) {

            StockComparisonScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // EMISSIONS
        // ====================================================

        composable(Screen.Emissions.route) {

            AdvancedDiagnosticsScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // MAINTENANCE
        // ====================================================

        composable(Screen.Maintenance.route) {

            MaintenanceScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // REPORTS
        // ====================================================

        composable(Screen.ReportsHub.route) {

            ReportsHubScreen(

                onNavigateToMechanic = {
                    navController.navigate(Screen.MechanicDashboard.route)
                },

                onNavigateToViewer = {
                    navController.navigate(Screen.ReportViewer.route)
                }
            )
        }

        // ====================================================
        // REPORT VIEWER
        // ====================================================

        composable(Screen.ReportViewer.route) {

            ReportViewerScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // ANALYTICS
        // ====================================================

        composable(Screen.AnalyticsDashboard.route) {

            AnalyticsDashboardScreen()
        }

        // ====================================================
        // FLEET
        // ====================================================

        composable(Screen.FleetDashboard.route) {

            FleetDashboardScreen()
        }

        // ====================================================
        // MECHANIC DASHBOARD
        // ====================================================

        composable(Screen.MechanicDashboard.route) {

            MechanicDashboardScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // BLUETOOTH
        // ====================================================

        composable(Screen.BluetoothSettings.route) {

            BluetoothSettingsScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // VEHICLE PROFILES
        // ====================================================

        composable(Screen.VehicleProfiles.route) {

            VehicleProfilesScreen(

                onBack = {
                    navController.popBackStack()
                },

                onAddVehicle = {
                    navController.navigate(Screen.VehicleEditor.route)
                }
            )
        }

        // ====================================================
        // HUD CUSTOMIZATION
        // ====================================================

        composable(Screen.HudCustomization.route) {

            HudCustomizationScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // HUD
        // ====================================================

        composable(Screen.HUD.route) {

            HUDScreen()
        }

        // ====================================================
        // BACKUP & RESTORE
        // ====================================================

        composable(Screen.BackupRestore.route) {

            BackupRestoreScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ====================================================
        // PRIVACY
        // ====================================================

        composable(Screen.PrivacyControls.route) {

            PrivacyControlsScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
package com.example.autopulse_poe.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector? = null) {
    object Splash : Screen("splash", "Splash")
    object Onboarding : Screen("onboarding", "Onboarding")
    object Home : Screen("home", "Home", Icons.Default.Home)
    object FuelHub : Screen("fuel_hub", "Fuel", Icons.Default.LocalGasStation)
    object Alerts : Screen("alerts", "Alerts", Icons.Default.Notifications)
    object Performance : Screen("performance_menu", "Performance", Icons.Default.Speed)
    object PerformanceMetrics : Screen("performance_metrics", "Metrics Hub")
    object TripHistory : Screen("trip_history", "Trips", Icons.Default.History)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
    
    // Sub-screens
    object DiagnosticsMain : Screen("diagnostics_main", "Diagnostics Hub")
    object DtcList : Screen("dtc_list", "DTC List")
    object Diagnostics : Screen("diagnostics", "Diagnostics", Icons.Default.DirectionsCar)
    object Reports : Screen("reports", "Reports", Icons.Default.Assessment)
    object Settings : Screen("settings", "Settings", Icons.Default.Settings)
    object HUD : Screen("hud", "HUD Mode")
    object AiAssistant : Screen("ai_assistant", "AI Mechanic")
    object DtcDetail : Screen("dtc_detail", "Fault Analysis")
    object DtcHistory : Screen("dtc_history", "History Log")
    object FreezeFrame : Screen("freeze_frame", "Freeze Frame")
    object VehicleInfo : Screen("vehicle_info", "Vehicle Info")
    object TripDetails : Screen("trip_details", "Trip Details")
    object VehicleEditor : Screen("vehicle_editor", "Vehicle Profile")
    object Achievements : Screen("achievements", "Achievements")
    object Dyno : Screen("dyno", "Dyno Mode")
    object AccelerationTimer : Screen("acceleration_timer", "Acceleration Timer")
    object BrakingDistance : Screen("braking_distance", "Braking Distance")
    object CustomPids : Screen("custom_pids", "Custom PIDs")
    object StockComparison : Screen("stock_comparison", "Stock Comparison")
    object Emissions : Screen("emissions", "Emissions")
    object Leaderboards : Screen("leaderboards", "Leaderboards")
    object WeeklyChallenges : Screen("weekly_challenges", "Weekly Challenges")
    object Maintenance : Screen("maintenance", "Maintenance")
    object ReportsHub : Screen("reports_hub", "Health Reports")
    object AnalyticsDashboard : Screen("analytics_dashboard", "Analytics")
    object FleetDashboard : Screen("fleet_dashboard", "Fleet")
    object MechanicDashboard : Screen("mechanic_dashboard", "Mechanic View")
    object BluetoothSettings : Screen("bluetooth_settings", "OBD-II Adapter")
    object VehicleProfiles : Screen("vehicle_profiles", "Vehicle Profiles")
    object DashboardEditor : Screen("dashboard_editor", "Edit Layout")
    object ThemePicker : Screen("theme_picker", "Gauge Theme")
    object HudCustomization : Screen("hud_customization", "HUD Settings")
    object BackupRestore : Screen("backup_restore", "Backup")
    object PrivacyControls : Screen("privacy_controls", "Privacy")
    object Auth : Screen("auth", "Authentication")
    object Register : Screen("register", "Registration")
    object EditProfile : Screen("edit_profile", "Edit Profile")
    object ReportViewer : Screen("report_viewer", "Report Preview")
    object Permissions : Screen("permissions", "App Permissions")

    companion object {
        val bottomNavItems = listOf(Home, Performance, TripHistory, Profile)
    }
}

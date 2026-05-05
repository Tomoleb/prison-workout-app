package com.prisonworkout.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.prisonworkout.data.UserPreferences
import com.prisonworkout.ui.home.HomeScreen
import com.prisonworkout.ui.programs.ProgramsScreen
import com.prisonworkout.ui.statistics.StatisticsScreen
import com.prisonworkout.ui.settings.SettingsScreen
import com.prisonworkout.ui.workout.WorkoutScreen

sealed class BottomNavItem(val route: String, val title: String, val icon: @Composable () -> Unit) {
    object Home : BottomNavItem("home", "Сегодня", { Icon(Icons.Filled.Home, "Главная") })
    object Programs : BottomNavItem("programs", "Программы", { Icon(Icons.Filled.List, "Программы") })
    object Statistics : BottomNavItem("stats", "Статистика", { Icon(Icons.Filled.BarChart, "Статистика") })
    object Settings : BottomNavItem("settings", "Настройки", { Icon(Icons.Filled.Settings, "Настройки") })
}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val prefs = remember { UserPreferences(context) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                val items = listOf(BottomNavItem.Home, BottomNavItem.Programs, BottomNavItem.Statistics, BottomNavItem.Settings)
                val currentRoute = currentRoute(navController)
                items.forEach { item ->
                    NavigationBarItem(
                        icon = item.icon,
                        label = { Text(item.title) },
                        selected = currentRoute == item.route,
                        onClick = {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(prefs) }
            composable("programs") { ProgramsScreen(prefs, navController) }
            composable("stats") { StatisticsScreen(prefs) }
            composable("settings") { SettingsScreen(prefs) }
            composable(
                "program_detail/{programType}",
                arguments = listOf(navArgument("programType") { type = NavType.StringType })
            ) { backStackEntry ->
                val programType = backStackEntry.arguments?.getString("programType") ?: return@composable
                ProgramDetailScreen(programType, prefs, navController)
            }
            composable(
                "workout/{exerciseName}",
                arguments = listOf(navArgument("exerciseName") { type = NavType.StringType })
            ) { backStackEntry ->
                val exerciseName = backStackEntry.arguments?.getString("exerciseName") ?: return@composable
                WorkoutScreen(exerciseName, prefs, navController)
            }
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val currentEntry = navController.currentBackStackEntry
    return currentEntry?.destination?.route
}

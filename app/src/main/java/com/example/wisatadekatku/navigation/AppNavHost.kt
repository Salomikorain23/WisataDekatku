package com.example.wisatadekatku.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.wisatadekatku.data.WisataRepository
import com.example.wisatadekatku.ui.screen.DetailScreen
import com.example.wisatadekatku.ui.screen.MapsScreen
import com.example.wisatadekatku.ui.screens.*

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            LoginScreen(navController)
        }
        composable("register") {
            RegisterScreen(navController)
        }

        composable("main") {
            MainScreen(navController)
        }

        composable("home") {
            HomeScreen(navController)
        }

        composable("search") {
            ListWisataScreen(navController)
        }

        composable("profile") {
            ProfileScreen(navController)
        }

        composable(
            "detail/{wisataId}",
            arguments = listOf(navArgument("wisataId") { type = NavType.StringType })
        ) { backStackEntry ->
            val wisataId = backStackEntry.arguments?.getString("wisataId") ?: ""
            DetailScreen(
                wisataId = wisataId,
                navController = navController
            )
        }

        // ✅ Rute Maps yang menerima 3 argumen
        composable(
            "maps/{latitude}/{longitude}/{wisataId}",
            arguments = listOf(
                navArgument("latitude") { type = NavType.StringType },
                navArgument("longitude") { type = NavType.StringType },
                navArgument("wisataId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val latitude = backStackEntry.arguments?.getString("latitude")?.toDoubleOrNull() ?: 0.0
            val longitude = backStackEntry.arguments?.getString("longitude")?.toDoubleOrNull() ?: 0.0
            val wisataId = backStackEntry.arguments?.getString("wisataId") ?: ""
            MapsScreen(
                wisataLatitude = latitude,
                wisataLongitude = longitude,
                wisataId = wisataId,
                navController = navController
            )
        }
    }
}

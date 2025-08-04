package com.example.wisatadekatku.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.wisatadekatku.ui.screen.DetailScreen
import com.example.wisatadekatku.ui.screen.MapsScreen
import com.example.wisatadekatku.ui.screens.*
import com.example.wisatadekatku.viewmodel.CatatanViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    catatanViewModel: CatatanViewModel
) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController)
        }
        composable("register") {
            RegisterScreen(navController)
        }
        composable("main") {
            MainScreen(
                rootNavController = navController,
                catatanViewModel = catatanViewModel
            )
        }
        composable(
            "detail/{wisataId}",
            arguments = listOf(navArgument("wisataId") { type = NavType.IntType })
        ) { backStackEntry ->
            val wisataId = backStackEntry.arguments?.getInt("wisataId") ?: 0
            DetailScreen(wisataId = wisataId, navController = navController)
        }

        // rute untuk MapsScreen
        composable(
            "maps/{latitude}/{longitude}/{id}",
            arguments = listOf(
                navArgument("latitude") { type = NavType.FloatType },
                navArgument("longitude") { type = NavType.FloatType },
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val latitude = backStackEntry.arguments?.getFloat("latitude")?.toDouble() ?: 0.0
            val longitude = backStackEntry.arguments?.getFloat("longitude")?.toDouble() ?: 0.0
            val id = backStackEntry.arguments?.getInt("id") ?: 0

            MapsScreen(
                wisataLatitude = latitude,
                wisataLongitude = longitude,
                wisataId = id,
                navController = navController
            )
        }
    }
}

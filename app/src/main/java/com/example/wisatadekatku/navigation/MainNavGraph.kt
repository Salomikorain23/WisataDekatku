package com.example.wisatadekatku.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wisatadekatku.ui.screen.DetailScreen
import com.example.wisatadekatku.ui.screens.*
import com.example.wisatadekatku.viewmodel.CatatanViewModel

@Composable
fun MainNavGraph(
    navController: NavHostController,
    rootNavController: NavHostController,
    catatanViewModel: CatatanViewModel
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = rootNavController)
        }
        composable("search") {
            ListWisataScreen(navController = rootNavController)
        }
        composable("catatan") {
            CatatanScreen(navController = navController, viewModel = catatanViewModel)
        }
        composable("profile") {
            ProfileScreen(navController = rootNavController)
        }
        composable("catatan_form") {
            CatatanFormScreen(navController = navController, viewModel = catatanViewModel)
        }
        composable("catatan_form/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            CatatanFormScreen(
                navController = navController,
                viewModel = catatanViewModel,
                catatanId = id
            )
        }
        composable("detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            if (id != null) {
                DetailScreen(wisataId = id, navController = rootNavController)
            }
        }
    }
}

package com.example.wisatadekatku.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wisatadekatku.ui.screens.*

@Composable
fun MainNavGraph(
    navController: NavHostController,
    rootNavController: NavHostController
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = rootNavController)
        }
        composable("search") {
            ListWisataScreen(navController = rootNavController)
        }
        composable("profile") {
            ProfileScreen(navController = rootNavController)
        }
    }
}
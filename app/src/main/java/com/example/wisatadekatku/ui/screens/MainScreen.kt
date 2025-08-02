package com.example.wisatadekatku.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wisatadekatku.navigation.MainNavGraph
import com.example.wisatadekatku.viewmodel.CatatanViewModel

@Composable
fun MainScreen(
    rootNavController: NavHostController,
    catatanViewModel: CatatanViewModel
) {
    val navController = rememberNavController() // khusus untuk bottom nav

    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            MainNavGraph(
                navController = navController,
                rootNavController = rootNavController,
                catatanViewModel = catatanViewModel // Kirim ke graph
            )
        }
    }
}

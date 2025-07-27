package com.example.wisatadekatku.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

object BottomNavItem {
    val Home = Screen("home", Icons.Filled.Home, "Beranda")
    val Search = Screen("search", Icons.Filled.Search, "Cari")
    val Detail = Screen("detail", Icons.Filled.Place, "Detail")
    val Profile = Screen("profile", Icons.Filled.Person, "Profil")
}

data class Screen(
    val route: String,
    val icon: ImageVector,
    val title: String
)

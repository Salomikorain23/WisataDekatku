package com.example.wisatadekatku.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector, val title: String) {
    object Home : Screen("home", Icons.Filled.Home, "Beranda")
    object Search : Screen("search", Icons.Filled.Search, "Cari")
    object Catatan : Screen("catatan", Icons.Filled.Note, "Catatan")
    object Profile : Screen("profile", Icons.Filled.Person, "Profil")
}

object BottomNavItem {
    val items = listOf(
        Screen.Home,
        Screen.Search,
        Screen.Catatan,
        Screen.Profile
    )
}

package com.example.aquaguard.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home: Screen("home", "Inicio", Icons.Default.Home)
    object Profile: Screen("profile", "Perfil", Icons.Default.Person)
}
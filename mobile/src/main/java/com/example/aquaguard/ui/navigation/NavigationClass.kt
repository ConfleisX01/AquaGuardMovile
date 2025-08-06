package com.example.aquaguard.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector? = null
) {
    object Welcome : Screen("welcome", "Bienvenida")
    object Login : Screen("login", "Iniciar sesión")
    object Register : Screen("register", "Registro")

    object Main : Screen("main", "Principal") // no visible
    object Home : Screen("home", "Inicio", Icons.Default.Home)
    object ProfileEdit : Screen("profileEdit", "Editar Perfil", Icons.Default.Person)
    object ProfileInformation : Screen("profileInformation", "Ver Perfil", Icons.Default.Person)
    object ControlDevice : Screen("controlDevice", "Mi Casa", Icons.Default.Settings)
    object Forum : Screen("forum", "Foro", Icons.Default.Info)
}
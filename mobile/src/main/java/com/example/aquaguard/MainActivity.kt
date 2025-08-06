package com.example.aquaguard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aquaguard.data.config.SessionManager
import com.example.aquaguard.ui.login.LoginScreen
import com.example.aquaguard.ui.main.MainScreen
import com.example.aquaguard.ui.navigation.Screen
import com.example.aquaguard.ui.register.RegisterScreen
import com.example.aquaguard.ui.theme.AquaGuardTheme
import com.example.aquaguard.ui.welcome.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AquaGuardTheme {                         // Aplicamos en tema global
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation() // Navegacion de la aplicacion
                }
            }
        }
    }
}

/*
* AppNavigation - Esta funcion sirve para el enrutamiento de todas las pantallas disponibles dentro de la aplicacion
*/

@Composable
fun AppNavigation() {
    val sessionManager = SessionManager(LocalContext.current)
    val navController = rememberNavController()
    val userId: Int = sessionManager.obtenerUsuario()

    if (userId != -1) {
        // Usuario autenticado, muestra navegación con barra
        MainScreen(navController, sessionManager)
    } else {
        // Usuario no autenticado, solo navegación sin barra
        NavHost(
            navController = navController,
            startDestination = Screen.Welcome.route
        ) {
            composable(Screen.Welcome.route) { WelcomeScreen(navController) }
            composable(Screen.Register.route) { RegisterScreen(navController) }
            composable(Screen.Login.route) { LoginScreen(navController, sessionManager) }
        }
    }
}
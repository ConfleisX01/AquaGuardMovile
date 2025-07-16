package com.example.aquaguard

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.aquaguard.data.ViewModel.UsuarioViewModel
import com.example.aquaguard.data.config.SessionManager
import com.example.aquaguard.ui.home.HomeScreen
import com.example.aquaguard.ui.home.HomeViewModel
import com.example.aquaguard.ui.login.LoginScreen
import com.example.aquaguard.ui.navigation.BottomNavigationBar
import com.example.aquaguard.ui.navigation.Screen
import com.example.aquaguard.ui.profile.ProfileScreen
import com.example.aquaguard.ui.profile.ProfileViewModel
import com.example.aquaguard.ui.register.RegisterScreen
import com.example.aquaguard.ui.welcome.WelcomeScreen
import com.example.aquaguard.ui.welcome.WelcomeViewModel
import com.example.compose.AquaCycleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AquaCycleTheme {                         // Aplica tu tema global
                Surface(
                    modifier = Modifier.fillMaxSize(), // Cubre toda la pantalla
                    color = MaterialTheme.colorScheme.background // Usa el color del fondo del tema
                ) {
                    val context = LocalContext.current
                    AppNavigation(context)
                }
            }
        }
    }
}


@Composable
fun AppNavigation(context: Context) {
    val sessionManager = remember { SessionManager(context) }
    val userSession = remember { mutableStateOf(sessionManager.getUser()) }
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = if (userSession.value != null) Screen.Main.route else "auth"
    ) {
        // Subgrafo de autenticación
        navigation (startDestination = Screen.Welcome.route, route = "auth") {
            composable(Screen.Welcome.route) {
                val viewModel: WelcomeViewModel = viewModel()
                WelcomeScreen(rootNavController, viewModel)
            }
            composable(Screen.Login.route) {
                LoginScreen(navController = rootNavController, onLoginSuccess = {
                    userSession.value = sessionManager.getUser()
                    rootNavController.navigate(Screen.Main.route) {
                        popUpTo("auth") { inclusive = true }
                    }
                })
            }
            composable(Screen.Register.route) {
                val viewModel: UsuarioViewModel = viewModel()
                RegisterScreen(usuarioViewModel = viewModel)
            }
        }

        // Subgrafo principal con navegación inferior
        composable(Screen.Main.route) {
            MainLayoutWithBottomNav(
                context = context,
                onLogout = {
                    sessionManager.clear()
                    userSession.value = null
                    rootNavController.navigate("auth") {
                        popUpTo(Screen.Main.route) { inclusive = true }
                    }
                }
            )
        }
    }
}


@Composable
fun MainLayoutWithBottomNav(context: Context, onLogout: () -> Unit) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                val viewModel: HomeViewModel = viewModel()
                HomeScreen(viewModel = viewModel, onLogout = onLogout, context)
            }
            composable(Screen.Profile.route) {
                val viewModel: ProfileViewModel = viewModel()
                ProfileScreen(viewModel = viewModel, context)
            }
        }
    }
}
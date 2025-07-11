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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aquaguard.data.ViewModel.UsuarioViewModel
import com.example.aquaguard.data.config.SessionManager
import com.example.aquaguard.ui.home.HomeScreen
import com.example.aquaguard.ui.login.LoginScreen
import com.example.aquaguard.ui.navigation.BottomNavigationBar
import com.example.aquaguard.ui.navigation.Screen
import com.example.aquaguard.ui.profile.ProfileScreen
import com.example.aquaguard.ui.profile.ProfileViewModel
import com.example.aquaguard.ui.register.RegisterScreen
import com.yourpackagename.ui.theme.AquaCycleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AquaCycleTheme {
                Surface (
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    AppNavigation(context)
                }
            }
        }
    }
}


@Composable
fun AppNavigation(context : Context) {
    val sessionManager = remember { SessionManager(context) }
    val userSession = remember { mutableStateOf(sessionManager.getUser()) }

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (userSession.value != null) "main" else Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController, onLoginSuccess = {
                userSession.value = sessionManager.getUser()
                navController.navigate(Screen.Home.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            })
        }


        composable(Screen.Register.route) {
            val viewModel: UsuarioViewModel = viewModel()
            RegisterScreen(usuarioViewModel = viewModel)
        }

        // 
        composable("main") {
            MainLayoutWithBottomNav(
                onLogout = {
                    sessionManager.clear()
                    userSession.value = null
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0)
                    }
                },
                context = context,
                correoUsuario = userSession.value?.correo ?: ""
            )
        }
    }
}

@Composable
fun MainLayoutWithBottomNav(
    context : Context,
    onLogout: () -> Unit,
    correoUsuario: String
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                val viewModel: UsuarioViewModel = viewModel()
                HomeScreen(viewModel = viewModel, correoUsuario = correoUsuario, onLogout = onLogout)
            }
            composable(Screen.Profile.route) {
                val viewModel: ProfileViewModel = viewModel()
                ProfileScreen(viewModel, context)
            }
        }
    }
}


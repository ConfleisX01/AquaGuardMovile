package com.example.aquaguard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aquaguard.data.ViewModel.UsuarioViewModel
import com.example.aquaguard.ui.home.HomeScreen
import com.example.aquaguard.ui.navigation.BottomNavigationBar
import com.example.aquaguard.ui.navigation.Screen
import com.example.aquaguard.ui.profile.ProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Home.route) { backStackEntry ->
                val viewModel: UsuarioViewModel = viewModel(backStackEntry)
                HomeScreen(viewModel)
            }
            composable(Screen.Profile.route) { backStackEntry ->
                val viewModel: UsuarioViewModel = viewModel(backStackEntry)
                ProfileScreen(viewModel)
            }
        }
    }
}
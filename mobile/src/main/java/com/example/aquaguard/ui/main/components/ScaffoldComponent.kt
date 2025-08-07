package com.example.aquaguard.ui.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aquaguard.ui.Forum.ForumScreen
import com.example.aquaguard.ui.controlDevice.ControlDeviceScreen
import com.example.aquaguard.ui.home.HomeScreen
import com.example.aquaguard.ui.navigation.BottomNavigationBar
import com.example.aquaguard.ui.navigation.Screen
import com.example.aquaguard.ui.profile.edit.ProfileEditScreen
import com.example.aquaguard.ui.profile.view.ProfileInformationScreen

@Composable
fun ScaffoldNavigationBar(isProductOwner: Boolean, onLogout: () -> Unit) {
    val internalNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(internalNavController, isProductOwner)
        }
    ) { contentPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)) {
            NavHost(
                navController = internalNavController,
                startDestination = if (isProductOwner) Screen.Home.route else Screen.Forum.route
            ) {
                // Pantallas de control de dispositivo, foro, perfil
                composable(Screen.Home.route) { HomeScreen() }
                composable(Screen.ControlDevice.route) { ControlDeviceScreen() }
                composable(Screen.ProfileEdit.route) { ProfileEditScreen() }
                composable(Screen.ProfileInformation.route) { ProfileInformationScreen(onLogout, internalNavController) }
                composable(Screen.Forum.route) { ForumScreen() }
            }
        }
    }
}

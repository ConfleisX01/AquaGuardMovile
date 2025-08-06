package com.example.aquaguard.ui.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aquaguard.ui.Forum.ForumScreen
import com.example.aquaguard.ui.controlDevice.ControlDeviceScreen
import com.example.aquaguard.ui.home.HomeScreen
import com.example.aquaguard.ui.navigation.BottomNavigationBar
import com.example.aquaguard.ui.navigation.Screen
import com.example.aquaguard.ui.profile.edit.ProfileEditScreen
import com.example.aquaguard.ui.profile.view.ProfileInformationScreen

@Composable
fun ScaffoldNavigationBar(navController: NavController, isProductOwner: Boolean) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController, isProductOwner)
        }
    ) { contentPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)) {
            NavHost(
                navController = navController as NavHostController,
                startDestination = if (isProductOwner) Screen.Home.route else Screen.Forum.route
            ) {
                composable(Screen.Home.route) { HomeScreen() }
                composable(Screen.ControlDevice.route) { ControlDeviceScreen() }
                composable(Screen.ProfileEdit.route) { ProfileEditScreen() }
                composable(Screen.ProfileInformation.route) { ProfileInformationScreen(navController) }
                composable(Screen.Forum.route) { ForumScreen() }
            }
        }
    }
}

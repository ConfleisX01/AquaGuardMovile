package com.example.aquaguard.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.aquaguard.data.config.SessionManager
import com.example.aquaguard.ui.main.components.ScaffoldNavigationBar
import androidx.compose.runtime.getValue

@Composable
fun MainScreen(navController: NavController, sessionManager: SessionManager) {
    val mainScreenViewModel: MainViewModel = viewModel()
    val userId: Int = sessionManager.obtenerUsuario()

    val isProductOwner by mainScreenViewModel.isProductOwner.collectAsState()

    LaunchedEffect(Unit) {
        mainScreenViewModel.verifyProductUser(userId)
    }

    ScaffoldNavigationBar(navController, isProductOwner)
}
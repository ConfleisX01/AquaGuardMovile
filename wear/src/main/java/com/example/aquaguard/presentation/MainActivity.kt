package com.example.aquaguard.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.SwipeToDismissBox
import com.example.aquaguard.presentation.theme.AquaGuardWearTheme
import com.example.aquaguard.presentation.ui.main.DetailsScreenSecondTank
import com.example.aquaguard.presentation.ui.main.DetailsScreenFirstTank
import com.example.aquaguard.presentation.ui.main.viewmodels.DeviceInformationViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AquaGuardWearTheme {
                WearApp() // Navegacion
            }
        }
    }
}

@Composable
fun WearApp(navController: NavHostController = rememberNavController()) {
    val viewModel: DeviceInformationViewModel = viewModel() // Instancia del viewmodel para evitar sobrecargos al wear
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            SwipeToDismissBox(onDismissed = {
                navController.navigate("details")
            }) {
                DetailsScreenFirstTank(viewModel)
            }
        }

        composable("details") {
            SwipeToDismissBox(
                onDismissed = {
                    navController.popBackStack()
                }
            ) {
                DetailsScreenSecondTank(viewModel)
            }
        }
    }
}

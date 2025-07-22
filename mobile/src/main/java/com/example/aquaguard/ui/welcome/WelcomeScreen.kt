package com.example.aquaguard.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aquaguard.R
import com.example.aquaguard.ui.navigation.Screen
import com.example.aquaguard.ui.theme.AquaGuardTheme

@Composable
fun WelcomeScreen(navController: NavController, viewModel: WelcomeViewModel) {
    AquaGuardTheme {
        Image(
            painter = painterResource(id = R.drawable.background_3),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
        )

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column (
                modifier = Modifier
                    .fillMaxHeight(.5f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "¡Bienvenido a AquaGuard!",
                    style = MaterialTheme.typography.displayLarge,
                    color = Color.White
                )
                Spacer(modifier = Modifier.padding(0.dp, 12.dp))
                Text(
                    "Cuidar el agua nunca fue tan fácil. Controla y monitorea tu sistema de recolección desde cualquier lugar.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
            Column (
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button (
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { navController.navigate(Screen.Login.route) }
                ) {
                    Text("Iniciar sesión")
                }
                FilledTonalButton (
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { navController.navigate(Screen.Register.route) }
                ) {
                    Text("Crear una cuenta")
                }
            }
        }
    }
}
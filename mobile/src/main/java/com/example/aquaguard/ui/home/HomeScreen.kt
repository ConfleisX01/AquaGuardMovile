package com.example.aquaguard.ui.home

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.aquaguard.ui.home.components.DeviceCardInfo
import com.example.aquaguard.ui.home.components.WeatherInfoSection
import com.example.aquaguard.ui.theme.AquaGuardTheme

@Composable
fun HomeScreen (
    viewModel: HomeViewModel,
    onLogout: () -> Unit,
    context: Context,
) {
    val datosConAQI by viewModel.dataListState.collectAsState()

    AquaGuardTheme {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            WeatherInfoSection() // Parte superior con datos del clima
            DeviceCardInfo() // Parte inferior con datos de los contenedores
        }
    }
}
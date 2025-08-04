package com.example.aquaguard.presentation.ui.main.components

import androidx.compose.runtime.Composable
import com.example.aquaguard.R

@Composable
fun TemperatureCard(temp: Float) {
    InfoCard(title = "Temperatura", value = "$temp °C", icon = R.drawable.icon_temperature)
}
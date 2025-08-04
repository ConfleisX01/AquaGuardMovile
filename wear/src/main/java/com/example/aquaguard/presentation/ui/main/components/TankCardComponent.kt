package com.example.aquaguard.presentation.ui.main.components

import androidx.compose.runtime.Composable
import com.example.aquaguard.R

@Composable
fun TankCard(tank: String) {
    InfoCard(
        title = "Tanque",
        value = if (tank.equals("SINFILTRAR")) "Sin Filtrar" else "Filtrado",
        icon = R.drawable.icon_tank
    )
}
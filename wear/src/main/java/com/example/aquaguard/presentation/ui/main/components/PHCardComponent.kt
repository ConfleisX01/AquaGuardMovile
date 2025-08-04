package com.example.aquaguard.presentation.ui.main.components

import androidx.compose.runtime.Composable
import com.example.aquaguard.R

@Composable
fun PHCard(ph: Float) {
    InfoCard(title = "pH", value = "$ph", icon = R.drawable.icon_ph)
}
package com.example.aquaguard.presentation.ui.main.components

import androidx.compose.runtime.Composable
import com.example.aquaguard.R

@Composable
fun TDSCard(tds: Int) {
    InfoCard(title = "TDS", value = "$tds ppm", icon = R.drawable.icon_waterqa)
}
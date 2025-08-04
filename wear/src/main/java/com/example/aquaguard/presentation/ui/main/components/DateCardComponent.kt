package com.example.aquaguard.presentation.ui.main.components

import androidx.compose.runtime.Composable
import com.example.aquaguard.R

@Composable
fun DateCard (date: String){
    InfoCard(title = "Ultima actualización", value = date, icon = R.drawable.icon_date)
}
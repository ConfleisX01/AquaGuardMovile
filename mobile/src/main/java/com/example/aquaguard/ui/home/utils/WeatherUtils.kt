package com.example.aquaguard.ui.home.utils

import com.example.aquaguard.R

fun getWeatherIcon(weatherCode: Int): Int {
    return when (weatherCode) {
        0 -> R.drawable.sun                      // Despejado
        1, 2, 3 -> R.drawable.cloudy      // Parcialmente nublado
        in 45..48 -> R.drawable.fog              // Neblina
        in 51..67 -> R.drawable.light_rain          // Llovizna
        in 80..82 -> R.drawable.rain             // Lluvia
        in 95..99 -> R.drawable.storm            // Tormenta
        else -> R.drawable.logo       // Icono por defecto
    }
}

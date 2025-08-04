package com.example.aquaguard.ui.home.utils

fun classifyAqi(aqi: Int): String {
    return when (aqi) {
        in 0..50 -> "Buena"
        in 51..100 -> "Moderada"
        in 101..150 -> "Insalubre para grupos sensibles"
        in 151..200 -> "Insalubre"
        in 201..300 -> "Muy insalubre"
        else -> "Peligroso"
    }
}

fun estimarAQI(valor: Int): Int {
    val minimo = 0
    val maximo = 4095
    val valClamp = valor.coerceIn(minimo, maximo)
    return ((valClamp - minimo).toFloat() / (maximo - minimo) * 500).toInt()
}
package com.example.aquaguard.ui.home.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DecisionViewModel : ViewModel() {
    private val _dataWeatherDesition = MutableStateFlow("")
    val dataWeatherDesition: StateFlow<String> = _dataWeatherDesition

    private val _dataWeatherDesitionTitle = MutableStateFlow("")
    val dataWeatherDesitionTitle: StateFlow<String> = _dataWeatherDesitionTitle

    fun verifyWeather(aqi: Int = 0, isRaining: Boolean = false, isLowWater: Boolean = false) {
        viewModelScope.launch {
            when {
                aqi >= 50 -> {
                    _dataWeatherDesition.value = "Lluvia muy contaminada, espera por favor"
                    _dataWeatherDesitionTitle.value = "Lluvia contaminada"
                }
                !isRaining -> {
                    _dataWeatherDesition.value = "No está lloviendo en este momento"
                    _dataWeatherDesitionTitle.value = "Sin lluvia"
                }
                !isLowWater -> {
                    _dataWeatherDesition.value = "Contenedor demasiado lleno para recolectar agua"
                    _dataWeatherDesitionTitle.value = "Contenedor lleno"
                }
                isRaining && isLowWater -> {
                    _dataWeatherDesition.value = "Buen momento para recolectar agua"
                    _dataWeatherDesitionTitle.value = "A recolectar"
                }
                else -> {
                    _dataWeatherDesition.value = "Condiciones no ideales para recolectar agua"
                    _dataWeatherDesitionTitle.value = "Indefinido"
                }
            }
        }
    }
}
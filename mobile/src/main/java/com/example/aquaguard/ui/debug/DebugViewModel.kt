package com.example.aquaguard.ui.debug

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquaguard.data.models.WeatherInfo
import com.example.aquaguard.ui.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DebugViewModel : ViewModel() {
    data class MutableWeatherInfo (
        var temperature: Double = 25.0,
        var windspeed: Double = 10.0,
        var humidity: Double = 50.0,
        var precipitation: Double = 20.0,
        var cloudcover: Double = 30.0,
        var description: String = "Nublado",
        var iconCode: Int = 1
    ) {
        fun toWeatherInfo(): WeatherInfo {
            return WeatherInfo(
                temperature = temperature,
                windspeed = windspeed,
                humidity = humidity,
                precipitation = precipitation,
                cloudcover = cloudcover,
                description = description,
                iconCode = iconCode
            )
        }
    }

    private val _weatherTestData = MutableStateFlow<WeatherInfo?>(null)
    val weatherTestData: StateFlow<WeatherInfo?> = _weatherTestData

    fun loadWeatherTestData() {
        if (_weatherTestData.value == null) {
            viewModelScope.launch {
                val testData = MutableWeatherInfo().toWeatherInfo()
                _weatherTestData.value = testData
            }
        }
    }

    fun updateWeatherTestData(info: WeatherInfo) {
        _weatherTestData.value = info
        Log.i("DEBUGSCREEN", "Informacion del clima actualizada")
    }
}
package com.example.aquaguard.ui.home.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquaguard.data.config.RetrofitClientWeahter
import com.example.aquaguard.data.models.WeatherInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class WeatherViewModel : ViewModel() {
    private val _weather = MutableStateFlow<WeatherInfo?>(null)
    val weather: StateFlow<WeatherInfo?> = _weather

    fun fetchWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            while (true) {
                try {
                    val response = RetrofitClientWeahter.api.getWeatherData(lat, lon)
                    val current = response.current_weather
                    val hourly = response.hourly

                    val now = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS)
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:00")
                    val nowFormatted = now.format(formatter)

                    val index = hourly.time.indexOf(nowFormatted)
                    if (index == -1) return@launch

                    val humidity = hourly.relativehumidity_2m[index]
                    val precipitation = hourly.precipitation_probability[index]
                    val cloudcover = hourly.cloudcover[index]

                    val description = mapWeatherCode(current.weathercode)

                    _weather.value = WeatherInfo(
                        temperature = current.temperature,
                        windspeed = current.windspeed,
                        humidity = humidity,
                        precipitation = precipitation,
                        cloudcover = cloudcover,
                        description = description,
                        iconCode = current.weathercode
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                delay(60_000L)
            }
        }
    }

    private fun mapWeatherCode(code: Int): String = when (code) {
        0 -> "Despejado"
        1, 2, 3 -> "Nublado"
        45, 48 -> "Niebla"
        in 51..67 -> "Llovizna"
        in 71..77 -> "Nieve"
        in 80..82 -> "Lluvia"
        in 95..99 -> "Tormenta"
        else -> "Desconocido"
    }
}
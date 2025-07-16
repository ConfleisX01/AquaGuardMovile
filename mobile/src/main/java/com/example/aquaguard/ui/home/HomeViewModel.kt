package com.example.aquaguard.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquaguard.data.config.RetrofitClient
import com.example.aquaguard.data.config.RetrofitClientWeahter
import com.example.aquaguard.data.models.CurrentWeather
import com.example.aquaguard.data.models.Usuario
import com.example.aquaguard.data.models.WeatherInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class HomeViewModel : ViewModel() {
    private val _weather = MutableStateFlow<WeatherInfo?>(null)
    val weather: StateFlow<WeatherInfo?> = _weather

    private val _formState = MutableStateFlow(Usuario())
    val formState: StateFlow<Usuario> = _formState

    fun cargarUsuario(id: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiServiceUsuario.obtenerUsuariosId(id)
                if (response.isSuccessful) {
                    response.body()?.let { usuario ->
                        _formState.value = usuario
                        Log.i("ProfileM", "Usuario cargado correctamente")
                    }
                } else {
                    Log.e("API", "Error al cargar los usuarios ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Error de servidor, sabe porque")
            }
        }
    }

    fun fetchWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val response = RetrofitClientWeahter.api.getWeatherData(lat, lon)
                val current = response.current_weather
                val hourly = response.hourly

                // Buscar índice actual (ejemplo: "2025-07-12T23:00")
                val now = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS)
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:00")
                val nowFormatted = now.format(formatter)

                val index = hourly.time.indexOf(nowFormatted)
                if (index == -1) {
                    println("Hora actual no encontrada en los datos horarios.")
                    return@launch
                }

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
        }
    }

    private fun mapWeatherCode(code: Int): String {
        return when (code) {
            0 -> "Despejado"
            1, 2, 3 -> "Parcialmente nublado"
            45, 48 -> "Niebla"
            in 51..67 -> "Llovizna"
            in 71..77 -> "Nieve"
            in 80..82 -> "Lluvia"
            in 95..99 -> "Tormenta"
            else -> "Desconocido"
        }
    }
}
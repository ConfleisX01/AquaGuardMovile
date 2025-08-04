package com.example.aquaguard.ui.home.viewmodels

import WaterData
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquaguard.data.config.RetrofitClientWeahter
import com.example.aquaguard.data.models.AirInformationWithAQI
import com.example.aquaguard.data.models.WaterQualityClass
import com.example.aquaguard.data.models.WeatherInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.Boolean

class WeatherViewModel() : ViewModel() {
    private val _weather = MutableStateFlow<WeatherInfo?>(null)
    val weather: StateFlow<WeatherInfo?> = _weather

    private val _dataWeatherDesition = MutableStateFlow(String())
    val dataWeatherDesition: StateFlow<String> = _dataWeatherDesition

    private val _dataWeatherDesitionTitle = MutableStateFlow(String())
    val dataWeatherDesitionTitle: StateFlow<String> = _dataWeatherDesitionTitle

    private val _isWorthCollect = MutableStateFlow(false)
    val isWorthCollect: StateFlow<Boolean> = _isWorthCollect


    fun verifyWeather(
        aqiData: AirInformationWithAQI?,
        weatherData: WeatherInfo?,
        waterData: WaterData?,
        waterStoredData: WaterQualityClass?
    ) {
        viewModelScope.launch {
            try {
                val aqi = aqiData?.aqiEstimado
                val precipitation = weatherData?.precipitation
                val waterLevel = if (waterData?.waterLevel != null) {
                    waterData.waterLevel
                } else {
                    waterStoredData?.cantidad
                }

                Log.i("WEATHERDEB", "aqi: $aqi, precipitacion: $precipitation, cantidad: $waterLevel")

                // Si uno es nulo saltara un mensaje de error ;)
                if (aqi == null || precipitation == null || waterLevel == null) {
                    _dataWeatherDesition.value = "Faltan datos para una recolección de calidad"
                    _dataWeatherDesitionTitle.value = "Datos incompletos"
                    return@launch
                    _isWorthCollect.value = false
                }

                when {
                    aqi >= 50 && precipitation > 50.0 -> {
                        _dataWeatherDesition.value = "Lluvia muy contaminada, espera por favor"
                        _dataWeatherDesitionTitle.value = "Lluvia contaminada"
                        _isWorthCollect.value = false
                    }

                    aqi >= 50 -> {
                        _dataWeatherDesition.value = "La lluvia estará muy contaminada, espera por favor"
                        _dataWeatherDesitionTitle.value = "Ambiente contaminado"
                        _isWorthCollect.value = false
                    }

                    precipitation <= 50.0 -> {
                        _dataWeatherDesition.value = "No está lloviendo en este momento"
                        _dataWeatherDesitionTitle.value = "Sin lluvia"
                        _isWorthCollect.value = false
                    }

                    waterLevel >= 90.0f -> {
                        _dataWeatherDesition.value = "Contenedor demasiado lleno para recolectar agua"
                        _dataWeatherDesitionTitle.value = "Contenedor lleno"
                        _isWorthCollect.value = false
                    }

                    precipitation >= 50.0 && waterLevel <= 80.0 && aqi <= 30 -> {
                        _dataWeatherDesition.value = "Buen momento para recolectar agua"
                        _dataWeatherDesitionTitle.value = "A recolectar"
                        _isWorthCollect.value = true
                    }

                    else -> {
                        _dataWeatherDesition.value = "Condiciones no ideales para recolectar agua"
                        _dataWeatherDesitionTitle.value = "Indefinido"
                        _isWorthCollect.value = false
                    }
                }
            } catch (e: Exception) {
                Log.e("ERROR", "Error al consultar los datos: ${e.message}")
                _dataWeatherDesition.value = "Ocurrió un error al evaluar condiciones"
                _dataWeatherDesitionTitle.value = "Error"
            }
        }
    }

    fun reloadFetchWeather (lat: Double, lon: Double) {
        viewModelScope.launch {
            while (true) {
                try {
                    fetchWeather(lat, lon)
                } catch (e : Exception) {
                    Log.e("Weather", "Error al obtener el clima actual")
                }
                delay(60_000L)
            }
        }
    }

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
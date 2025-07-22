package com.example.aquaguard.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquaguard.data.config.RetrofitClientData
import com.example.aquaguard.data.config.RetrofitClientWeahter
import com.example.aquaguard.data.models.DataInformationWithAQI
import com.example.aquaguard.data.models.Usuario
import com.example.aquaguard.data.models.WeatherInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.Int

class HomeViewModel : ViewModel() {
    private val _weather = MutableStateFlow<WeatherInfo?>(null)
    val weather: StateFlow<WeatherInfo?> = _weather

    private val _formState = MutableStateFlow(Usuario())
    val formState: StateFlow<Usuario> = _formState

    private val _dataListState = MutableStateFlow<List<DataInformationWithAQI>>(emptyList())
    val dataListState: StateFlow<List<DataInformationWithAQI>> = _dataListState

    private val _dataWeatherDesition = MutableStateFlow(String())
    val dataWeatherDesition: StateFlow<String> = _dataWeatherDesition

    private val _dataWeatherDesitionTitle = MutableStateFlow(String())
    val dataWeatherDesitionTitle: StateFlow<String> = _dataWeatherDesitionTitle


    fun verifyWeather(aqi: Int = 0, isRaining: Boolean = false, isLowWater: Boolean = false) {
        viewModelScope.launch {
            try {
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
            } catch (e: Exception) {
                Log.e("ERROR", "Error al consultar los datos: ${e.message}")
            }
        }
    }

    fun cargarDatosPeriodicos () {
        viewModelScope.launch {
            while (true) {
                cargarDatosAQI()
                delay(60_000L)
            }
        }
    }

    fun cargarDatosAQI() {
        viewModelScope.launch {
                try {
                    val response = RetrofitClientData.retrofit.obtenerDatos()
                    if (response.isSuccessful) {
                        response.body()?.let { datosCrudos ->
                            val listaProcesada = datosCrudos.map { dato ->
                                val aqiInt = dato.aqi.toIntOrNull() ?: 0
                                val aqiEstimado = estimarAQI(aqiInt)
                                DataInformationWithAQI(
                                    id = dato.id,
                                    aqiRaw = aqiInt,
                                    aqiEstimado = aqiEstimado,
                                    tds = dato.tds,
                                    temperature = dato.temperature,
                                    ph = dato.ph
                                )
                            }
                            Log.d("ViewModel", "Datos cargados: ${listaProcesada.size}")
                            _dataListState.value = listaProcesada
                        }
                    } else {
                        Log.e("ViewModel", "Error HTTP: ${response.code()}")
                    }
                } catch (e: Exception) {
                    Log.e("ViewModel", "Excepción: ${e.message}")
                }
        }
    }

    fun estimarAQI(valor: Int): Int {
        val minimo = 0
        val maximo = 4095
        val valClamp = valor.coerceIn(minimo, maximo)
        return ((valClamp - minimo).toFloat() / (maximo - minimo) * 500).toInt()
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
                delay(60_000L)
            }
        }
    }

    private fun mapWeatherCode(code: Int): String {
        return when (code) {
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
}
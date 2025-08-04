package com.example.aquaguard.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquaguard.data.config.RetrofitClientAqi
import com.example.aquaguard.data.config.RetrofitClientWeahter
import com.example.aquaguard.data.models.AirInformationWithAQI
import com.example.aquaguard.data.models.Usuario
import com.example.aquaguard.data.models.WeatherInfo
import com.example.aquaguard.ui.home.utils.estimarAQI
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.Int

class HomeViewModel : ViewModel() {
    private val _dataListState = MutableStateFlow<List<AirInformationWithAQI>>(emptyList())
    val dataListState: StateFlow<List<AirInformationWithAQI>> = _dataListState

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
                    val response = RetrofitClientAqi.retrofit.obtenerDatos()
                    if (response.isSuccessful) {
                        response.body()?.let { datosCrudos ->
                            val listaProcesada = datosCrudos.map { dato ->
                                val aqiInt = dato.aqi.toIntOrNull() ?: 0
                                val aqiEstimado = estimarAQI(aqiInt)
                                AirInformationWithAQI(
                                    id = dato.id,
                                    aqiRaw = aqiInt,
                                    aqiEstimado = aqiEstimado,
                                )
                            }
                            Log.d("ViewModel", "Datos cargados: ${listaProcesada.size}")
                            _dataListState.value = listaProcesada
                        }
                    } else {
                        Log.e("ViewModel", "Error HTTP: ${response.errorBody().toString()}")
                    }
                } catch (e: Exception) {
                    Log.e("ViewModel", "Excepción: ${e.message}")
                }
        }
    }
}
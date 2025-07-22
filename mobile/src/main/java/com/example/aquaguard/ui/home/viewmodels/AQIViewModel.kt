package com.example.aquaguard.ui.home.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquaguard.data.config.RetrofitClientData
import com.example.aquaguard.data.models.DataInformationWithAQI
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AQIViewModel : ViewModel() {
    private val _dataListState = MutableStateFlow<List<DataInformationWithAQI>>(emptyList())
    val dataListState: StateFlow<List<DataInformationWithAQI>> = _dataListState

    fun cargarDatosPeriodicos() {
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
                        _dataListState.value = listaProcesada
                    }
                }
            } catch (e: Exception) {
                Log.e("AQIViewModel", "Excepción: ${e.message}")
            }
        }
    }

    private fun estimarAQI(valor: Int): Int {
        val valClamp = valor.coerceIn(0, 4095)
        return ((valClamp.toFloat() / 4095) * 500).toInt()
    }
}
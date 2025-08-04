package com.example.aquaguard.presentation.ui.main.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquaguard.presentation.models.TanqueEnum
import com.example.aquaguard.presentation.models.WaterQualityClass
import com.example.aquaguard.presentation.service.retrofit.RetrofitClientContainers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DeviceInformationViewModel : ViewModel() {
    private val _waterData = MutableStateFlow<List<WaterQualityClass?>>(emptyList())
    val waterData: StateFlow<List<WaterQualityClass?>> = _waterData

    private val _firstTankInformation = MutableStateFlow<List<WaterQualityClass?>>(emptyList())
    val firstTankInformation : StateFlow<List<WaterQualityClass?>> = _firstTankInformation

    private val _secondTankInformation = MutableStateFlow<List<WaterQualityClass?>>(emptyList())
    val secondTankInformation : StateFlow<List<WaterQualityClass?>> = _secondTankInformation

    fun loadWaterData() {
        viewModelScope.launch {
            try {
                val response = RetrofitClientContainers.retrofit.obtenerDatos()
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        _waterData.value = data // Datos sin filtrar

                        _firstTankInformation.value = data // Tanque sin filtrar
                            .asReversed()
                            .filter { it.tipoTanque == TanqueEnum.SINFILTRAR }
                            .take(1)

                        _secondTankInformation.value = data // Tanque filtrado
                            .asReversed()
                            .filter { it.tipoTanque == TanqueEnum.FILTRADA }
                            .take(1)
                    }
                } else {
                    Log.e("WEARAPI", "Error al obtener los datos")
                }
            } catch (e: Exception) {
                Log.e("WEARAPI", "Error al consultar los datos del servidor ${e.printStackTrace()}")
            }
        }
    }
}
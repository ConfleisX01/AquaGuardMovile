package com.example.aquaguard.ui.home.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquaguard.data.config.RetrofitClientContainers
import com.example.aquaguard.data.models.WaterQualityClass
import com.google.android.gms.wearable.Wearable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WaterViewModel : ViewModel() {
    private val _waterInfo = MutableStateFlow<List<WaterQualityClass?>>(emptyList())
    val waterInfo : StateFlow<List<WaterQualityClass?>> = _waterInfo

    private val _waterNoFilter = MutableStateFlow<List<WaterQualityClass?>>(emptyList())
    val waterNoFilter: StateFlow<List<WaterQualityClass?>> = _waterNoFilter

    private val _waterFilter = MutableStateFlow<List<WaterQualityClass?>>(emptyList())
    val waterFilter: StateFlow<List<WaterQualityClass?>> = _waterFilter

    fun loadWaterData(context: Context) {
        viewModelScope.launch {
            try {
                val response = RetrofitClientContainers.retrofit.obtenerDatos()
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        _waterInfo.value = data

                        Log.i("WATERDEV", "${data.lastOrNull()?.tds}")

                        // Separar por idAlmacen
                        _waterNoFilter.value = data.filter { it.almacenRaw == 0 }
                        _waterFilter.value = data.filter { it.almacenRaw == 1 }
                    }
                } else {
                    Log.i("WATERDEV", "Error al obtener los datos de los contenedores")
                }
            } catch (e: Exception) {
                Log.e("WATERDEV", "Error de servidor", e)
            }
        }
    }
}
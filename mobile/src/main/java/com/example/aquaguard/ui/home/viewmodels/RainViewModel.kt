package com.example.aquaguard.ui.home.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquaguard.data.config.RetrofitClientRainData
import com.example.aquaguard.data.models.RainClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RainViewModel : ViewModel() {
    private val _rainDays = MutableStateFlow<List<RainClass?>>(emptyList())
    val rainDays: StateFlow<List<RainClass?>> = _rainDays

    fun loadDataRain () {
        viewModelScope.launch {
            try {
                val response = RetrofitClientRainData.retrofit.obtenerDatos()
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        _rainDays.value = data
                    }
                } else {
                    Log.i("RainDEV", "Error al obtener la lista de lluvias")
                }
            } catch (e: Exception) {
                Log.e("Error", "Error: ${e.printStackTrace()}")
            }
        }
    }
}
package com.example.aquaguard.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquaguard.data.config.RetrofitClientSalesData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _isProductOwner = MutableStateFlow(false)
    val isProductOwner: StateFlow<Boolean> = _isProductOwner

    fun verifyProductUser(userId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitClientSalesData.retrofit.getData()
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        _isProductOwner.value = data.any { it.clientId == userId }
                        Log.i("MAINDEB", "Valor: ${_isProductOwner.value}")
                    }
                } else {
                    Log.i("MAINDEB", "Error al obtener los datos")
                }
            } catch (e: Exception) {
                Log.e("MAINDEB", "Error de servidor")
            }
        }
    }
}
package com.example.aquaguard.ui.register

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquaguard.data.config.RetrofitClient
import com.example.aquaguard.data.models.Usuario
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    var errorMessage by mutableStateOf<String?>(null)
        internal set

    fun agregarUsuario(usuario: Usuario, isTermsCheked : Boolean) {
        if (isTermsCheked) {
            viewModelScope.launch {
                try {
                    val response = RetrofitClient.apiServiceUsuario.agregarUsuario(usuario)
                    if (response.isSuccessful) {
                        val usuarioCreado = response.body()
                        Log.i("API", "Usuario creado: $usuarioCreado")
                    } else {
                        errorMessage = "Error al agregar usuario: ${response.code()}"
                        Log.e("API", "Error al agregar usuario: ${response.code()} - ${response.message()}")
                    }
                } catch (e: Exception) {
                    errorMessage = e.message
                    Log.e("API", "Exception: ${e.message}")
                }
            }
        } else {
            errorMessage = "Acepta los terminos y condiciones:"
        }
    }
}
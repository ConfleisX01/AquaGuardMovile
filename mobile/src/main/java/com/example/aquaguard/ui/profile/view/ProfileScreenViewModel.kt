package com.example.aquaguard.ui.profile.view

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquaguard.data.config.RetrofitClient
import com.example.aquaguard.data.models.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileScreenViewModel : ViewModel() {
    private val _formState = MutableStateFlow(Usuario())
    val formState: StateFlow<Usuario?> = _formState

    val errorMessage = mutableStateOf<String?>(null)

    fun cargarUsuario(id: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiServiceUsuario.obtenerUsuariosId(id)
                if (response.isSuccessful) {
                    response.body()?.let { usuario ->
                        _formState.value = usuario
                        Log.i("ProfileM", "Usuario cargado correctamente")
                        Log.i("ProfileM", usuario.nombre)
                    }
                } else {
                    errorMessage.value = "Error al obtener el usuario"
                }
            } catch (e: Exception) {
                errorMessage.value = "Error: ${e.message} "
            }
        }
    }
}
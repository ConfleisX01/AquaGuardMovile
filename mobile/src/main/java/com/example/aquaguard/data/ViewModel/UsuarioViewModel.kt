package com.example.aquaguard.data.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquaguard.data.config.RetrofitClient
import com.example.aquaguard.data.models.Usuario
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class UsuarioViewModel : ViewModel() {

    var usuarios = mutableStateListOf<Usuario>()
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun cargarUsuarios() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiServiceUsuario.obtenerUsuarios()
                if (response.isSuccessful) {
                    usuarios.clear()
                    usuarios.addAll(response.body() ?: emptyList())
                } else {
                    errorMessage = "Error al obtener usuarios"
                }
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }

    fun agregarUsuario(usuario: Usuario) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiServiceUsuario.agregarUsuario(usuario)
                if (response.isSuccessful) {
                    cargarUsuarios()
                } else {
                    errorMessage = "Error al agregar usuario: ${response.code()}"
                }
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }

    fun modificarUsuario(id: Int, usuario: Usuario) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiServiceUsuario.modificarUsuario(id, usuario)
                if (response.isSuccessful) {
                    cargarUsuarios()
                } else {
                    errorMessage = "Error al modificar usuario: ${response.code()}"
                }
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }

    fun eliminarUsuario(id: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiServiceUsuario.eliminarUsuario(id)
                if (response.isSuccessful) {
                    cargarUsuarios()
                } else {
                    errorMessage = "Error al eliminar usuario: ${response.code()}"
                }
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }
}
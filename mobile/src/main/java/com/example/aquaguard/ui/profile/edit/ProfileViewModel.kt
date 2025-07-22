package com.example.aquaguard.ui.profile.edit

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquaguard.data.config.RetrofitClient
import com.example.aquaguard.data.models.Usuario
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileEditViewModel : ViewModel() {
    private val _formState = MutableStateFlow(Usuario())
    val formState: StateFlow<Usuario> = _formState

    private val _mensajeExito = MutableSharedFlow<String>(replay = 0)
    val mensajeExito = _mensajeExito.asSharedFlow()

    fun cargarUsuario(id: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiServiceUsuario.obtenerUsuariosId(id)
                if (response.isSuccessful) {
                    response.body()?.let { usuario ->
                        _formState.value = usuario
                    }
                } else {
                    // Mostrar un error o parecido
                }
            } catch (e: Exception) {
                // Mostrar el error
            }
        }
    }

    fun actualizarCampo(campo: String, valor: String) {
        _formState.update { usuario ->
            when(campo) {
                "nombre" -> usuario.copy(nombre = valor)
                "apellidoPaterno" -> usuario.copy(apellidoPaterno = valor)
                "apellidoMaterno" -> usuario.copy(apellidoMaterno = valor)
                "telefono" -> usuario.copy(telefono = valor)
                "fechaNacimiento" -> usuario.copy(fechaNacimiento = valor)
                "pais" -> usuario.copy(pais = valor)
                "correo" -> usuario.copy(correo = valor)
                "contrasena" -> usuario.copy(contrasena = valor)
                else -> usuario
            }
        }
    }

    fun guardarCambios() {
        val usuario = _formState.value
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiServiceUsuario.modificarUsuario(usuario.id, usuario)
                if (response.isSuccessful) {
                    _mensajeExito.emit("Información actualizada con éxito")
                } else {
                    _mensajeExito.emit("Error al guardar: ${response.code()}")
                }
            } catch (e: Exception) {
                _mensajeExito.emit("Error: ${e.message}")
            }
        }
    }
}
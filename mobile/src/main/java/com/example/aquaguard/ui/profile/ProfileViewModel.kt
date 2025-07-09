package com.example.aquaguard.ui.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquaguard.data.config.RetrofitClient
import com.example.aquaguard.data.models.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _formState = MutableStateFlow(Usuario())
    val formState: StateFlow<Usuario> = _formState

    val errorMessage = mutableStateOf<String?>(null)

    fun cargarUsuario(id: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiServiceUsuario.obtenerUsuariosId(id)
                if (response.isSuccessful) {
                    response.body()?.let { usuario ->
                        _formState.value = usuario
                        Log.i("ProfileM", "Usuario cargado correctamente")
                    }
                } else {
                    errorMessage.value = "Error al obtener el usuario"
                }
            } catch (e: Exception) {
                errorMessage.value = "Error: ${e.message} "
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
                    Log.i("ProfileVM", "Usuario actualizado con éxito")
                } else {
                    errorMessage.value = "Error al guardar: ${response.code()}"
                }
            } catch (e: Exception) {
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }
}
package com.example.aquaguard.ui.register

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.aquaguard.data.config.RetrofitClient
import com.example.aquaguard.data.models.Usuario
import com.example.aquaguard.ui.navigation.Screen
import kotlinx.coroutines.launch
import java.util.Objects

class RegisterViewModel : ViewModel() {
    var errorMessage by mutableStateOf<String?>(null)
        internal set

    fun agregarUsuario(usuario: Usuario, navController: NavController) {
        if (verificarUsuario(usuario)) {
            viewModelScope.launch {
                try {
                    val response = RetrofitClient.apiServiceUsuario.agregarUsuario(usuario)
                    if (response.isSuccessful) {
                        val usuarioCreado = response.body()
                        Log.i("API", "Usuario creado: $usuarioCreado")
                        navController.navigate(Screen.Login.route)
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
            errorMessage = "Ningun campo debe de estar vacio"
        }
    }

    fun verificarUsuario(usuario: Usuario): Boolean {
        if (Objects.isNull(usuario)) return false

        if (usuario.nombre.isEmpty()) return false

        if (usuario.apellidoMaterno.isEmpty()) return false

        if (usuario.apellidoPaterno.isEmpty()) return false

        if (usuario.correo.isEmpty()) return false

        if (usuario.pais.isEmpty()) return false

        if (usuario.contrasena.isEmpty()) return false

        if (usuario.telefono.isEmpty()) return false

        return true
    }
}
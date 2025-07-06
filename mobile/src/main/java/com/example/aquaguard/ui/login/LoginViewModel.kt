package com.example.aquaguard.ui.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquaguard.data.config.RetrofitClient
import com.example.aquaguard.data.config.SessionManager
import com.example.aquaguard.data.models.Usuario
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var usuarioLogueado: Usuario? = null
        private set

    fun login(correo: String, contrasena: String, context: Context, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiServiceUsuario.obtenerUsuarios()
                if (response.isSuccessful) {
                    val usuarios = response.body()
                    val user = usuarios?.find {
                        it.correo == correo && it.contrasena == contrasena
                    }

                    Log.i("USUARIO", user.toString())

                    if (user != null) {
                        usuarioLogueado = user
                        SessionManager(context).saveUser(user)
                        SessionManager(context).guardarUsuario(user.id)
                        onSuccess()
                    } else {
                        onError("Credenciales incorrectas")
                    }
                } else {
                    onError("Error al comunicarse con el servidor: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Excepción: ${e.message}")
                onError("Error de conexión: ${e.message}")
            }
        }
    }
}

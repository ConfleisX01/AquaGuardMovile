package com.example.aquaguard.ui.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquaguard.data.config.RetrofitClientUserAuth
import com.example.aquaguard.data.config.SessionManager
import com.example.aquaguard.data.models.UserAuth
import com.example.aquaguard.data.models.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _isProductOwner = MutableStateFlow(false)
    val isProductOwner: StateFlow<Boolean> = _isProductOwner

    var usuarioLogueado: Usuario? = null
        private set

    fun login(
        correo: String,
        contrasena: String,
        context: Context,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val userDataAuth = UserAuth(correo, contrasena)
                val response = RetrofitClientUserAuth.retrofit.iniciarSesion(userDataAuth)

                if (response.isSuccessful) {
                    val authResponse = response.body()

                    if (authResponse != null && authResponse.usuario.id > 0) {
                        val user = authResponse.usuario
                        val token = authResponse.token

                        usuarioLogueado = user
                        val sessionManager = SessionManager(context)
                        sessionManager.saveUser(user)
                        sessionManager.guardarUsuario(user.id)
                        // sessionManager.saveToken(token) por ahora no :)
                        onSuccess()
                        Log.d("AuthDEV", "Login exitoso. ID: ${user.id}, Token: $token")
                    } else {
                        onError("Credenciales incorrectas o usuario no encontrado.")
                    }
                } else {
                    val errorMessage = response.errorBody()?.string()
                    onError("Error al comunicarse con el servidor: ${response.code()}")
                    Log.e("AuthDEV", "Error: $errorMessage")
                }
            } catch (e: Exception) {
                onError("Error de conexión: ${e.message}")
            }
        }
    }

}

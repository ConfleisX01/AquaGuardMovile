package com.example.aquaguard.data.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.aquaguard.data.models.Usuario
import com.example.aquaguard.data.repository.UsuarioRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UsuarioViewModel(private val repository: UsuarioRepository) : ViewModel() {

    var usuarios = mutableStateListOf<Usuario>()
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun cargarUsuarios() {
        viewModelScope.launch {
            try {
                val lista = repository.obtenerUsuarios()
                usuarios.clear()
                usuarios.addAll(lista)
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }

    fun agregarUsuario(usuario: Usuario) {
        viewModelScope.launch {
            val exito = repository.agregarUsuario(usuario)
            if (exito) cargarUsuarios()
            else errorMessage = "Error al agregar usuario"
        }
    }

    fun modificarUsuario(id: Int, usuario: Usuario) {
        viewModelScope.launch {
            val exito = repository.modificarUsuario(id, usuario)
            if (exito) cargarUsuarios()
            else errorMessage = "Error al modificar usuario"
        }
    }

    fun eliminarUsuario(id: Int) {
        viewModelScope.launch {
            val exito = repository.eliminarUsuario(id)
            if (exito) cargarUsuarios()
            else errorMessage = "Error al eliminar usuario"
        }
    }
}

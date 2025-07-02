package com.example.aquaguard.data.repository

import com.example.aquaguard.data.interfaces.ApiServiceUsuario
import com.example.aquaguard.data.models.Usuario
import retrofit2.Response

class UsuarioRepository(private val apiService: ApiServiceUsuario) {

    suspend fun obtenerUsuarios(): Response<List<Usuario>> {
        return apiService.obtenerUsuarios()
    }

    suspend fun agregarUsuario(usuario: Usuario): Boolean {
        val response = apiService.agregarUsuario(usuario)
        return response.isSuccessful
    }

    suspend fun modificarUsuario(id: Int, usuario: Usuario): Boolean {
        val response = apiService.modificarUsuario(id, usuario)
        return response.isSuccessful
    }

    suspend fun eliminarUsuario(id: Int): Boolean {
        val response = apiService.eliminarUsuario(id)
        return response.isSuccessful
    }
}
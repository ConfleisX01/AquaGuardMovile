package com.example.aquaguard.data.models

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiServiceUsuario {
    @PUT("/Usuarios/modificarUsuario/{id}")
    suspend fun modificarUsuario(
        @Path("id") id: Int,
        @Body usuario: Usuario
    ): Response<Unit>
}
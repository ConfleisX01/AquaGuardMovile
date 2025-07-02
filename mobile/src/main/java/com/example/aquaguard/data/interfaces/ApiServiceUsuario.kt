package com.example.aquaguard.data.interfaces

import com.example.aquaguard.data.models.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.DELETE
import retrofit2.http.Path

interface ApiServiceUsuario {
    @GET("/Usuarios")
    suspend fun obtenerUsuarios(): Response<List<Usuario>>

    @POST("/Usuarios")
    suspend fun agregarUsuario(@Body usuario: Usuario): Response<Void>

    @PUT("/Usuarios/modificarUsuario/{id}")
    suspend fun modificarUsuario(@Path("id") id: Int, @Body usuario: Usuario): Response<Void>

    @DELETE("/Usuarios/eliminarUsuario/{id}")
    suspend fun eliminarUsuario(@Path("id") id: Int): Response<Void>
}

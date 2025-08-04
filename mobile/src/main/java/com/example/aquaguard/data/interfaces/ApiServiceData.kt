package com.example.aquaguard.data.interfaces

import com.example.aquaguard.data.models.AirInformation
import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceData {
    @GET("LecturaCalidadAires")
    suspend fun obtenerDatos(): Response<List<AirInformation>>
}
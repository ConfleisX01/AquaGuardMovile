package com.example.aquaguard.data.interfaces

import com.example.aquaguard.data.models.RainClass
import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceRain {
    @GET("Lluvias")
    suspend fun obtenerDatos(): Response<List<RainClass>>
}
package com.example.aquaguard.data.interfaces

import com.example.aquaguard.data.models.WaterQualityClass
import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceWaterQuality {
    @GET("CalidadAguas")
    suspend fun obtenerDatos(): Response<List<WaterQualityClass>>
}
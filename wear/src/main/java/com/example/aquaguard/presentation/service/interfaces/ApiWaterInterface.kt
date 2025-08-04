package com.example.aquaguard.presentation.service.interfaces

import com.example.aquaguard.presentation.models.WaterQualityClass
import retrofit2.Response
import retrofit2.http.GET

interface ApiWaterInterface {
    @GET("CalidadAguas")
    suspend fun obtenerDatos(): Response<List<WaterQualityClass>>
}
package com.example.aquaguard.data.interfaces

import com.example.aquaguard.data.models.DataInformation
import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceData {
    @GET("DataInformations")
    suspend fun obtenerDatos(): Response<List<DataInformation>>
}
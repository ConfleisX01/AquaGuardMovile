package com.example.aquaguard.data.interfaces

import com.example.aquaguard.data.models.SalesModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiSales {
    @GET("Ventas")
    suspend fun getData(): Response<List<SalesModel>>
}
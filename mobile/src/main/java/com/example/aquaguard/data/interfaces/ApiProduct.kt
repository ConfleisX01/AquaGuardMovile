package com.example.aquaguard.data.interfaces

import com.example.aquaguard.data.models.ProductModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiProduct {
    @GET("Productoes")
    suspend fun getData(): Response<List<ProductModel>>
}
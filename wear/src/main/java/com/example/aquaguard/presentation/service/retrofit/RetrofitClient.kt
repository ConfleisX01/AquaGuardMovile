package com.example.aquaguard.presentation.service.retrofit

import com.example.aquaguard.presentation.service.interfaces.ApiWaterInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://7xrpfkt5-5002.usw3.devtunnels.ms/api/"

object RetrofitClientContainers { // Cliente para la calidad de agua de los contenedores
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiWaterInterface::class.java)
}
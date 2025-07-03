package com.example.aquaguard.data.config

import com.example.aquaguard.data.interfaces.ApiServiceUsuario
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:5002/api/"


    val apiServiceUsuario: ApiServiceUsuario by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceUsuario::class.java)
    }
}


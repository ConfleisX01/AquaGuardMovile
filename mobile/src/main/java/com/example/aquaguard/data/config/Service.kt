package com.example.aquaguard.data.config

import com.example.aquaguard.data.interfaces.ApiServiceClima
import com.example.aquaguard.data.interfaces.ApiServiceUsuario
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://10.246.34.2:5002/api/" // Cuando es emulador del android
    //private const val BASE_URL = "http://192.168.1.11:5002/api/" // Cuando es dispositivo fisico
    //private const val BASE_URL = "http://192.168.0.200:5002/api/" // Server en casa de confleis

    val apiServiceUsuario: ApiServiceUsuario by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceUsuario::class.java)
    }
}

object RetrofitClientWeahter {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.open-meteo.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: ApiServiceClima = retrofit.create(ApiServiceClima::class.java)
}

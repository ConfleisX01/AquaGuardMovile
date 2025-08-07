package com.example.aquaguard.data.config

import com.example.aquaguard.data.interfaces.ApiNews
import com.example.aquaguard.data.interfaces.ApiProduct
import com.example.aquaguard.data.interfaces.ApiSales
import com.example.aquaguard.data.interfaces.ApiServiceAuth
import com.example.aquaguard.data.interfaces.ApiServiceClima
import com.example.aquaguard.data.interfaces.ApiServiceData
import com.example.aquaguard.data.interfaces.ApiServiceRain
import com.example.aquaguard.data.interfaces.ApiServiceUsuario
import com.example.aquaguard.data.interfaces.ApiServiceWaterQuality
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://7xrpfkt5-5002.usw3.devtunnels.ms/api/"

object RetrofitClient { // Cliente para los usuarios
    val apiServiceUsuario: ApiServiceUsuario by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceUsuario::class.java)
    }
}

object RetrofitClientUserAuth { // Cliente para la auntenticacion del usuario

    val retrofit: ApiServiceAuth by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceAuth::class.java)
    }
}

object RetrofitClientWeahter { // Cliente para el clima
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.open-meteo.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: ApiServiceClima = retrofit.create(ApiServiceClima::class.java)
}

object RetrofitClientAqi { // Cliente para la calidad del aire

     val retrofit: ApiServiceData by lazy {
         Retrofit.Builder()
             .baseUrl(BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
             .create(ApiServiceData::class.java)
     }
}

object RetrofitClientRainData { // Cliente para datos de lluvia

    val retrofit: ApiServiceRain by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceRain::class.java)
    }
}

object RetrofitClientProductData { // Cliente para datos de los productos

    val retrofit: ApiProduct by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiProduct::class.java)
    }
}

object RetrofitClientSalesData { // Cliente para datos de los productos

    val retrofit: ApiSales by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiSales::class.java)
    }
}

object RetrofitClientNewsData { // Cliente para datos de las noticias del foro

    val retrofit: ApiNews by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiNews::class.java)
    }
}

object RetrofitClientContainers { // Cliente para la calidad de agua de los contenedores

    val retrofit: ApiServiceWaterQuality by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceWaterQuality::class.java)
    }
}

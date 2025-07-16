package com.example.aquaguard.data.interfaces

import com.example.aquaguard.data.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceClima {
    @GET("forecast")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("current_weather") currentWeather: Boolean = true,
        @Query("hourly") hourly: String = "relativehumidity_2m,precipitation_probability,cloudcover",
        @Query("timezone") timezone: String = "auto"
    ): WeatherResponse
}
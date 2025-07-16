package com.example.aquaguard.data.models

data class WeatherResponse(
    val current_weather: CurrentWeather,
    val hourly: HourlyData
)

data class CurrentWeather(
    val temperature: Double,
    val windspeed: Double,
    val weathercode: Int,
    val time: String
)

data class HourlyData(
    val time: List<String>,
    val relativehumidity_2m: List<Double>,
    val precipitation_probability: List<Double>,
    val cloudcover: List<Double>
)

data class WeatherInfo(
    val temperature: Double,
    val windspeed: Double,
    val humidity: Double,
    val precipitation: Double,
    val cloudcover: Double,
    val description: String,
    val iconCode: Int // puedes usarlo para íconos personalizados
)
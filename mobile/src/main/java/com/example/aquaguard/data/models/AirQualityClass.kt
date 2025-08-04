package com.example.aquaguard.data.models

import com.google.gson.annotations.SerializedName

data class AirInformation (
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("aqi")
    val aqi: String = "",
)

data class AirInformationWithAQI (
    val id: Int,
    val aqiRaw: Int,
    val aqiEstimado: Int,
)

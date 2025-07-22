package com.example.aquaguard.data.models

import com.google.gson.annotations.SerializedName

data class DataInformation (
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("aqi")
    val aqi: String = "",

    @SerializedName("tds")
    val tds: String = "",

    @SerializedName("temperature")
    val temperature: String = "",

    @SerializedName("ph")
    val ph: String = ""
)

data class DataInformationWithAQI(
    val id: Int,
    val aqiRaw: Int,
    val aqiEstimado: Int,
    val tds: String?,
    val temperature: String?,
    val ph: String?
)

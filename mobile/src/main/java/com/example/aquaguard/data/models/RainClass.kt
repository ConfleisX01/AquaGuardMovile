package com.example.aquaguard.data.models

import com.google.gson.annotations.SerializedName

data class RainClass (
    @SerializedName("id")
    val id: Int,

    @SerializedName("tiempo")
    val tiempo: String,

    @SerializedName("lluviaDetectada")
    val lluviaDetectada: Boolean
)
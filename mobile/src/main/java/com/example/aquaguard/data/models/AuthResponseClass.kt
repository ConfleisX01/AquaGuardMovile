package com.example.aquaguard.data.models

import com.google.gson.annotations.SerializedName

data class AuthResponseClass (
    @SerializedName("token")
    val token: String,

    @SerializedName("usuario")
    val usuario: Usuario
)
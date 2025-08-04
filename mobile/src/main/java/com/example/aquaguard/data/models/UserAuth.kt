package com.example.aquaguard.data.models

import com.google.gson.annotations.SerializedName

data class UserAuth (
    @SerializedName("correo")
    val correo : String,

    @SerializedName("contrasena")
    val contrasena : String
)
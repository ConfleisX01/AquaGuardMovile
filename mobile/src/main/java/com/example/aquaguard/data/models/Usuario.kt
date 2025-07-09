package com.example.aquaguard.data.models

import com.google.gson.annotations.SerializedName

data class Usuario (
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("nombre")
    val nombre: String = "",

    @SerializedName("apellidoPaterno")
    val apellidoPaterno: String = "",

    @SerializedName("apellidoMaterno")
    val apellidoMaterno: String = "",

    @SerializedName("telefono")
    val telefono: String = "",

    @SerializedName("fechaNacimiento")
    val fechaNacimiento: String = "",

    @SerializedName("pais")
    val pais: String = "",

    @SerializedName("correo")
    val correo: String = "",

    @SerializedName("contrasena")
    val contrasena: String = ""
)

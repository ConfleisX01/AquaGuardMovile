package com.example.aquaguard.data.models

data class Usuario (
    val id: Int,
    val nombre: String,
    val apellidoPaterno: String,
    val apellidoMaterno: String,
    val telefono: String,
    val fechaNacimiento: String,
    val pais: String,
    val correo: String,
    val contrasena: String
)
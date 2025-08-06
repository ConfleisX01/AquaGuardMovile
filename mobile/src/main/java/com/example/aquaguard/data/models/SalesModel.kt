package com.example.aquaguard.data.models

import com.google.gson.annotations.SerializedName

data class SalesModel (
    @SerializedName("id")
    val id: Int,

    @SerializedName("fechaVenta")
    val soldDate: String,

    @SerializedName("clienteId")
    val clientId: Int,

    @SerializedName("cliente")
    val client: Usuario?, // Puede ser nulo

    @SerializedName("usuarioId")
    val userId: Int,

    @SerializedName("usuario")
    val user: Usuario?, // Puede ser nulo

    @SerializedName("total")
    val total: Int,

    @SerializedName("montoPagado")
    val mountPaid: Int,

    @SerializedName("cambio")
    val change: Int,

    @SerializedName("detalles")
    val details: String? // Puede ser nulo
)
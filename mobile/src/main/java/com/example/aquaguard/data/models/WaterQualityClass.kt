package com.example.aquaguard.data.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class WaterQualityClass (
    @SerializedName("id")
    val id : Int,

    @SerializedName("almacen")
    val almacenRaw: Int,

    @SerializedName("tiempo")
    val tiempo : String,

    @SerializedName("cantidad")
    val cantidad: Float,

    @SerializedName("ph")
    val ph : Float,

    @SerializedName("temperatura")
    val temperatura : Float,

    @SerializedName("tds")
    val tds : Int

) {
    val tipoTanque : TanqueEnum
        get() = TanqueEnum.fromInt(almacenRaw)

    fun getFechaFormateada(): String {
        return try {
            val fechaHora = LocalDateTime.parse(tiempo)
            val formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy 'a las' HH:mm")
            fechaHora.format(formatter)
        } catch (e: Exception) {
            "Fecha inválida"
        }
    }

    fun toModelString(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromModelString(json: String): WaterQualityClass {
            return Gson().fromJson(json, WaterQualityClass::class.java)
        }
    }
}


enum class TanqueEnum(val tipoTanque: Int) {
    SINFILTRAR(0),
    FILTRADA(1);

    companion object {
        fun fromInt(value: Int): TanqueEnum = entries.first { it.tipoTanque == value }
    }
}

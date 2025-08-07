package com.example.aquaguard.data.models

import com.google.gson.annotations.SerializedName

data class ProductModel (
    @SerializedName("id")
    val id: Int,

    @SerializedName("nombre")
    val name: String,

    @SerializedName("descripcion")
    val description: String,

    @SerializedName("imagen")
    val image: String,

    @SerializedName("precioBase")
    val price: Float,

    @SerializedName("activo")
    val isActive: Boolean,

    @SerializedName("fechaCreacion")
    val creationDate: String,

    @SerializedName("ultimaModificacion")
    val lastModification: String,

    @SerializedName("usuarioId")
    val userId: Int,

    @SerializedName("imagenFile")
    val imageFile: String,

    @SerializedName("pdfManual")
    val pdfManual: String,

    @SerializedName("stockActual")
    val stock: Int,

    @SerializedName("pdfFile")
    val pdfFile: String
) {
}
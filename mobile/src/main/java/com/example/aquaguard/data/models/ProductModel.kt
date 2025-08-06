package com.example.aquaguard.data.models

import com.google.gson.annotations.SerializedName

data class ProductModel (
    @SerializedName("id")
    val id: Int,

    @SerializedName("nombre")
    val name: String,

    @SerializedName("descripcion")
    val description: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("price")
    val price: Float,

    @SerializedName("isActive")
    val isActive: Boolean,

    @SerializedName("creationDate")
    val creationDate: String,

    @SerializedName("lastModification")
    val lastModification: String,

    @SerializedName("usuarioId")
    val userId: Int,

    @SerializedName("imageFile")
    val imageFile: String,

    @SerializedName("pdfManual")
    val pdfManual: String,

    @SerializedName("stock")
    val stock: Int,

    @SerializedName("pdfFile")
    val pdfFile: String
) {
}
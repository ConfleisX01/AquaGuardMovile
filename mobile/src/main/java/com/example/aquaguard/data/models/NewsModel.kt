package com.example.aquaguard.data.models

import com.google.gson.annotations.SerializedName

data class NewsModel (
    @SerializedName("id")
    val id: Int,

    @SerializedName("titulo")
    val title: String,

    @SerializedName("descripcion")
    val description: String,

    @SerializedName("importante")
    val isImportant: Boolean,

    @SerializedName("fechaNoticia")
    val newsDate: String
)
package com.example.aquaguard.data.interfaces

import com.example.aquaguard.data.models.NewsModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiNews {
    @GET("Noticias")
    suspend fun getNews(): Response<List<NewsModel>>
}
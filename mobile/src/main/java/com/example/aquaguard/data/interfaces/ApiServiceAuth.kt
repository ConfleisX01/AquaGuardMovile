package com.example.aquaguard.data.interfaces

import com.example.aquaguard.data.models.AuthResponseClass
import com.example.aquaguard.data.models.UserAuth
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServiceAuth {
    @POST("LoginSesion")
    suspend fun iniciarSesion(@Body usuario: UserAuth): Response<AuthResponseClass>
}
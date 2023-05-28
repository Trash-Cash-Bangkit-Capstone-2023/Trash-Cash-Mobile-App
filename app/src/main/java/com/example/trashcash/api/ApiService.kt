package com.example.trashcash.api

import com.example.trashcash.model.ProfileResponse
import com.example.trashcash.model.RegisterRequest
import com.example.trashcash.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService{
    @POST("/v1/auth/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @GET("/v1/profile")
    fun getProfile(
        @Header("Authorization")
        token: String
    ): Call<ProfileResponse>
}
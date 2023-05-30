package com.example.trashcash.api

import com.example.trashcash.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService{
    @POST("/v1/auth/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @GET("/v1/profile")
    fun getProfile(
        @Header("Authorization")
        token: String
    ): Call<ProfileResponse>

    @PUT("/v1/profile")
    fun editProfile(
        @Header("Authorization")
        token: String,

        @Body
        userRequest: EditRequest
    ): Call<EditResponse>
}
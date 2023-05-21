package com.example.trashcash.api

import com.example.trashcash.model.RegisterRequest
import com.example.trashcash.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService{
    @POST("/v1/auth/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>
}
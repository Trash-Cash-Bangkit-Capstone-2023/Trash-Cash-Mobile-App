package com.example.trashcash.model

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: UserData
)


package com.example.trashcash.model

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: UserData
)

data class UserData(
    @SerializedName("user")
    val user: User
)

data class User(
    @SerializedName("uid")
    val uid: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("province")
    val province: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("created_at")
    val createdAt: String
)
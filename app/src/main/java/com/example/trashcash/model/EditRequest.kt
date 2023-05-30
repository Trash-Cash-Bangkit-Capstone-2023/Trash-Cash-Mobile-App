package com.example.trashcash.model

import com.google.gson.annotations.SerializedName

data class EditRequest(
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
)
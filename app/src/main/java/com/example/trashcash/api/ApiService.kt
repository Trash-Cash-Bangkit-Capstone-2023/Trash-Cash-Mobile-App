package com.example.trashcash.api

import com.example.trashcash.model.*
import retrofit2.Call
import retrofit2.http.*

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

    @GET("v1/posts")
    fun getPosts(
        @Header("Authorization")
        token: String,

        @Query("title")
        title: String?,

        @Query("category")
        category: String?,

        @Query("user_uid")
        userUid: String?
    ): Call<PostsResponse>

    @GET("v1/posts/{id}")
    fun getPost(
        @Path("id")
        id:String,

        @Header("Authorization")
        token: String,
    ): Call<PostDetailResponse>

    @GET("v1/users/{id}/posts")
    fun getPostsByUserId(
        @Path("id")
        id:String,

        @Header("Authorization")
        token: String,
    ): Call<UserPostsResponse>
}
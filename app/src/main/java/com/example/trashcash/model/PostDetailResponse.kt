package com.example.trashcash.model

import com.google.gson.annotations.SerializedName

data class PostDetailResponse(

	@field:SerializedName("data")
	val data: Data
)

data class Data(

	@field:SerializedName("post")
	val post: Post
)

data class Post(

	@field:SerializedName("quantity")
	val quantity: String,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("image_url")
	val imageUrl: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("user")
	val user: User,

	@field:SerializedName("tags")
	val tags: List<String>,

	@field:SerializedName("user_uid")
	val userUid: String
)

data class EditResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: UserData
)
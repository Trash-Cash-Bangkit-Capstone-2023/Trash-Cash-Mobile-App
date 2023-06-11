package com.example.trashcash.model

import com.google.gson.annotations.SerializedName

data class UserPostsResponse(

	@field:SerializedName("data")
	val data: UserPostsData
)

data class UserPostItem(

	@field:SerializedName("quantity")
	val quantity: String,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("image_url")
	val imageUrl: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("tags")
	val tags: List<String>,

	@field:SerializedName("user_uid")
	val userUid: String
)

data class UserPostsData(

	@field:SerializedName("posts")
	val posts: List<UserPostItem>
)

package com.example.trashcash.model

import com.google.gson.annotations.SerializedName

data class PostsResponse(
	@field:SerializedName("data")
	val data: ListPost
)

data class ListPost(

	@field:SerializedName("posts")
	val posts: List<PostItem>
)

data class PostItem(

	@field:SerializedName("quantity")
	val quantity: String,

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
	val userUid: String,

	@field:SerializedName("price")
	val price: Long
)

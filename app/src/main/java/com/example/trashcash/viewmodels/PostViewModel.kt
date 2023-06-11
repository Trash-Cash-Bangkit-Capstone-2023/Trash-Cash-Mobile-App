package com.example.trashcash.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trashcash.api.ApiConfig
import com.example.trashcash.model.Post
import com.example.trashcash.model.PostDetailResponse
import com.example.trashcash.model.PostItem
import com.example.trashcash.model.PostsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel: ViewModel() {

    private val _posts = MutableLiveData<List<PostItem>>()
    val posts: LiveData<List<PostItem>> = _posts

    private val _post = MutableLiveData<Post>()
    val post: LiveData<Post> = _post

    fun filterPostByTitle( keyword: String) {
        _posts.value = _posts.value?.filter { it.title.contains(keyword, ignoreCase = true) }
    }

    fun getPosts(token: String, title: String?, category: String?){
        val client = ApiConfig.getApiService().getPosts("Bearer $token", title, category, null)

        client.enqueue(object : Callback<PostsResponse> {
            override fun onResponse(call: Call<PostsResponse>, response: Response<PostsResponse>) {

                if(response.isSuccessful){
                    val apiResponse = response.body()

                    if (apiResponse != null) {
                        _posts.value = apiResponse.data.posts
                    }
                }else{
                    Log.e("PostViewModel", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PostsResponse>, t: Throwable) {
                Log.e("PostViewModel", "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getPost(token: String, id: String){
        val client = ApiConfig.getApiService().getPost(id, "Bearer $token")

        client.enqueue(object : Callback<PostDetailResponse> {
            override fun onResponse(
                call: Call<PostDetailResponse>,
                response: Response<PostDetailResponse>
            ) {
                if(response.isSuccessful){
                    val apiResponse = response.body()

                    if(apiResponse != null){
                        _post.value = apiResponse.data.post
                    }
                }
            }

            override fun onFailure(call: Call<PostDetailResponse>, t: Throwable) {
                Log.e("PostViewModel", "onFailure: ${t.message.toString()}")
            }
        })
    }
}
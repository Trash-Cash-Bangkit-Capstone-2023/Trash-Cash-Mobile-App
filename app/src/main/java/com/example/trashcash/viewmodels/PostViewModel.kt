package com.example.trashcash.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trashcash.api.ApiConfig
import com.example.trashcash.model.PostItem
import com.example.trashcash.model.PostsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel: ViewModel() {

    private val _posts = MutableLiveData<List<PostItem>>()
    val posts: LiveData<List<PostItem>> = _posts

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
}
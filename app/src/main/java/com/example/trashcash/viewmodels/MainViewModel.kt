package com.example.trashcash.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trashcash.api.ApiConfig
import com.example.trashcash.model.ProfileResponse
import com.example.trashcash.model.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    companion object{
        const val TAG = "MainViewModel"
    }

    private val _profile = MutableLiveData<UserData>()
    val profile: LiveData<UserData> = _profile

    fun getUserProfile(token: String){
        val client = ApiConfig.getApiService().getProfile("Bearer $token")

        client.enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {

                if(response.isSuccessful){
                    val apiResponse = response.body()

                    if (apiResponse != null) {
                        _profile.value= apiResponse.data
                    }
                }else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}
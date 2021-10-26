package com.ryfazrin.githubusers.ui.followers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ryfazrin.githubusers.Users
import com.ryfazrin.githubusers.webapi.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {

    private val _users = MutableLiveData<List<Users>>()
    val users: LiveData<List<Users>> = _users

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun showListFollowers(getUser: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailFollowers(getUser)
        client.enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _users.value = responseBody
                    }
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "FollowersFragment"
    }
}
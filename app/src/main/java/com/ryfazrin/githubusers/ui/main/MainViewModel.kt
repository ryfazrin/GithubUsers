package com.ryfazrin.githubusers.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ryfazrin.githubusers.Users
import com.ryfazrin.githubusers.UsersSearch
import com.ryfazrin.githubusers.API.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _users = MutableLiveData<List<Users>>()
    val users: LiveData<List<Users>> = _users

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isMessage = MutableLiveData<Boolean>()
    val isMessage: LiveData<Boolean> = _isMessage

    init {
        showFirstListUsers()
    }

    fun showFirstListUsers() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUser()
        client.enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _isMessage.value = false
                        _users.value = responseBody
                    }
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                _isLoading.value = false
                _isMessage.value = true
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun showSearchUser(query: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getSearchUser(query)
        client.enqueue(object : Callback<UsersSearch> {
            override fun onResponse(call: Call<UsersSearch>, response: Response<UsersSearch>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _isMessage.value = false
                        _users.value = responseBody.items
                    }
                }
            }

            override fun onFailure(call: Call<UsersSearch>, t: Throwable) {
                _isLoading.value = false
                _isMessage.value = true
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
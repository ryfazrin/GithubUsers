package com.ryfazrin.githubusers.ui.detailuser

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ryfazrin.githubusers.webapi.ApiConfig
import com.ryfazrin.githubusers.UserDetailResponse
import com.ryfazrin.githubusers.database.UserFavorite
import com.ryfazrin.githubusers.repository.UserFavoriteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application) : ViewModel() {

    private val mUserFavoriteRepository: UserFavoriteRepository = UserFavoriteRepository(application)

    private val _user = MutableLiveData<UserDetailResponse>()
    val user: LiveData<UserDetailResponse> = _user

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun findUser(getUser: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(getUser)
        client.enqueue(object : Callback<UserDetailResponse> {
            override fun onResponse(
                call: Call<UserDetailResponse>,
                response: Response<UserDetailResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _user.value = responseBody
                    }
                }
            }

            override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun insertFavorite(userFavorite: UserFavorite) {
        mUserFavoriteRepository.insert(userFavorite)
    }

    companion object {
        private const val TAG = "DetailUserActivity"
    }
}
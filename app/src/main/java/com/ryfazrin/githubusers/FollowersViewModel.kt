package com.ryfazrin.githubusers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ryfazrin.githubusers.API.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {

    private val _users = MutableLiveData<List<Users>>()
    val users: LiveData<List<Users>> = _users

    fun showListFollowers(getUser: String) {
//        val data = requireArguments().getString(FollowersFragment.EXTRA_USER).toString()

        // test ambil data
//        Log.d("fragmentfollow", "Tes: ${data}")
//        binding.testText.text = data
        // val client = ApiConfig.getApiService().getDetailFollowers(DetailUserActivity.EXTRA_USER)
        val client = ApiConfig.getApiService().getDetailFollowers(getUser)
        client.enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _users.value = responseBody
//                        setFollowerData(responseBody)
                    }
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "FollowersFragment"
    }
}
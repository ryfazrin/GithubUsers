package com.ryfazrin.githubusers.api

import com.ryfazrin.githubusers.BuildConfig
import com.ryfazrin.githubusers.UserDetailResponse
import com.ryfazrin.githubusers.Users
import com.ryfazrin.githubusers.UsersSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("users")
    fun getUser(): Call<List<Users>>

    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("search/users?")
    fun getSearchUser(
        @Query("q") q: String
    ): Call<UsersSearch>

    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("users/{login}")
    fun getDetailUser(
        @Path("login") login: String
    ): Call<UserDetailResponse>

    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("users/{login}/followers")
    fun getDetailFollowers(
        @Path("login") login: String
    ): Call<List<Users>>

    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("users/{login}/following")
    fun getDetailFollowing(
        @Path("login") login: String
    ): Call<List<Users>>
}
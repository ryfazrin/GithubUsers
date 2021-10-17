package com.ryfazrin.githubusers.API

import com.ryfazrin.githubusers.UserDetailResponse
import com.ryfazrin.githubusers.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @Headers("Authorization: token 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")
    @GET("users")
    fun getUser(): Call<List<Users>>

    @Headers("Authorization: token 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")
    @GET("users/{login}")
    fun getDetailUser(
        @Path("login") login: String
    ): Call<UserDetailResponse>

    @Headers("Authorization: token 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")
    @GET("users/{login}/followers")
    fun getDetailFollowers(
        @Path("login") login: String
    ): Call<List<Users>>
}
package com.ryfazrin.githubusers

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @Headers("Authorization: token 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")

    @GET("users")
    fun getUser(): Call<List<Users>>

    @GET("users/{login}")
    fun getDetailUser(
        @Path("login") login: String
    ): Call<UserDetailResponse>
}
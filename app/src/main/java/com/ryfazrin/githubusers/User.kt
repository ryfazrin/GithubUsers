package com.ryfazrin.githubusers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String,
    val username: String,
    val location: String,
    val avatar: Int,
    val repository: String,
    val company: String,
    val followers: String,
    val following: String
) : Parcelable

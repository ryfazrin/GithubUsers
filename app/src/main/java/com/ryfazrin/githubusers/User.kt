package com.ryfazrin.githubusers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var name: String,
    var username: String,
    var location: String,
    var avatar: Int,
    var repository: String,
    var company: String,
    var followers: String,
    var following: String
) : Parcelable

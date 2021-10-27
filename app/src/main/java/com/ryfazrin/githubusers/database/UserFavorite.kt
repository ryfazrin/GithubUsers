package com.ryfazrin.githubusers.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class UserFavorite(
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "id")
//    var id: Int = 0,

    @PrimaryKey
    @ColumnInfo(name = "login")
    var login: String = "username",

    @ColumnInfo(name = "avatar")
    var avatar: String? = null,

    @ColumnInfo(name = "type")
    var type: String? = null
) : Parcelable

package com.ryfazrin.githubusers.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserFavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userFavorite: UserFavorite)

    @Update
    fun update(userFavorite: UserFavorite)

    @Delete
    fun delete(userFavorite: UserFavorite)

    @Query("SELECT * FROM UserFavorite")
    fun getAllUserFavorite(): LiveData<List<UserFavorite>>

    @Query("SELECT * FROM UserFavorite WHERE login=:login")
    fun getUserFavoriteById(login: String): LiveData<List<UserFavorite>>
}
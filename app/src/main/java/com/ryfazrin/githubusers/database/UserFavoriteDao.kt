package com.ryfazrin.githubusers.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserFavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(userFavorite: UserFavorite)

    @Update
    fun update(userFavorite: UserFavorite)

    @Delete
    fun delete(userFavorite: UserFavorite)

    @Query("SELECT * FROM userfavorite")
    fun getAllUserFavorite(): LiveData<List<UserFavorite>>
}
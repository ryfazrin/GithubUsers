package com.ryfazrin.githubusers.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.ryfazrin.githubusers.database.UserFavorite
import com.ryfazrin.githubusers.database.UserFavoriteDao
import com.ryfazrin.githubusers.database.UserFavoriteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UserFavoriteRepository(application: Application) {
    private val mUserFavoriteDao: UserFavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = UserFavoriteRoomDatabase.getDatabase(application)
        mUserFavoriteDao = db.userFavoriteDao()
    }

    fun getAllUserFavorite(): LiveData<List<UserFavorite>> = mUserFavoriteDao.getAllUserFavorite()

    fun getUserFavoriteById(login: String): LiveData<List<UserFavorite>> = mUserFavoriteDao.getUserFavoriteById(login)

    fun insert(userFavorite: UserFavorite) {
        executorService.execute { mUserFavoriteDao.insert(userFavorite) }
    }

    fun delete(userFavorite: UserFavorite) {
        executorService.execute { mUserFavoriteDao.delete(userFavorite) }
    }
}
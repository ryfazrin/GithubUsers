package com.ryfazrin.githubusers.ui.favorites

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ryfazrin.githubusers.database.UserFavorite
import com.ryfazrin.githubusers.repository.UserFavoriteRepository

class FavoritesViewModel(application: Application) : ViewModel() {
    private val mUserFavoriteRepository: UserFavoriteRepository = UserFavoriteRepository(application)

    fun getAllUserFavorite(): LiveData<List<UserFavorite>> =mUserFavoriteRepository.getAllUserFavorite()
}
package com.ryfazrin.githubusers.helper

import androidx.recyclerview.widget.DiffUtil
import com.ryfazrin.githubusers.database.UserFavorite

class NoteDiffCallback(private val mOldUserFavorite: List<UserFavorite>, private val mNewUserFavorite: List<UserFavorite>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldUserFavorite.size
    }

    override fun getNewListSize(): Int {
        return mNewUserFavorite.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldUserFavorite[oldItemPosition].login == mNewUserFavorite[newItemPosition].login
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldUserFavorite[oldItemPosition]
        val newEmployee = mNewUserFavorite[newItemPosition]
        return oldEmployee.avatar == newEmployee.avatar && oldEmployee.type == newEmployee.type
    }
}
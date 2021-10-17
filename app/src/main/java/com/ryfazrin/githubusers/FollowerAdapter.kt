package com.ryfazrin.githubusers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FollowerAdapter(private val listFollowers: ArrayList<Users>) : RecyclerView.Adapter<FollowerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFollower: TextView = itemView.findViewById(R.id.tv_follower)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row_followers, parent, false))
    }

    override fun onBindViewHolder(holder: FollowerAdapter.ViewHolder, position: Int) {
        val (login, avatar_url, type) = listFollowers[position]
        holder.tvFollower.text = login
        Log.e(TAG, "onBindViewHolder: login")
    }

    override fun getItemCount(): Int = listFollowers.size

    companion object {
        private const val TAG = "Adapter"
    }
}
package com.ryfazrin.githubusers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FollowerAdapter(private val listFollowers: ArrayList<Users>) : RecyclerView.Adapter<FollowerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFollowerPhoto: ImageView = itemView.findViewById(R.id.img_follower_photo)
        val tvFollowerUsername: TextView = itemView.findViewById(R.id.tv_follower_username)
        val tvFollowerType: TextView = itemView.findViewById(R.id.tv_follower_type)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row_followers, parent, false))
    }

    override fun onBindViewHolder(holder: FollowerAdapter.ViewHolder, position: Int) {
        val (login, avatar_url, type) = listFollowers[position]
        Glide.with(holder.itemView.context)
            .load(avatar_url)
            .circleCrop()
            .into(holder.imgFollowerPhoto)
        holder.tvFollowerUsername.text = login
        holder.tvFollowerType.text = type
    }

    override fun getItemCount(): Int = listFollowers.size
}
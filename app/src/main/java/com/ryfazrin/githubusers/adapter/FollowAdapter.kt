package com.ryfazrin.githubusers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ryfazrin.githubusers.R
import com.ryfazrin.githubusers.Users

class FollowAdapter(private val listFollowers: ArrayList<Users>) : RecyclerView.Adapter<FollowAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFollowPhoto: ImageView = itemView.findViewById(R.id.img_follow_photo)
        val tvFollowUsername: TextView = itemView.findViewById(R.id.tv_follow_username)
        val tvFollowType: TextView = itemView.findViewById(R.id.tv_follow_type)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row_follow, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (login, avatar_url, type) = listFollowers[position]
        Glide.with(holder.itemView.context)
            .load(avatar_url)
            .circleCrop()
            .into(holder.imgFollowPhoto)
        holder.tvFollowUsername.text = login
        holder.tvFollowType.text = type
    }

    override fun getItemCount(): Int = listFollowers.size
}
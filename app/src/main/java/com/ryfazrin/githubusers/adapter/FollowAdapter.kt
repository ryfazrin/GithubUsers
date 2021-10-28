package com.ryfazrin.githubusers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ryfazrin.githubusers.Users
import com.ryfazrin.githubusers.databinding.ItemRowFollowBinding

class FollowAdapter(private val listFollow: ArrayList<Users>) : RecyclerView.Adapter<FollowAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemRowFollowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(users: Users) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(users.avatarUrl)
                    .circleCrop()
                    .into(imgFollowPhoto)
                tvFollowUsername.text = users.login
                tvFollowType.text = users.type
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowFollowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listFollow[position])
    }

    override fun getItemCount(): Int = listFollow.size
}
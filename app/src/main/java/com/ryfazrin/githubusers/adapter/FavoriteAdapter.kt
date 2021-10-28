package com.ryfazrin.githubusers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ryfazrin.githubusers.database.UserFavorite
import com.ryfazrin.githubusers.databinding.ItemRowUserBinding

class FavoriteAdapter(private val listUser: ArrayList<UserFavorite>) : RecyclerView.Adapter<FavoriteAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemCLickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemCLickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(private val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(users: UserFavorite) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(users.avatar)
                    .circleCrop()
                    .into(imgItemPhoto)
                tvItemUsername.text = users.login
                tvItemType.text = users.type
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUser[position])

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listUser[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listUser.size

    interface OnItemCLickCallback {
        fun onItemClicked(data: UserFavorite)
    }
}
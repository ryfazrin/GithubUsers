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

class ListUserAdapter(private val listUser: ArrayList<Users>) : RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemCLickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemCLickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvUsername: TextView = itemView.findViewById(R.id.tv_item_username)
        var tvType: TextView = itemView.findViewById(R.id.tv_item_type)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_user, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (login, avatar_url, type) = listUser[position]

        Glide.with(holder.itemView.context)
            .load(avatar_url)
            .circleCrop()
            .into(holder.imgPhoto)
        holder.tvUsername.text = login
        holder.tvType.text = type

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listUser[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listUser.size

    interface OnItemCLickCallback {
        fun onItemClicked(data: Users)
    }
}
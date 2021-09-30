package com.ryfazrin.githubusers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val imgUser: ImageView = findViewById(R.id.img_detail_user)
        val tvName: TextView = findViewById(R.id.tv_detail_name)
        val tvFollowers: TextView = findViewById(R.id.tv_detail_followers)
        val tvFollowing: TextView = findViewById(R.id.tv_detail_following)

        // data User
        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User

        supportActionBar?.title = user.username

//        val text = "Name : ${user.name},\nRepository : ${user.repository}"
        imgUser.setImageResource(user.avatar)
        tvName.text = user.name
        tvFollowers.text = user.followers
        tvFollowing.text = user.following
    }
}
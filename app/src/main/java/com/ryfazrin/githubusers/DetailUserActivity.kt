package com.ryfazrin.githubusers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

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
        val tvLocation: TextView = findViewById(R.id.tv_detail_location)
        val tvCompany: TextView = findViewById(R.id.tv_detail_company)
        val tvRepository: TextView = findViewById(R.id.tv_detail_repository)

        // data User
        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User

        supportActionBar?.title = user.username


        Glide.with(this)
            .load(user.avatar)
            .into(imgUser)
        tvName.text = user.name
        tvFollowers.text = user.followers
        tvFollowing.text = user.following
        tvLocation.text = user.location
        tvCompany.text = user.company
        tvRepository.text = user.repository
    }
}
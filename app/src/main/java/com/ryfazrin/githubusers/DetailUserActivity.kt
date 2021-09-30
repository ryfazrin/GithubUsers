package com.ryfazrin.githubusers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val tvObject: TextView = findViewById(R.id.text_example)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
        val text = "Name : ${user.name.toString()},\nRepository : ${user.repository.toString()}"
        tvObject.text = text
    }
}
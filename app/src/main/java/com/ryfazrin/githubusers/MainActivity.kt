package com.ryfazrin.githubusers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvUser: RecyclerView
    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUser = findViewById(R.id.rv_user)
        rvUser.setHasFixedSize(true)

        list.addAll(listUser)
        showRecyclerList()
    }

    private val listUser: ArrayList<User>
        get() {
            val dataName = resources.getStringArray(R.array.name)
            val dataUsername = resources.getStringArray(R.array.username)
            val dataLocation = resources.getStringArray(R.array.location)
            val dataAvatar = resources.obtainTypedArray(R.array.avatar)
            val dataRepository = resources.getStringArray(R.array.repository)
            val dataCompany = resources.getStringArray(R.array.company)
            val dataFollowers = resources.getStringArray(R.array.followers)
            val dataFollowing = resources.getStringArray(R.array.following)

            val listUser = ArrayList<User>()
            for (i in dataName.indices) {
                val user = User(
                    name = dataName[i],
                    username = dataUsername[i],
                    location = dataLocation[i],
                    avatar = dataAvatar.getResourceId(i, -1),
                    repository = dataRepository[i],
                    company = dataCompany[i],
                    followers = dataFollowers[i],
                    following = dataFollowing[i]
                )
                listUser.add(user)
            }
            return listUser
        }

    private fun showRecyclerList() {
        rvUser.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUserAdapter(list)
        rvUser.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemCLickCallback {
            override fun onItemClicked(data: User) {
                showSelectedUser(data)
            }
        })
    }

    private fun showSelectedUser(user: User) {
        val moveDetailIntent = Intent(this@MainActivity, DetailUserActivity::class.java)
        moveDetailIntent.putExtra(DetailUserActivity.EXTRA_USER, user)
        startActivity(moveDetailIntent)
    }
}
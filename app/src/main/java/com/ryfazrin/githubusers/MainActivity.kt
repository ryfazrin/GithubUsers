package com.ryfazrin.githubusers

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
            val listUser = ArrayList<User>()
            for (i in dataName.indices) {
                val user = User(dataName[i], dataUsername[i], dataLocation[i], dataAvatar.getResourceId(i, -1))
                listUser.add(user)
            }
            return listUser
        }

    private fun showRecyclerList(){
        rvUser.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUserAdapter(list)
        rvUser.adapter = listUserAdapter
    }
}
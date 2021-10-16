package com.ryfazrin.githubusers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryfazrin.githubusers.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList as ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecoration)
        showRecyclerList()
    }

//    private val listUser: ArrayList<User>
//        get() {
//            val dataName = resources.getStringArray(R.array.name)
//            val dataUsername = resources.getStringArray(R.array.username)
//            val dataLocation = resources.getStringArray(R.array.location)
//            val dataAvatar = resources.obtainTypedArray(R.array.avatar)
//            val dataRepository = resources.getStringArray(R.array.repository)
//            val dataCompany = resources.getStringArray(R.array.company)
//            val dataFollowers = resources.getStringArray(R.array.followers)
//            val dataFollowing = resources.getStringArray(R.array.following)
//
//            val listUser = ArrayList<User>()
//            for (i in dataName.indices) {
//                val user = User(
//                    name = dataName[i],
//                    username = dataUsername[i],
//                    location = dataLocation[i],
//                    avatar = dataAvatar.getResourceId(i, -1),
//                    repository = dataRepository[i],
//                    company = dataCompany[i],
//                    followers = dataFollowers[i],
//                    following = dataFollowing[i]
//                )
//                listUser.add(user)
//            }
//            return listUser
//        }

    private fun showRecyclerList() {
//        val listUserAdapter = ListUserAdapter(list)
        showLoading(true)
        val client = ApiConfig.getApiService().getUser()
        client.enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setUserData(responseBody)
                    }
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    private fun setUserData(users: List<Users>) {
        val listUser = ArrayList<Users>()
        for (user in users) {
            listUser.add(user)
//            Log.d(TAG, "setUserData: $user")
        }

        val adapter = ListUserAdapter(listUser)
        binding.rvUser.adapter = adapter

        adapter.setOnItemClickCallback(object : ListUserAdapter.OnItemCLickCallback {
            override fun onItemClicked(data: Users) {
                showSelectedUser(data)
            }
        })
    }

    private fun showSelectedUser(user: Users) {
        val moveDetailIntent = Intent(this@MainActivity, DetailUserActivity::class.java)
        moveDetailIntent.putExtra(DetailUserActivity.EXTRA_USER, user)
        startActivity(moveDetailIntent)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
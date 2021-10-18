package com.ryfazrin.githubusers

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryfazrin.githubusers.API.ApiConfig
import com.ryfazrin.githubusers.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        mainViewModel.users.observe(this, { user ->
            setUserData(user)
        })

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecoration)

        mainViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        mainViewModel.isMessage.observe(this, {
            showError(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            /*
             Gunakan method ini ketika search selesai atau OK
             */
            override fun onQueryTextSubmit(query: String): Boolean {
                showSearchUser(query)
                return true
            }

            /*
             Gunakan method ini untuk merespon tiap perubahan huruf pada SearchView
             */
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Check if user triggered a refresh:
            R.id.refresh -> {
                mainViewModel.users.observe(this, { user ->
                    setUserData(user)
                })
                return true
            }
        }
        // User didn't trigger a refresh, let the superclass handle this action
        return super.onOptionsItemSelected(item)
    }

    private fun showSearchUser(query: String) {
//        binding.rvUser.adapter.clear
//        Log.e(TAG, "adapter: ${binding.rvUser.adapter}")
        showLoading(true)
        val client = ApiConfig.getApiService().getSearchUser(query)
//        Log.e(TAG, "onResponse: $client")
        client.enqueue(object : Callback<UsersSearch> {
            override fun onResponse(call: Call<UsersSearch>, response: Response<UsersSearch>) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        showError(false)
                        setUserData(responseBody.items)
//                        Log.e(TAG, "onResponse: ${responseBody.items}")
                    }
                }
            }

            override fun onFailure(call: Call<UsersSearch>, t: Throwable) {
                showLoading(false)
                showError(true)
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

//    private fun setUserSearch(users: List<Users>) {
//        val listUser = ArrayList<String>()
//        for (user in users) {
//            listUser.add(user.login)
//        }
//
//        val adapter = ListUserSearchAdapter(listUser)
//        binding.rvUser.adapter = adapter
//
////        adapter.setOnItemClickCallback(object : ListUserSearchAdapter.OnItemCLickCallback {
////            override fun onItemClicked(data: Users) {
////                showSelectedUser(data)
////            }
////        })
//    }

    // showFirstListUsers() { }

    private fun setUserData(users: List<Users>) {
        var listUser = ArrayList<Users>()
        listUser.clear()
        for (user in users) {
            listUser.add(user)
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
        // moveDetailIntent.putExtra(DetailUserActivity.EXTRA_USER, user)
        moveDetailIntent.putExtra(DetailUserActivity.EXTRA_USER, user.login)
        startActivity(moveDetailIntent)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun showError(isMessage: Boolean) {
        if (isMessage) {
            binding.errorMessage.visibility = View.VISIBLE
        } else {
            binding.errorMessage.visibility = View.GONE
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
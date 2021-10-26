package com.ryfazrin.githubusers

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryfazrin.githubusers.adapter.ListUserAdapter
import com.ryfazrin.githubusers.databinding.ActivityMainBinding
import com.ryfazrin.githubusers.ui.detailuser.DetailUserActivity
import com.ryfazrin.githubusers.ui.main.MainViewModel
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java)
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

            override fun onQueryTextSubmit(query: String): Boolean {
                mainViewModel.showSearchUser(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> {
                mainViewModel.showFirstListUsers()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setUserData(users: List<Users>) {
        val listUser = ArrayList<Users>()
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
        moveDetailIntent.putExtra(DetailUserActivity.EXTRA_USER, user.login)

        startActivity(moveDetailIntent)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showError(isMessage: Boolean) {
        binding.errorMessage.visibility = if (isMessage) View.VISIBLE else View.GONE
    }
}
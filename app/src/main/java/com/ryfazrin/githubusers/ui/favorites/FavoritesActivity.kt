package com.ryfazrin.githubusers.ui.favorites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryfazrin.githubusers.R
import com.ryfazrin.githubusers.Users
import com.ryfazrin.githubusers.adapter.FavoriteAdapter
import com.ryfazrin.githubusers.adapter.ListUserAdapter
import com.ryfazrin.githubusers.database.UserFavorite
import com.ryfazrin.githubusers.databinding.ActivityFavoritesBinding
import com.ryfazrin.githubusers.helper.ViewModelFactory
import com.ryfazrin.githubusers.ui.detailuser.DetailUserActivity

class FavoritesActivity : AppCompatActivity() {

    private lateinit var favoriteViewModel: FavoritesViewModel
    private lateinit var binding: ActivityFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoriteViewModel =
            ViewModelProvider(this, ViewModelFactory.getInstance(this.application))
                .get(FavoritesViewModel::class.java)

        supportActionBar?.title = getString(R.string.favorite_users)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val layoutManager = LinearLayoutManager(this)
        binding.rvFavorites.layoutManager = layoutManager

        favoriteViewModel.getAllUserFavorite().observe(this, { userFavoriteList ->
            setFavoriteData(userFavoriteList)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setFavoriteData(users: List<UserFavorite>) {
        val listUser = ArrayList<UserFavorite>()
        listUser.clear()

        listUser.addAll(users)

        val adapter = FavoriteAdapter(listUser)
        binding.rvFavorites.adapter = adapter

        adapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemCLickCallback {
            override fun onItemClicked(data: UserFavorite) {
                showSelectedUser(data)
            }
        })
    }

    private fun showSelectedUser(user: UserFavorite) {
        val moveDetailIntent = Intent(this@FavoritesActivity, DetailUserActivity::class.java)
        moveDetailIntent.putExtra(DetailUserActivity.EXTRA_USER, user.login)

        startActivity(moveDetailIntent)
    }
}
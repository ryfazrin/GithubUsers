package com.ryfazrin.githubusers.ui.detailuser

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ryfazrin.githubusers.R
import com.ryfazrin.githubusers.adapter.SectionDetailUserPagerAdapter
import com.ryfazrin.githubusers.UserDetailResponse
import com.ryfazrin.githubusers.database.UserFavorite
import com.ryfazrin.githubusers.databinding.ActivityDetailUserBinding
import com.ryfazrin.githubusers.helper.ViewModelFactory
import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10

class DetailUserActivity : AppCompatActivity() {

    private lateinit var detailUserViewModel: DetailUserViewModel
    private lateinit var binding: ActivityDetailUserBinding
    private var userFavorite: UserFavorite = UserFavorite()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailUserViewModel =
            ViewModelProvider(this, ViewModelFactory.getInstance(this.application))
                .get(DetailUserViewModel::class.java)

        val getUser: String = intent.getStringExtra(EXTRA_USER).toString()

        val sectionPagerAdapter = SectionDetailUserPagerAdapter(this@DetailUserActivity, getUser)

        val viewPager: ViewPager2 = binding.vpUserFollow
        viewPager.adapter = sectionPagerAdapter

        val tabs: TabLayout = binding.tlUserFollow
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.title = getUser
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailUserViewModel.findUser(getUser)

        detailUserViewModel.user.observe(this, {
            setUserData(it)
        })

        detailUserViewModel.isLoading.observe(this, {
            showLoading(it)
        })
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setUserData(user: UserDetailResponse) {

        Glide.with(this)
            .load(user.avatarUrl)
            .into(binding.imgDetailUser)
        binding.tvDetailName.text = user.name
        binding.tvDetailFollowers.text = countViews(user.followers.toLong())
        binding.tvDetailFollowing.text = countViews(user.following.toLong())
        binding.tvDetailLocation.text = user.location
        binding.tvDetailCompany.text = user.company
        binding.tvDetailRepository.text = countViews(user.publicRepos.toLong())

        detailUserViewModel.getUserFavoriteById(user.login).observe(this, {
            if (it.isNotEmpty()) {
                with(binding.fabAdd) {
                    setImageDrawable(getDrawable(R.drawable.ic_baseline_favorite_24))
                    setColorFilter(Color.RED)
                    backgroundTintList = ColorStateList.valueOf(Color.WHITE)

                    setOnClickListener {
                        userFavorite.login = user.login

                        detailUserViewModel.deleteFavorite(userFavorite)

                        Toast.makeText(this@DetailUserActivity, "${user.login} dihapus dari favorite", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                with(binding.fabAdd) {
                    setImageDrawable(getDrawable(R.drawable.ic_baseline_favorite_border_24))
                    setColorFilter(Color.WHITE)
                    backgroundTintList = ColorStateList.valueOf(Color.RED)
                    setOnClickListener {
                        userFavorite.login = user.login
                        userFavorite.avatar = user.avatarUrl
                        userFavorite.type = user.type

                        detailUserViewModel.insertFavorite(userFavorite)

                        Toast.makeText(this@DetailUserActivity, "Berhasil ditambahkan ke favorite", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.share -> {
                detailUserViewModel.user.observe(this, { user ->
                    val sendData = "Github User's\n\nName: ${user.name}\n\nUsername: ${user.login}\n\nCompany: ${user.company}"

                    val intent = Intent()
                    intent.action = Intent.ACTION_SEND
                    intent.putExtra(Intent.EXTRA_TEXT, sendData)
                    intent.type="text/plain"
                    startActivity(Intent.createChooser(intent,"Share ${user.login} Github Profile :"))
                })
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun countViews(count: Long): String{
        val array = arrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
        val value = floor(log10(count.toDouble())).toInt()
        val base = value / 3
        return if (value >= 3 && base < array.size) {
            DecimalFormat("#0.0").format(count/ Math.pow(10.0, (base * 3).toDouble())) + array[base]
        } else {
            DecimalFormat("#,##0").format(count)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val EXTRA_USER = "extra_user"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }
}
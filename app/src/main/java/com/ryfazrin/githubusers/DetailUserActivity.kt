package com.ryfazrin.githubusers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ryfazrin.githubusers.API.ApiConfig
import com.ryfazrin.githubusers.databinding.ActivityDetailUserBinding
import retrofit2.Call
import java.text.DecimalFormat
import retrofit2.Callback
import retrofit2.Response

class DetailUserActivity : AppCompatActivity() {

    private lateinit var user: Users
    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val imgUser: ImageView = findViewById(R.id.img_detail_user)
//        val tvName: TextView = findViewById(R.id.tv_detail_name)
//        val tvFollowers: TextView = findViewById(R.id.tv_detail_followers)
//        val tvFollowing: TextView = findViewById(R.id.tv_detail_following)
//        val tvLocation: TextView = findViewById(R.id.tv_detail_location)
//        val tvCompany: TextView = findViewById(R.id.tv_detail_company)
//        val tvRepository: TextView = findViewById(R.id.tv_detail_repository)

        user = intent.getParcelableExtra<Users>(EXTRA_USER) as Users

        Log.e(TAG, "onResponse Detail: ${user.login}")
        Log.e(TAG, "onResponse Detail: ${user}")

        val sectionPagerAdapter = SectionDetailUserPagerAdapter(this@DetailUserActivity)
        val viewPager: ViewPager2 = binding.vpUserFollow
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = binding.tlUserFollow
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.title = user.login
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        tvName.text = user.name
//        tvFollowers.text = countViews(user.followers.toLong())
//        tvFollowing.text = countViews(user.following.toLong())
//        tvLocation.text = user.location
//        tvCompany.text = user.company
//        tvRepository.text = user.repository

        findUser()
    }

    private fun findUser() {
        showLoading(true)
        val client = ApiConfig.getApiService().getDetailUser(user.login)
        client.enqueue(object : Callback<UserDetailResponse> {
            override fun onResponse(
                call: Call<UserDetailResponse>,
                response: Response<UserDetailResponse>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setUserData(responseBody)
                    }
                }
            }

            override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

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
//                val sendData = "Github User's\n\nName: ${user.name}\n\nUsername: ${user.username}\n\nCompany: ${user.company}"

//                val intent = Intent()
//                intent.action = Intent.ACTION_SEND
//                intent.putExtra(Intent.EXTRA_TEXT, sendData)
//                intent.type="text/plain"
//                startActivity(Intent.createChooser(intent,"Share ${user.username} Github Profile :"))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun countViews(count: Long): String{
        val array = arrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
        val value = Math.floor(Math.log10(count.toDouble())).toInt()
        val base = value / 3
        return if (value >= 3 && base < array.size) {
            DecimalFormat("#0.0").format(count/ Math.pow(10.0, (base * 3).toDouble())) + array[base]
        } else {
            DecimalFormat("#,##0").format(count)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        private const val TAG = "DetailUserActivity"
        const val EXTRA_USER = "extra_user"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }
}
package com.ryfazrin.githubusers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import java.text.DecimalFormat

class DetailUserActivity : AppCompatActivity() {

    private lateinit var user: User

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

        user = intent.getParcelableExtra<User>(EXTRA_USER) as User

        //set back button
        supportActionBar?.title = user.username
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Glide.with(this)
            .load(user.avatar)
            .into(imgUser)
        tvName.text = user.name
        tvFollowers.text = countViews(user.followers.toLong())
        tvFollowing.text = countViews(user.following.toLong())
        tvLocation.text = user.location
        tvCompany.text = user.company
        tvRepository.text = user.repository
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
                val sendData = "Github User's\n\nNama: ${user.username}\n\nUsername: ${user.username}\n\nCompany: ${user.company}"

                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, sendData)
                intent.type="text/plain"
                startActivity(Intent.createChooser(intent,"Share ${user.username} Github Profile :"))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun countViews(count:Long): String{
        val array = arrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
        val value = Math.floor(Math.log10(count.toDouble())).toInt()
        val base = value / 3
        if (value >= 3 && base < array.size) {
            return DecimalFormat("#0.0").format(count/ Math.pow(10.0, (base * 3).toDouble())) + array[base]
        } else {
            return DecimalFormat("#,##0").format(count)
        }
    }
}
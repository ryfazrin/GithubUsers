package com.ryfazrin.githubusers.ui.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import com.ryfazrin.githubusers.R
import com.ryfazrin.githubusers.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.switchTheme.setOnCheckedChangeListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCheckedChanged(compoundButton: CompoundButton?, isChecked: Boolean) {
        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            binding.switchTheme.isChecked = true
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            binding.switchTheme.isChecked = false
        }
    }
}
package com.ryfazrin.githubusers

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionDetailUserPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        return FollowersFragment.newInstance(position + 1)
    }

    override fun getItemCount(): Int = 2
}
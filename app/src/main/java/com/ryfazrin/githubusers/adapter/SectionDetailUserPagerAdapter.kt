package com.ryfazrin.githubusers.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ryfazrin.githubusers.ui.followers.FollowersFragment
import com.ryfazrin.githubusers.ui.following.FollowingFragment

class SectionDetailUserPagerAdapter(activity: AppCompatActivity, private val getUserData: String)
    : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragment.newInstance(position + 1, getUserData)
            1 -> fragment = FollowingFragment.newInstance(position + 1, getUserData)
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int = 2
}
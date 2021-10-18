package com.ryfazrin.githubusers.ui.followers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryfazrin.githubusers.adapter.FollowAdapter
import com.ryfazrin.githubusers.Users
import com.ryfazrin.githubusers.databinding.FragmentFollowersBinding

class FollowersFragment : Fragment() {

    private lateinit var binding: FragmentFollowersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_followers, container, false)
        binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val followersViewModel = ViewModelProvider(this).get(FollowersViewModel::class.java)

        val getUser: String = requireArguments().getString(EXTRA_USER).toString()

        followersViewModel.showListFollowers(getUser)
        followersViewModel.users.observe(viewLifecycleOwner, { user ->
            setFollowerData(user)
        })

        followersViewModel.isLoading.observe(viewLifecycleOwner, {
            showLoading(it)
        })
    }

    // showListFollowers()

    private fun setFollowerData(users: List<Users>) {
        val listFollower = ArrayList<Users>()
        for (user in users) {
            listFollower.add(user)
        }

        binding.rvFollowers.apply {
            layoutManager = LinearLayoutManager(requireContext()) // activity or requireContext()
            adapter = FollowAdapter(listFollower)
//            adapter = ListUserAdapter(listFollower)
        }
//        val adapter = FollowerAdapter(listFollower)
//        binding.rvFollowers.adapter = adapter
//        binding.rvFollowers.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        const val EXTRA_USER = "extra_user"
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(index: Int, string: String) =
            FollowersFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, index)
                    putString(EXTRA_USER, string)
                }
            }
    }
}
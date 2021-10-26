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

    private fun setFollowerData(users: List<Users>) {
        val listFollower = ArrayList<Users>()

        listFollower.addAll(users)

        binding.rvFollowers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = FollowAdapter(listFollower)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
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
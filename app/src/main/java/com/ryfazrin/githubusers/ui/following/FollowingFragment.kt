package com.ryfazrin.githubusers.ui.following

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryfazrin.githubusers.adapter.FollowAdapter
import com.ryfazrin.githubusers.Users
import com.ryfazrin.githubusers.databinding.FragmentFollowingBinding

class FollowingFragment : Fragment() {

    private lateinit var binding: FragmentFollowingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val followingViewModel = ViewModelProvider(this).get(FollowingViewModel::class.java)

        val getUser: String = requireArguments().getString(EXTRA_USER).toString()

        followingViewModel.showListFollowing(getUser)
        followingViewModel.users.observe(viewLifecycleOwner, { user ->
            setFollowingData(user)
        })

        followingViewModel.isLoading.observe(viewLifecycleOwner, {
            showLoading(it)
        })
    }

    private fun setFollowingData(users: List<Users>) {
        val listFollowing = ArrayList<Users>()

        listFollowing.addAll(users)

        binding.rvFollowing.apply {
            layoutManager = LinearLayoutManager(requireContext()) // activity or requireContext()
            adapter = FollowAdapter(listFollowing)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        private const val EXTRA_USER = "extra_user"
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(index: Int, string: String) =
            FollowingFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, index)
                    putString(EXTRA_USER, string)
                }
            }
    }
}
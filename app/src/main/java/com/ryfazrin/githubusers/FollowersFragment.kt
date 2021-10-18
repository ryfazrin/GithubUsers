package com.ryfazrin.githubusers

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryfazrin.githubusers.API.ApiConfig
import com.ryfazrin.githubusers.databinding.FragmentFollowersBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
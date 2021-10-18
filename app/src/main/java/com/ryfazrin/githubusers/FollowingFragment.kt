package com.ryfazrin.githubusers

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryfazrin.githubusers.API.ApiConfig
import com.ryfazrin.githubusers.databinding.FragmentFollowingBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingFragment : Fragment() {

    private lateinit var binding: FragmentFollowingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showRecyclerFollowing()
    }

    private fun showRecyclerFollowing() {

        val data = requireArguments().getString(FollowingFragment.EXTRA_USER).toString()
        val client = ApiConfig.getApiService().getDetailFollowing(data)
        client.enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setFollowingData(responseBody)
                    }
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun setFollowingData(users: List<Users>) {
        val listFollowing = ArrayList<Users>()
        for (user in users) {
            listFollowing.add(user)
        }

        binding.rvFollowing.apply {
            layoutManager = LinearLayoutManager(requireContext()) // activity or requireContext()
            adapter = FollowAdapter(listFollowing)
        }
//        val adapter = FollowerAdapter(listFollower)
//        binding.rvFollowers.adapter = adapter
//        binding.rvFollowers.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
        const val EXTRA_USER = "extra_user"
        private const val TAG = "FollowingFragment"
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
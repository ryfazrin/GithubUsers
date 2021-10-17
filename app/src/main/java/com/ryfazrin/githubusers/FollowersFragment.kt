package com.ryfazrin.githubusers

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryfazrin.githubusers.API.ApiConfig
import com.ryfazrin.githubusers.databinding.FragmentFollowersBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.zip.Inflater

class FollowersFragment : Fragment() {

    private lateinit var binding: FragmentFollowersBinding
    private lateinit var user: List<Users>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        showRecyclerFollowers()
    }

    private fun showRecyclerFollowers() {
        // val client = ApiConfig.getApiService().getDetailFollowers(DetailUserActivity.EXTRA_USER)
        val client = ApiConfig.getApiService().getDetailFollowers("mojombo") // sengaja menggunakan statik username, mencoba menampilkan datanya dahulu
        client.enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        Log.e(TAG, "onResponse: $responseBody")
                        setFollowerData(responseBody)
                    }
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun setFollowerData(users: List<Users>) {
        val listFollower = ArrayList<Users>()
        for (user in users) {
            listFollower.add(user)
        }

        binding.rvFollowers.apply {
            layoutManager = LinearLayoutManager(requireContext()) // activity or requireContext()
//            adapter = FollowerAdapter(listFollower)
            adapter = ListUserAdapter(listFollower)
        }
//        val adapter = FollowerAdapter(listFollower)
//        binding.rvFollowers.adapter = adapter
//        binding.rvFollowers.layoutManager = LinearLayoutManager(requireContext())
    }


    companion object {
        const val EXTRA_USER = "extra_user"
        private const val TAG = "FollowersFragment"
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(index: Int) =
            FollowersFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, index)
                }
            }
    }
}
package com.pramudiaputr.githubapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pramudiaputr.githubapp.adapter.FollowPagerAdapter
import com.pramudiaputr.githubapp.adapter.FollowingAdapter
import com.pramudiaputr.githubapp.databinding.FragmentFollowingBinding
import com.pramudiaputr.githubapp.model.ListUserResponse
import com.pramudiaputr.githubapp.viewmodel.FollowingViewModel

class FollowingFragment : Fragment() {

    private lateinit var binding: FragmentFollowingBinding
    private lateinit var followingAdapter: FollowingAdapter

    private val followingViewMode: FollowingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val username = arguments?.getString(FollowPagerAdapter.USERNAME)
            followingViewMode.getFollowing(username!!)
        }

        followingViewMode.listFollowing.observe(viewLifecycleOwner, { listFollowing ->
            showFollowing(listFollowing)
        })

        followingViewMode.isLoading.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
            }
        })
    }

    private fun showFollowing(list: List<ListUserResponse>) {
        followingAdapter = FollowingAdapter(list)

        with(binding.recyclerViewFollowing) {
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            adapter = followingAdapter
        }
    }
}
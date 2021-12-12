package com.pramudiaputr.githubapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pramudiaputr.githubapp.adapter.FollowAdapter
import com.pramudiaputr.githubapp.adapter.FollowPagerAdapter
import com.pramudiaputr.githubapp.databinding.FragmentFollowingBinding
import com.pramudiaputr.githubapp.model.ListUserResponse
import com.pramudiaputr.githubapp.viewmodel.FollowingViewModel

class FollowingFragment : Fragment() {

    private val followingViewMode: FollowingViewModel by viewModels()
    private lateinit var followingAdapter: FollowAdapter
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(layoutInflater)
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

        followingViewMode.countList.observe(viewLifecycleOwner, { count ->
            if (count == 0) {
                binding.tvEmptyList.visibility = View.VISIBLE
            } else {
                binding.tvEmptyList.visibility = View.GONE
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showFollowing(list: List<ListUserResponse>) {
        followingAdapter = FollowAdapter(list)

        with(binding.recyclerViewFollowing) {
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            adapter = followingAdapter
        }
    }
}
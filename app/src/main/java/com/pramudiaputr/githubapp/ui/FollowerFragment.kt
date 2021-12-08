package com.pramudiaputr.githubapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pramudiaputr.githubapp.adapter.FollowPagerAdapter.Companion.USERNAME
import com.pramudiaputr.githubapp.adapter.FollowerAdapter
import com.pramudiaputr.githubapp.databinding.FragmentFollowerBinding
import com.pramudiaputr.githubapp.model.ListUserResponse
import com.pramudiaputr.githubapp.viewmodel.FollowerViewModel

class FollowerFragment : Fragment() {

    private lateinit var binding: FragmentFollowerBinding
    private lateinit var followerAdapter: FollowerAdapter

    private val followerViewModel: FollowerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val username = arguments?.getString(USERNAME)
            followerViewModel.getFollower(username!!)
        }

        followerViewModel.listFollower.observe(viewLifecycleOwner, { listFollower ->
            showFollowers(listFollower)
        })

        followerViewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
            }
        })

        followerViewModel.countList.observe(viewLifecycleOwner, { count ->
            if (count == 0) {
                binding.tvEmptyList.visibility = View.VISIBLE
            } else {
                binding.tvEmptyList.visibility = View.GONE
            }
        })
    }

    private fun showFollowers(list: List<ListUserResponse>) {
        followerAdapter = FollowerAdapter(list)

        with(binding.recyclerViewFollower) {
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            adapter = followerAdapter
        }
    }
}
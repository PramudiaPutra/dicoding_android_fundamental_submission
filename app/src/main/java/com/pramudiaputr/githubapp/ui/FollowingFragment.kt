package com.pramudiaputr.githubapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pramudiaputr.githubapp.adapter.FollowPagerAdapter
import com.pramudiaputr.githubapp.databinding.FragmentFollowingBinding

class FollowingFragment : Fragment() {

    lateinit var binding: FragmentFollowingBinding

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
            with(binding) {
                tvName.text = username
            }
        }
    }
}
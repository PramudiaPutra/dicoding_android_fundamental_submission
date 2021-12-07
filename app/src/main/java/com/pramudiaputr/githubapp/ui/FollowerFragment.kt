package com.pramudiaputr.githubapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pramudiaputr.githubapp.adapter.FollowPagerAdapter.Companion.USERNAME
import com.pramudiaputr.githubapp.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {

    private lateinit var binding: FragmentFollowerBinding

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
            with(binding) {
                tvName.text = username
            }
        }

    }
}
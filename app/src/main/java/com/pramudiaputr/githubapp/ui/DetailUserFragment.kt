package com.pramudiaputr.githubapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.pramudiaputr.githubapp.databinding.FragmentDetailUserBinding
import com.pramudiaputr.githubapp.model.UserDetailResponse
import com.pramudiaputr.githubapp.viewmodel.DetailUserViewModel

class DetailUserFragment : Fragment() {

    private lateinit var binding: FragmentDetailUserBinding

    private val detailViewModel: DetailUserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailUserBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = DetailUserFragmentArgs.fromBundle(requireArguments())
        detailViewModel.getUserDetail(args.githubuser.login)
        detailViewModel.userDetail.observe(viewLifecycleOwner, { displayUserDetail(it) })
        detailViewModel.isLoading.observe(viewLifecycleOwner, {isLoading->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
            }
        })


    }

    private fun displayUserDetail(it: UserDetailResponse) {
        with(binding) {
            Glide.with(imgUserProfile).load(it.avatarUrl).into(imgUserProfile)
            tvRepo.text = it.publicRepos.toString()
            tvFollower.text = it.followers.toString()
            tvFollowing.text = it.following.toString()
            tvUserName.text = it.login
            tvName.text = it.name
            tvCompany.text = it.company
            tvLocation.text = it.location
        }
    }
}
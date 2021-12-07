package com.pramudiaputr.githubapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pramudiaputr.githubapp.databinding.FragmentDetailUserBinding

class DetailUserFragment : Fragment() {

    private lateinit var binding: FragmentDetailUserBinding

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
        with(binding) {
//            imgUserProfile.setImageResource(args.githubuser.avatar)
//            tvRepo.text = args.githubuser.repository
//            tvFollower.text = args.githubuser.follower
//            tvFollowing.text = args.githubuser.following
            tvUserName.text = args.githubuser.login
//            tvName.text = args.githubuser.name
//            tvCompany.text = args.githubuser.company
//            tvLocation.text = args.githubuser.location
        }
    }
}
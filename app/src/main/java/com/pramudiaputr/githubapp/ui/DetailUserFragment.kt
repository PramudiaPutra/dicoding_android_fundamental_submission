package com.pramudiaputr.githubapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.pramudiaputr.githubapp.R
import com.pramudiaputr.githubapp.adapter.FollowPagerAdapter
import com.pramudiaputr.githubapp.databinding.FragmentDetailUserBinding
import com.pramudiaputr.githubapp.model.UserDetailResponse
import com.pramudiaputr.githubapp.viewmodel.DetailUserViewModel

class DetailUserFragment : Fragment() {

    private lateinit var binding: FragmentDetailUserBinding

    private val detailViewModel: DetailUserViewModel by viewModels()

    companion object {
        private val TAB_TITLES = intArrayOf(
            R.string.tab_follower,
            R.string.tab_following,
        )
    }

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
        createPager(args.githubuser.login)
        detailViewModel.userDetail.observe(viewLifecycleOwner, { displayUserDetail(it) })
        detailViewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
            }
        })
    }

    private fun createPager(login: String) {
        val fragmentList = arrayListOf(
            FollowerFragment(),
            FollowingFragment(),
        )

        val followPagerAdapter = FollowPagerAdapter(
            login,
            fragmentList,
            childFragmentManager,
            lifecycle
        )

        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = followPagerAdapter

        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
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
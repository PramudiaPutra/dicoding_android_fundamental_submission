package com.pramudiaputr.githubapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.pramudiaputr.githubapp.R
import com.pramudiaputr.githubapp.ui.adapter.FollowPagerAdapter
import com.pramudiaputr.githubapp.databinding.FragmentDetailUserBinding
import com.pramudiaputr.githubapp.model.UserDetailResponse
import com.pramudiaputr.githubapp.ui.viewmodel.detailuser.DetailViewModel
import com.pramudiaputr.githubapp.ui.viewmodel.detailuser.DetailViewModelFactory

class DetailUserFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel
    private var _binding: FragmentDetailUserBinding? = null
    private val binding get() = _binding!!
    private var user: UserDetailResponse? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailUserBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = DetailUserFragmentArgs.fromBundle(requireArguments())

        detailViewModel = obtainViewModel(activity as AppCompatActivity)
        detailViewModel.getUserDetail(args.githubuser.login)

        createPager(args.githubuser.login)
        detailViewModel.userDetail.observe(viewLifecycleOwner, {
            displayUserDetail(it)
            user = it
        })

        detailViewModel.getIsFavorite(args.githubuser.login).observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                binding.addFavorite?.visibility = View.GONE
                binding.removeFavorite?.visibility = View.VISIBLE
            } else {
                binding.addFavorite?.visibility = View.VISIBLE
                binding.removeFavorite?.visibility = View.GONE
            }
        })

        detailViewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
                binding.tvCompany.visibility = View.GONE
                binding.tvLocation.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
                binding.tvCompany.visibility = View.VISIBLE
                binding.tvLocation.visibility = View.VISIBLE
            }
        })

        binding.addFavorite?.setOnClickListener {
            user.let { user ->
                user?.login = args.githubuser.login
                user?.isFavorite = true
            }

            detailViewModel.addFavorite(user as UserDetailResponse)
            Log.d("USER_TAG", "isert: $user")
        }

        binding.removeFavorite?.setOnClickListener {
            detailViewModel.removeFavorite(user as UserDetailResponse)
            Log.d("USER_TAG", "delete: $user")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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
            tvCompany.text = it.company ?: getString(R.string.no_company)
            tvLocation.text = it.location ?: getString(R.string.no_location)
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
        val factory = DetailViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[DetailViewModel::class.java]
    }

    companion object {
        private val TAB_TITLES = intArrayOf(
            R.string.tab_follower,
            R.string.tab_following,
        )
    }
}
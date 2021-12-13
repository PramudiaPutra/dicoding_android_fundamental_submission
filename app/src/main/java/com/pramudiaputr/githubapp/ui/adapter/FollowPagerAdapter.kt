package com.pramudiaputr.githubapp.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FollowPagerAdapter(
    login: String,
    list: ArrayList<Fragment>,
    manager: FragmentManager,
    lifecycle: Lifecycle
) :
    FragmentStateAdapter(manager, lifecycle) {

    companion object {
        const val USERNAME = "USERNAME"
    }

    private val fragmentList = list
    private val username = login

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        val mBundle = Bundle()
        mBundle.putString(USERNAME, username)
        fragmentList[position].arguments = mBundle
        return fragmentList[position]
    }
}
package com.pramudiaputr.githubapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pramudiaputr.githubapp.R
import com.pramudiaputr.githubapp.adapter.GithubUserAdapter
import com.pramudiaputr.githubapp.databinding.FragmentMainBinding
import com.pramudiaputr.githubapp.model.GithubUserModel

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var githubUserAdapter: GithubUserAdapter
    private val list = ArrayList<GithubUserModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.addAll(listGithubUser)
        showRecyclerList()
    }

    private val listGithubUser: ArrayList<GithubUserModel>
        @SuppressLint("Recycle")
        get() {

            val username = resources.getStringArray(R.array.username)
            val name = resources.getStringArray(R.array.name)
            val avatar = resources.obtainTypedArray(R.array.avatar)
            val company = resources.getStringArray(R.array.company)
            val location = resources.getStringArray(R.array.location)
            val repository = resources.getStringArray(R.array.repository)
            val follower = resources.getStringArray(R.array.followers)
            val following = resources.getStringArray(R.array.following)

            val listUser = ArrayList<GithubUserModel>()
            for (i in username.indices) {
                val data = GithubUserModel(
                    username[i],
                    name[i],
                    avatar.getResourceId(i, -1),
                    company[i],
                    location[i],
                    repository[i],
                    follower[i],
                    following[i],
                )
                listUser.add(data)
            }
            return listUser
        }

    private fun showRecyclerList() {
        githubUserAdapter = GithubUserAdapter(list)

        githubUserAdapter.setOnClickItem(object : GithubUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: GithubUserModel) {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailUserFragment(data))
            }

        })

        with(binding.rvGithub) {
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            adapter = githubUserAdapter
        }
    }
}
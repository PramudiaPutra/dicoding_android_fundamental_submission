package com.pramudiaputr.githubapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.pramudiaputr.githubapp.adapter.GithubUserAdapter
import com.pramudiaputr.githubapp.databinding.ActivityMainBinding
import com.pramudiaputr.githubapp.model.GithubUserModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<GithubUserModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        with(binding.rvGithub) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = GithubUserAdapter(list)
        }
    }
}
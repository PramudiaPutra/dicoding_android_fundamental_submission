package com.pramudiaputr.githubapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pramudiaputr.githubapp.MainActivity.Companion.USER_DATA
import com.pramudiaputr.githubapp.databinding.ActivityDetailUserBinding
import com.pramudiaputr.githubapp.model.GithubUserModel

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.detail_user)

        val intent = intent.getParcelableExtra<GithubUserModel>(USER_DATA)
        if (intent != null) {
            with(binding) {
                imgUserProfile.setImageResource(intent.avatar)
                tvRepo.text = intent.repository
                tvFollower.text = intent.follower
                tvFollowing.text = intent.following
                tvUserName.text = intent.username
                tvName.text = intent.name
                tvCompany.text = intent.company
                tvLocation.text = intent.location
            }
        }
    }
}
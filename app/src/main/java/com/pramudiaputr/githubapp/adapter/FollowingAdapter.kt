package com.pramudiaputr.githubapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pramudiaputr.githubapp.databinding.ItemGithubUserBinding
import com.pramudiaputr.githubapp.model.ListUserResponse

class FollowingAdapter(
    private val listUser: List<ListUserResponse>,
) :
    RecyclerView.Adapter<FollowingAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemGithubUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val data = listUser[position]

        with(holder.binding) {

            Glide.with(imgUserProfile)
                .load(data.avatarUrl)
                .into(imgUserProfile)

            tvUserName.text = data.login
        }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    class ListViewHolder(var binding: ItemGithubUserBinding) : RecyclerView.ViewHolder(binding.root)
}
package com.pramudiaputr.githubapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pramudiaputr.githubapp.databinding.ItemGithubUserBinding
import com.pramudiaputr.githubapp.model.GithubUserModel

class GithubUserAdapter(
    private val listUser: List<GithubUserModel>,
    private val onClick: (GithubUserModel) -> Unit
) :
    RecyclerView.Adapter<GithubUserAdapter.ListViewHolder>() {

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
            imgUserProfile.setImageResource(data.avatar)
            tvUserName.text = data.username
            tvName.text = data.name
        }
        holder.itemView.setOnClickListener {onClick(data)}
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    class ListViewHolder(var binding: ItemGithubUserBinding) : RecyclerView.ViewHolder(binding.root)
}
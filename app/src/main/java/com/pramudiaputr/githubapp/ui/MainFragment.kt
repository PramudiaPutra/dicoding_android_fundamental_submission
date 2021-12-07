package com.pramudiaputr.githubapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pramudiaputr.githubapp.MainViewModel
import com.pramudiaputr.githubapp.adapter.GithubUserAdapter
import com.pramudiaputr.githubapp.databinding.FragmentMainBinding
import com.pramudiaputr.githubapp.model.ListUserResponse

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var githubUserAdapter: GithubUserAdapter
    private val mainViewModel: MainViewModel by viewModels()

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

        mainViewModel.listUser.observe(viewLifecycleOwner, { listUser ->
            showRecyclerList(listUser)
        })

        mainViewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) binding.progressBar.visibility = View.VISIBLE
            else binding.progressBar.visibility = View.GONE

        })
    }

    private fun showRecyclerList(list: List<ListUserResponse>) {
        githubUserAdapter = GithubUserAdapter(list)

        githubUserAdapter.setOnClickItem(object : GithubUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ListUserResponse) {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToDetailUserFragment(data)
                )
            }
        })

        with(binding.rvGithub) {
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            adapter = githubUserAdapter
        }
    }
}
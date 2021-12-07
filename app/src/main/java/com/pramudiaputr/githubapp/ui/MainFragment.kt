package com.pramudiaputr.githubapp.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pramudiaputr.githubapp.R
import com.pramudiaputr.githubapp.viewmodel.MainViewModel
import com.pramudiaputr.githubapp.adapter.GithubUserAdapter
import com.pramudiaputr.githubapp.databinding.FragmentMainBinding
import com.pramudiaputr.githubapp.model.ListUserResponse

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var githubUserAdapter: GithubUserAdapter
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.maxWidth = Integer.MAX_VALUE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(search: String): Boolean {
                mainViewModel.searchUser(search)
                return true
            }

            override fun onQueryTextChange(search: String): Boolean {
                return false
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
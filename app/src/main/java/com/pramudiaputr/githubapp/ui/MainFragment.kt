package com.pramudiaputr.githubapp.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.pramudiaputr.githubapp.R
import com.pramudiaputr.githubapp.ui.viewmodel.MainViewModel
import com.pramudiaputr.githubapp.ui.adapter.GithubUserAdapter
import com.pramudiaputr.githubapp.databinding.FragmentMainBinding
import com.pramudiaputr.githubapp.model.ListUserResponse
import androidx.constraintlayout.widget.ConstraintLayout


class MainFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var githubUserAdapter: GithubUserAdapter
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.queryHint = getString(R.string.search_github_user)

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite -> {
                findNavController().navigate(R.id.action_mainFragment_to_favoriteFragment)
                true
            }
            R.id.setting -> {
                findNavController().navigate(R.id.action_mainFragment_to_settingFragment)
                true
            }
            else -> true
        }
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
        _binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar = view.findViewById(R.id.toolBar) as Toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        mainViewModel.listUser.observe(viewLifecycleOwner, { listUser ->
            showRecyclerList(listUser)
        })

        mainViewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        mainViewModel.searchCount.observe(viewLifecycleOwner, { count ->
            if (count == 0) {
                val mRootView = view.findViewById(R.id.mainFragment) as ConstraintLayout
                Snackbar.make(
                    mRootView,
                    getString(R.string.username_not_found),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showRecyclerList(list: List<ListUserResponse>) {
        githubUserAdapter = GithubUserAdapter(list)

        githubUserAdapter.setOnClickItem(object : GithubUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ListUserResponse) {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToDetailUserFragment(data, null)
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
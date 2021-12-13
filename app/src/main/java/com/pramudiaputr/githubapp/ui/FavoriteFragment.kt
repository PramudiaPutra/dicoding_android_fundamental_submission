package com.pramudiaputr.githubapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pramudiaputr.githubapp.databinding.FragmentFavoriteBinding
import com.pramudiaputr.githubapp.model.UserDetailResponse
import com.pramudiaputr.githubapp.ui.adapter.FavoriteAdapter
import com.pramudiaputr.githubapp.ui.viewmodel.FavoriteViewModel
import com.pramudiaputr.githubapp.ui.viewmodel.detailuser.DetailViewModelFactory

class FavoriteFragment : Fragment() {

    private lateinit var favoriteAdapter: FavoriteAdapter
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = obtainViewModel(activity as AppCompatActivity)
        viewModel.getAllFavorite().observe(viewLifecycleOwner, { favoriteList ->
            if (favoriteList != null) {
                showFavoriteList(favoriteList)
                if (favoriteList.isNotEmpty()) {
                    binding.tvEmptyList.visibility = View.GONE
                } else {
                    binding.tvEmptyList.visibility = View.VISIBLE
                }
            }
        })

    }

    private fun showFavoriteList(favoriteList: List<UserDetailResponse>) {
        favoriteAdapter = FavoriteAdapter(favoriteList)

        favoriteAdapter.setOnClickItem(object : FavoriteAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserDetailResponse) {
                findNavController().navigate(
                    FavoriteFragmentDirections.actionFavoriteFragmentToDetailUserFragment(
                        null,
                        data
                    )
                )
            }
        })

        with(binding.recyclerViewFavorite) {
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            adapter = favoriteAdapter
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): FavoriteViewModel {
        val factory = DetailViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[FavoriteViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
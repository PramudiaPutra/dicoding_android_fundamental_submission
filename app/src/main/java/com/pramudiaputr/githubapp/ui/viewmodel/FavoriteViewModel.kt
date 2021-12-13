package com.pramudiaputr.githubapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pramudiaputr.githubapp.database.FavoriteRepository
import com.pramudiaputr.githubapp.model.UserDetailResponse

class FavoriteViewModel(application: Application): ViewModel(){
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun getAllFavorite(): LiveData<List<UserDetailResponse>> = mFavoriteRepository.getFavorites()
}
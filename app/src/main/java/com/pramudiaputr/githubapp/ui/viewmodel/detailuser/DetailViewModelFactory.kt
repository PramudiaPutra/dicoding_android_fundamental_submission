package com.pramudiaputr.githubapp.ui.viewmodel.detailuser

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pramudiaputr.githubapp.ui.viewmodel.FavoriteViewModel

class DetailViewModelFactory private constructor(private val mApplication: Application) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(mApplication) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var INSTANCE: DetailViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): DetailViewModelFactory {
            if (INSTANCE == null) {
                synchronized(DetailViewModelFactory::class.java) {
                    INSTANCE = DetailViewModelFactory(application)
                }
            }
            return INSTANCE as DetailViewModelFactory
        }
    }
}
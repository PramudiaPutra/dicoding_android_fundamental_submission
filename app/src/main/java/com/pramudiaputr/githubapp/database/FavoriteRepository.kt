package com.pramudiaputr.githubapp.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.pramudiaputr.githubapp.model.UserDetailResponse
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val mFavoriteDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteDatabase.getDatabase(application)
        mFavoriteDao = db.favoriteDao()
    }

    fun getFavorites(): LiveData<List<UserDetailResponse>> = mFavoriteDao.getFavorites()

    fun insert(user: UserDetailResponse) = executorService.execute { mFavoriteDao.insert(user) }

    fun delete(user: UserDetailResponse) = executorService.execute { mFavoriteDao.delete(user) }

}
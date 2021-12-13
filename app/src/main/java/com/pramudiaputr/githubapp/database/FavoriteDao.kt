package com.pramudiaputr.githubapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pramudiaputr.githubapp.model.UserDetailResponse

@Dao
interface FavoriteDao {
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insert(user: UserDetailResponse)

    @Delete
    fun delete(user: UserDetailResponse)

    @Query("SELECT * FROM userdetailresponse ORDER BY id ASC")
    fun getFavorites(): LiveData<List<UserDetailResponse>>
}
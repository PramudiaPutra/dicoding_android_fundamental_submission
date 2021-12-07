package com.pramudiaputr.githubapp.network

import com.pramudiaputr.githubapp.model.ListUserResponse
import com.pramudiaputr.githubapp.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val token = "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"

interface ApiService {

    @GET("users")
    fun getUserList(): Call<List<ListUserResponse>>

    @GET("search/users")
    @Headers("Authorization: token $token")
    fun searchUser(@Query("q") username: String): Call<SearchResponse>
}
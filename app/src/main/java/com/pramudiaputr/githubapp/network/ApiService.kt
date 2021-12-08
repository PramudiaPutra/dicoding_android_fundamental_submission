package com.pramudiaputr.githubapp.network

import com.pramudiaputr.githubapp.model.ListUserResponse
import com.pramudiaputr.githubapp.model.SearchResponse
import com.pramudiaputr.githubapp.model.UserDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

const val token = "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"

interface ApiService {

    @GET("users")
    @Headers("Authorization: token $token")
    fun getUserList(): Call<List<ListUserResponse>>

    @GET("search/users")
    fun searchUser(@Query("q") username: String): Call<SearchResponse>

    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String): Call<UserDetailResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<ListUserResponse>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<ListUserResponse>>

}
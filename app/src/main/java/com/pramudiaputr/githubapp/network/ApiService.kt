package com.pramudiaputr.githubapp.network

import com.pramudiaputr.githubapp.BuildConfig
import com.pramudiaputr.githubapp.model.ListUserResponse
import com.pramudiaputr.githubapp.model.SearchResponse
import com.pramudiaputr.githubapp.model.UserDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

const val token = BuildConfig.KEY

interface ApiService {

    @GET("users")
    @Headers("Authorization: token $token")
    fun getUserList(): Call<List<ListUserResponse>>

    @GET("search/users")
    @Headers("Authorization: token $token")
    fun searchUser(@Query("q") username: String): Call<SearchResponse>

    @GET("users/{username}")
    @Headers("Authorization: token $token")
    fun getDetailUser(@Path("username") username: String): Call<UserDetailResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token $token")
    fun getFollowers(@Path("username") username: String): Call<List<ListUserResponse>>

    @GET("users/{username}/following")
    @Headers("Authorization: token $token")
    fun getFollowing(@Path("username") username: String): Call<List<ListUserResponse>>

}
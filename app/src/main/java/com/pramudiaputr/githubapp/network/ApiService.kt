package com.pramudiaputr.githubapp.network

import com.pramudiaputr.githubapp.model.ListUserResponse
import retrofit2.Call
import retrofit2.http.GET

val token = "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"

interface ApiService {

    @GET("users")
    fun getUserList(): Call<List<ListUserResponse>>
}
package com.pramudiaputr.githubapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pramudiaputr.githubapp.model.ListUserResponse
import com.pramudiaputr.githubapp.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel : ViewModel() {

    private val _listFollower = MutableLiveData<List<ListUserResponse>>()
    val listFollower: LiveData<List<ListUserResponse>> = _listFollower

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _countList = MutableLiveData<Int>()
    val countList: LiveData<Int> = _countList

    fun getFollower(username: String) {
        _isLoading.value = true

        val client = ApiConfig.getApiServices().getFollowers(username)
        client.enqueue(object : Callback<List<ListUserResponse>> {
            override fun onResponse(
                call: Call<List<ListUserResponse>>,
                response: Response<List<ListUserResponse>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listFollower.value = response.body()
                    _countList.value = response.body()?.size
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ListUserResponse>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure connect: ${t.message}")
            }
        })
    }

    companion object {
        private val TAG = FollowerViewModel::class.java.simpleName
    }
}
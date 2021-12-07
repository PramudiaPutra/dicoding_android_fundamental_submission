package com.pramudiaputr.githubapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pramudiaputr.githubapp.model.ListUserResponse
import com.pramudiaputr.githubapp.model.SearchResponse
import com.pramudiaputr.githubapp.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private var _listUser = MutableLiveData<List<ListUserResponse>>()
    var listUser: LiveData<List<ListUserResponse>> = _listUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isConnected = MutableLiveData<Boolean>()
    val isConnected: LiveData<Boolean> = _isConnected

    companion object {
        const val TAG = "MainViewModel"
    }

    init {
        getListUser()
    }

    private fun getListUser() {
        _isLoading.value = true

        val client = ApiConfig.getApiServices().getUserList()
        client.enqueue(object : Callback<List<ListUserResponse>> {
            override fun onResponse(
                call: Call<List<ListUserResponse>>,
                response: Response<List<ListUserResponse>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listUser.value = response.body()
                    _isConnected.value = true
                } else {
                    _isConnected.value = false
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ListUserResponse>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun searchUser(username: String) {
        _isLoading.value = true

        val client = ApiConfig.getApiServices().searchUser(username)
        client.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listUser.value = response.body()?.items
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }
}
package com.pramudiaputr.githubapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUserModel(
    val username: String,
    val name: String,
    val avatar: Int,
    val company: String,
    val location: String,
    val repository: String,
    val follower: String,
    val following: String,
) : Parcelable

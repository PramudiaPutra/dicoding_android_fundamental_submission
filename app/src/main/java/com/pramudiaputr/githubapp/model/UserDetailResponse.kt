package com.pramudiaputr.githubapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class UserDetailResponse(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false,

    @ColumnInfo(name = "username")
    @SerializedName("login")
    var login: String = "",

    @Ignore
    @SerializedName("avatar_url")
    val avatarUrl: String = "",

    @Ignore
    @SerializedName("company")
    val company: String? = null,

    @Ignore
    @SerializedName("followers")
    val followers: Int = 0,

    @Ignore
    @SerializedName("following")
    val following: Int = 0,

    @Ignore
    @SerializedName("location")
    val location: String? = null,

    @Ignore
    @SerializedName("name")
    val name: String = "",

    @Ignore
    @SerializedName("public_repos")
    val publicRepos: Int = 0,
) : Parcelable
package com.pramudiaputr.githubapp.model
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<ListUserResponse>,
    @SerializedName("total_count")
    val totalCount: Int
)
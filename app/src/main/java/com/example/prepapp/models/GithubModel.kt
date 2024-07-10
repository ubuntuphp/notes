package com.example.prepapp.models

import com.google.gson.annotations.SerializedName


data class GithubModel(
    @SerializedName("username") var username: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("type") var type: String,
    @SerializedName("url") var url: String,
    @SerializedName("avatar") var avatar: String,
    @SerializedName("repo") var repo: GithubRepoData

)

data class GithubRepoData(
    @SerializedName("name") var name: String,
    @SerializedName("description") var description: String,
    @SerializedName("url") var url: String

)
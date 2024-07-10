package com.example.prepapp.api

import com.example.prepapp.models.GithubModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("p6818/trending")
    suspend fun getTrendingRepos():List<GithubModel>
}
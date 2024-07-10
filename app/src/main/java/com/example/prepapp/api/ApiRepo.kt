package com.example.prepapp.api

import androidx.lifecycle.viewModelScope
import com.example.prepapp.models.GithubModel
import com.example.prepapp.models.GithubRepoData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import retrofit2.Response

object ApiRepo {
    private val apiModule = ApiModule.apiModule
    private val githubApiInterface = apiModule.create(ApiInterface::class.java)

    suspend fun getGithubRepos():List<GithubModel>{
        return githubApiInterface.getTrendingRepos()
    }
    private var counter = 0
    private val counterFlow: SharedFlow<Int> = flow {
        while (true) {
            emit(counter++)
            delay(1000L)
        }
    }.shareIn(CoroutineScope(Dispatchers.IO), SharingStarted.Lazily)

    fun getLiveCounter(): SharedFlow<Int> {
        return counterFlow
    }
}
package com.example.prepapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiModule {
    val apiModule: Retrofit by lazy {
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).addConverterFactory(
            MoshiConverterFactory.create())
            .baseUrl("https://api.mocklets.com/").build()
    }
}
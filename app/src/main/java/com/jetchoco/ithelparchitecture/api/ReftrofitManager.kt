package com.jetchoco.ithelparchitecture.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager private constructor() {

    companion object {
        private val instance = RetrofitManager()

        fun getAPI() = instance.githubService
    }

    private var githubService: GithubService = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GithubService::class.java)
}
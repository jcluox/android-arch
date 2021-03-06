package com.jetchoco.ithelparchitecture.api

import com.jetchoco.ithelparchitecture.data.model.RepoSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String): Call<RepoSearchResponse>
}
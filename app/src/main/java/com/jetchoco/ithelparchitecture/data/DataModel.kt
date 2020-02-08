package com.jetchoco.ithelparchitecture.data

import androidx.lifecycle.LiveData
import com.jetchoco.ithelparchitecture.api.ApiResponse
import com.jetchoco.ithelparchitecture.api.GithubService
import com.jetchoco.ithelparchitecture.data.model.RepoSearchResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataModel @Inject constructor(val githubService: GithubService) {

    companion object {
        const val TAG = "DataModel"
    }

    fun searchRepo(query: String): LiveData<ApiResponse<RepoSearchResponse>> {
        return githubService.searchRepos(query)
    }
}
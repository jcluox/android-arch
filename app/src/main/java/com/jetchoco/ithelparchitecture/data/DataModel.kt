package com.jetchoco.ithelparchitecture.data

import androidx.lifecycle.LiveData
import com.jetchoco.ithelparchitecture.api.ApiResponse
import com.jetchoco.ithelparchitecture.api.RetrofitManager
import com.jetchoco.ithelparchitecture.data.model.RepoSearchResponse

class DataModel {

    companion object {
        const val TAG = "DataModel"
    }

    private val githubService = RetrofitManager.getAPI()

    fun searchRepo(query: String): LiveData<ApiResponse<RepoSearchResponse>> {
        return githubService.searchRepos(query)
    }
}
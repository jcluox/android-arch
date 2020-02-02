package com.jetchoco.ithelparchitecture.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jetchoco.ithelparchitecture.api.RetrofitManager
import com.jetchoco.ithelparchitecture.data.model.Repo
import com.jetchoco.ithelparchitecture.data.model.RepoSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataModel {

    companion object {
        const val TAG = "DataModel"
    }

    private val githubService = RetrofitManager.getAPI()

    fun searchRepo(query: String): MutableLiveData<List<Repo>> {
        val repos = MutableLiveData<List<Repo>>()
        githubService.searchRepos(query).enqueue(object : Callback<RepoSearchResponse> {
            override fun onFailure(call: Call<RepoSearchResponse>, t: Throwable) {
                // TODO handle error
                Log.d(TAG, "searchRepo API error")
            }

            override fun onResponse(
                call: Call<RepoSearchResponse>,
                response: Response<RepoSearchResponse>
            ) {
                if (response.isSuccessful) {
                    repos.value = response.body()!!.items
                }
            }
        })
        return repos
    }
}
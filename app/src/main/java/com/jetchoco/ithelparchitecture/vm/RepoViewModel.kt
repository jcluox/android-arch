package com.jetchoco.ithelparchitecture.vm

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jetchoco.ithelparchitecture.data.DataModel
import com.jetchoco.ithelparchitecture.data.model.Repo

class RepoViewModel(private val dataModel: DataModel) : ViewModel() {

    companion object {
        private const val TAG = "RepoViewModel"
    }

    val isLoading = ObservableField<Boolean>(false)
    private val repos = MutableLiveData<List<Repo>>()

    fun repos() = repos

    fun doSearch(query: String) {
        Log.d(TAG, "set isLoading to true")
        isLoading.set(true)
        dataModel.searchRepo(query, object : DataModel.OnDataReadyCallback {
            override fun onDataReady(data: List<Repo>) {
                repos.value = data
                isLoading.set(false)
                Log.d(TAG, "set isLoading to false")
            }
        })
    }
}
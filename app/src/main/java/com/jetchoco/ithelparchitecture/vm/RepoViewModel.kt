package com.jetchoco.ithelparchitecture.vm

import android.text.TextUtils
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.jetchoco.ithelparchitecture.api.ApiResponse
import com.jetchoco.ithelparchitecture.data.DataModel
import com.jetchoco.ithelparchitecture.data.model.RepoSearchResponse
import com.jetchoco.ithelparchitecture.util.AbsentLiveData
import javax.inject.Inject

class RepoViewModel @Inject constructor(private val dataModel: DataModel) : ViewModel() {

    companion object {
        private const val TAG = "RepoViewModel"
    }

    val isLoading = ObservableField<Boolean>(false)
    private val query = MutableLiveData<String>()
    private val repos: LiveData<ApiResponse<RepoSearchResponse>>

    fun repos() = repos

    fun doSearch(userInput: String) {
        isLoading.set(true)
        query.value = userInput
    }

    init {
        repos = Transformations.switchMap(query) {
            if (TextUtils.isEmpty(it)) {
                AbsentLiveData.create()
            } else {
                dataModel.searchRepo(it)
            }
        }
    }
}
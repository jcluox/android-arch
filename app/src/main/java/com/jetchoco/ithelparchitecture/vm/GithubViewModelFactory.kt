package com.jetchoco.ithelparchitecture.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jetchoco.ithelparchitecture.data.DataModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubViewModelFactory @Inject constructor(val dataModel: DataModel) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RepoViewModel(dataModel) as T
        }
        throw IllegalArgumentException("No such model class")
    }
}
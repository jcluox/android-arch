package com.jetchoco.ithelparchitecture.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jetchoco.ithelparchitecture.data.DataModel

class GithubViewModelFactory : ViewModelProvider.Factory {

    private val dataModel = DataModel()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RepoViewModel(dataModel) as T
        }
        throw IllegalArgumentException("No such model class")
    }
}
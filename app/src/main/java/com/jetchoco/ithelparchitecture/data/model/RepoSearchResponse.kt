package com.jetchoco.ithelparchitecture.data.model

import com.google.gson.annotations.SerializedName

data class RepoSearchResponse(
    @SerializedName("total_count")
    val total: Int?,
    val items: List<Repo>?
)
package com.jetchoco.ithelparchitecture.data.model

import com.google.gson.annotations.SerializedName

data class Repo(
    val id: Int?,
    val name: String,
    @SerializedName("full_name")
    val fullName: String?,
    val description: String?,
    @SerializedName("stargazers_count") val stars: Int?,
    val owner: Owner
)
package com.example.midd_exam.api

import com.google.gson.annotations.SerializedName

data class Cities(
    val cityname: String?,
    val description: String?,
    val imageUrl: String?,
    val location: String?,
    val website: String?,
    val id: String?
)

data class ReqresObj<T>(
    var page: Int?,
    @SerializedName("per_page")
    var perPage: Int?,
    var total: Long?,
    @SerializedName("total_pages")
    var totalPages: Int?,
    var data: T?
)
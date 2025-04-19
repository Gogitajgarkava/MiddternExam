package com.example.midd_exam.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqResService {
    @GET("mid")
    fun getCities(@Query("page") page: Int): Call<ReqresObj<List<Cities>>>
}
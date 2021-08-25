package com.example.mvvmdaggerflowretrofit.custom.api

import com.example.mvvmdaggerflowretrofit.custom.datas.Users
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiConfig {
    @GET("users")
    suspend fun getUsers(
        @Query("per_page") perPage: Int,
        @Query("since") since: Int
    ): Response<Users>
}
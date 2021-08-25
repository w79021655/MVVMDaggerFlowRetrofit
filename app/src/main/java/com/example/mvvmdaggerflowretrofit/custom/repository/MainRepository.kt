package com.example.mvvmdaggerflowretrofit.custom.repository

import com.example.mvvmdaggerflowretrofit.custom.api.ApiResult
import com.example.mvvmdaggerflowretrofit.custom.api.IApiConfig
import com.example.mvvmdaggerflowretrofit.custom.datas.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException
import javax.inject.Inject

class MainRepository @Inject constructor(_apiConfig: IApiConfig) {
    private val apiConfig: IApiConfig = _apiConfig

    fun getUsers(perPage: Int, since: Int): Flow<ApiResult<Users?>> = flow {
        try {
            val result = apiConfig.getUsers(perPage, since)

            if (result.isSuccessful) {
                emit(ApiResult.success(result.body()))
            } else {
                emit(ApiResult.error(null, "Server Error"))
            }
        } catch (e: UnknownHostException) {
            e.printStackTrace()
        }

    }.flowOn(Dispatchers.IO)
}
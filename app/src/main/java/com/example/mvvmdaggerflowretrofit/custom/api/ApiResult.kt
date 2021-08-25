package com.example.mvvmdaggerflowretrofit.custom.api

import com.example.mvvmdaggerflowretrofit.custom.enum.Response

data class ApiResult<out T>(val status: Response, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): ApiResult<T> =
            ApiResult(status = Response.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): ApiResult<T> =
            ApiResult(status = Response.ERROR, data = data, message = message)

        fun <T> loading(data: T?): ApiResult<T> =
            ApiResult(status = Response.LOADING, data = data, message = null)
    }
}
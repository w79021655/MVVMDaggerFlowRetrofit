package com.example.mvvmdaggerflowretrofit.di.module

import com.example.mvvmdaggerflowretrofit.custom.api.IApiConfig
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
open class NetworkModule {
    @Provides
    @Singleton
    open fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(client)
        .build()

    @Provides
    @Singleton
    open fun provideApiService(retrofit: Retrofit): IApiConfig = retrofit.create(IApiConfig::class.java)

    companion object {
        private val client: OkHttpClient
            get() = OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.SECONDS)
                .writeTimeout(10000, TimeUnit.SECONDS)
                .readTimeout(10000, TimeUnit.SECONDS)
                .build()
    }
}
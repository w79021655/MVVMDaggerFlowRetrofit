package com.example.mvvmdaggerflowretrofit.di.module

import com.example.mvvmdaggerflowretrofit.utils.SharedPrefUtil
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class SharedPrefModule {

    @Singleton
    @Provides
    fun provideSharedPrefUtil(): SharedPrefUtil {
        return SharedPrefUtil()
    }

}
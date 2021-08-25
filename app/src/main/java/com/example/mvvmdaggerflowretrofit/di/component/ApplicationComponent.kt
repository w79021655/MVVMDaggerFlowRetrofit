package com.example.mvvmdaggerflowretrofit.di.component

import com.example.mvvmdaggerflowretrofit.custom.view.index.MainActivity
import com.example.mvvmdaggerflowretrofit.di.module.NetworkModule
import com.example.mvvmdaggerflowretrofit.di.module.SharedPrefModule
import com.example.mvvmdaggerflowretrofit.di.module.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedPrefModule::class, NetworkModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

}
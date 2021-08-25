package com.example.mvvmdaggerflowretrofit.custom

import android.app.Application
import com.example.mvvmdaggerflowretrofit.di.component.ApplicationComponent
import com.example.mvvmdaggerflowretrofit.di.component.DaggerApplicationComponent

class MyApplication : Application() {
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
}
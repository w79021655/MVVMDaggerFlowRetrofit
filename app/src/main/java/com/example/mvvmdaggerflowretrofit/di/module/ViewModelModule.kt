package com.example.mvvmdaggerflowretrofit.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmdaggerflowretrofit.custom.viewModel.MainViewModel
import com.example.mvvmdaggerflowretrofit.di.annotation.ViewModelKey
import com.example.mvvmdaggerflowretrofit.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds//方法必須是抽象或介面
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun mainViewModel(viewModel: MainViewModel): ViewModel
}
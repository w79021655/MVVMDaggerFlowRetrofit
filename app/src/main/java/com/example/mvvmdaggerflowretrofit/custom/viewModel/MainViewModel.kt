package com.example.mvvmdaggerflowretrofit.custom.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmdaggerflowretrofit.custom.api.ApiResult
import com.example.mvvmdaggerflowretrofit.custom.datas.Users
import com.example.mvvmdaggerflowretrofit.custom.repository.MainRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(_repository: MainRepository) : ViewModel() {
    private val repository: MainRepository = _repository
    private val users = MutableLiveData<ApiResult<Users?>>()

    fun fetchUsers(perPage: Int, since: Int): LiveData<ApiResult<Users?>> {
        users.postValue(ApiResult.loading(null))

        viewModelScope.launch {
            repository.getUsers(perPage, since)
                .map {
                    it
                }.collect {
                    users.postValue(it)
                }
        }

        return users
    }
}
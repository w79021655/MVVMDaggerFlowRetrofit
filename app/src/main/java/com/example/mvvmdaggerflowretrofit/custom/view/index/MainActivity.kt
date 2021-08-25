package com.example.mvvmdaggerflowretrofit.custom.view.index

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdaggerflowretrofit.custom.MyApplication
import com.example.mvvmdaggerflowretrofit.custom.enum.Response
import com.example.mvvmdaggerflowretrofit.custom.view.index.adapter.UsersAdapter
import com.example.mvvmdaggerflowretrofit.custom.viewModel.MainViewModel
import com.example.mvvmdaggerflowretrofit.databinding.ActivityMainBinding
import com.example.mvvmdaggerflowretrofit.utils.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var mAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (this@MainActivity.application as MyApplication).appComponent.inject(this)

        setupAdapter()
        setupViewModel()
        fetchUsers()
    }

    private fun setupAdapter() {
        binding.recyclerView.apply {
            val layoutManager = LinearLayoutManager(this@MainActivity)
            layoutManager.orientation = RecyclerView.VERTICAL
            binding.recyclerView.layoutManager = layoutManager
            mAdapter = UsersAdapter()
            binding.recyclerView.adapter = mAdapter
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private fun fetchUsers() {
        viewModel.fetchUsers(20, 0).observe(this, {
            it.let { apiResult ->
                when (apiResult.status) {
                    Response.SUCCESS -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.loading.visibility = View.GONE

                        apiResult.data.let { users ->
                            if (users == null) {
                                binding.recyclerView.visibility = View.GONE
                            } else {
                                mAdapter.setUsers(users)
                            }
                        }
                    }
                    Response.ERROR -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.loading.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Response.LOADING -> {
                        binding.loading.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }
}
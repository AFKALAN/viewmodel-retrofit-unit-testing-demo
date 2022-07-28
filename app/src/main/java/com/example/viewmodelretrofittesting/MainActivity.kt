package com.example.viewmodelretrofittesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModelFactory = MainViewModelFactory(ApiClient.apiService)
        mainViewModel =ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]

        mainViewModel.getData("madona")
    }
}
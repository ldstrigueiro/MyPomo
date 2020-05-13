package com.example.mypomo.di

import com.example.mypomo.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel { MainViewModel() }
}
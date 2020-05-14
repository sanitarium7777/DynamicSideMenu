package com.github.sanitarium7777.di

import com.github.sanitarium7777.data.api.RemoteDataSource
import com.github.sanitarium7777.data.api.ApiService
import com.github.sanitarium7777.data.api.RetrofitBuilder
import com.github.sanitarium7777.data.local.LocalDataSource
import com.github.sanitarium7777.data.repository.MenuRepository
import com.github.sanitarium7777.ui.image.ImageFragmentViewModel
import com.github.sanitarium7777.ui.main.MainViewModel
import com.github.sanitarium7777.ui.message.MessageFragmentViewModel
import com.github.sanitarium7777.ui.url.UrlFragmentViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelsModule: Module = module {
    viewModel { MainViewModel(get(), androidContext()) }
    viewModel { ImageFragmentViewModel() }
    viewModel { UrlFragmentViewModel() }
    viewModel { MessageFragmentViewModel() }
}

val apiModule: Module = module {
    single { RemoteDataSource(RetrofitBuilder.createApiService(ApiService::class.java)) }
    single { MenuRepository(get(), get()) }
    single { LocalDataSource() }
}
package com.github.sanitarium7777

import android.app.Application
import com.github.sanitarium7777.di.apiModule
import com.github.sanitarium7777.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(listOf(viewModelsModule, apiModule))}
    }
}
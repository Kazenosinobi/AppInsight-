package com.example.appinsight.core

import android.app.Application
import com.example.appinsight.di.interactorModule
import com.example.appinsight.di.repositoryModule
import com.example.appinsight.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(repositoryModule, interactorModule, viewModelModule))
        }
    }
}
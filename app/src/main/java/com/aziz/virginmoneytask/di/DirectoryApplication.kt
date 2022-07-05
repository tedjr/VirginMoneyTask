package com.aziz.virginmoneytask.di

import android.app.Application
import networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import viewModelModule

class DirectoryApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DirectoryApplication)
            modules(listOf(networkModule, viewModelModule))
        }
    }
}
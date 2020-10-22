package com.example.reignchallenge

import android.app.Application
import com.example.reignchallenge.di.apiModule
import com.example.reignchallenge.di.dbModule
import com.example.reignchallenge.di.noticeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(dbModule, apiModule, noticeModule))
        }
    }
}
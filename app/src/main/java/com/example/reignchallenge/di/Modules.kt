package com.example.reignchallenge.di

import com.example.reignchallenge.repositories.NoticeRepository
import com.example.reignchallenge.repositories.local.db.AppDatabase
import com.example.reignchallenge.repositories.remote.ApiInstance
import com.example.reignchallenge.repositories.remote.NoticeService
import com.example.reignchallenge.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val apiModule = module {
    single { ApiInstance.create() }
}

val dbModule = module {
    single{ AppDatabase.getDatabase(get()) }
    single{ (get<AppDatabase>().NoticeDeletedDAO())}
}

val noticeModule = module {
    viewModel { HomeViewModel(get()) }
    single { NoticeRepository(get(),get()) }
    single { NoticeService((get())) }
}
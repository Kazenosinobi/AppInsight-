package com.example.appinsight.di

import com.example.appinsight.applicationInfo.ui.AppInfoViewModel
import com.example.appinsight.applicationsList.ui.AppsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    viewModelOf(::AppInfoViewModel)
    viewModelOf(::AppsListViewModel)

}
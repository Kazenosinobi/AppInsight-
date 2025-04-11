package com.example.appinsight.di

import android.content.Context
import org.koin.dsl.module

val dataModule = module {
    single { get<Context>().packageManager }
}
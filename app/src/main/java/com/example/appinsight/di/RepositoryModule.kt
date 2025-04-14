package com.example.appinsight.di

import com.example.appinsight.applicationInfo.data.impl.AppInfoRepositoryImpl
import com.example.appinsight.applicationInfo.domain.api.AppInfoRepository
import com.example.appinsight.applicationsList.data.impl.AppsRepositoryImpl
import com.example.appinsight.applicationsList.domain.api.AppsRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {

    factoryOf(::AppsRepositoryImpl).bind<AppsRepository>()
    factoryOf(::AppInfoRepositoryImpl).bind<AppInfoRepository>()

}
package com.example.appinsight.di

import com.example.appinsight.applicationInfo.domain.api.AppInfoInteractor
import com.example.appinsight.applicationInfo.domain.impl.AppInfoInteractorImpl
import com.example.appinsight.applicationsList.domain.api.AppsInteractor
import com.example.appinsight.applicationsList.domain.impl.AppsInteractorImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val interactorModule = module {

    factoryOf(::AppsInteractorImpl).bind<AppsInteractor>()
    factoryOf(::AppInfoInteractorImpl).bind<AppInfoInteractor>()

}
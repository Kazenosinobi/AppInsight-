package com.example.appinsight.applicationsList.domain.api

import com.example.appinsight.applicationsList.domain.models.AppItem

interface AppsInteractor {
    fun getInstallApps(): List<AppItem>
}
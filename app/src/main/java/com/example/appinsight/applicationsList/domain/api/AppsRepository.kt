package com.example.appinsight.applicationsList.domain.api

import com.example.appinsight.applicationsList.domain.models.AppItem

interface AppsRepository {
    fun getInstallApps(): List<AppItem>
}

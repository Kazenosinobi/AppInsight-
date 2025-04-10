package com.example.appinsight.applicationInfo.domain.api

import com.example.appinsight.applicationInfo.domain.models.AppInfo

interface AppInfoInteractor {
    fun getAppInfo(packageName: String): AppInfo
}
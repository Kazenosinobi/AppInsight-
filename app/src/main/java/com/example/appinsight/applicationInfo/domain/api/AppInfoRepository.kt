package com.example.appinsight.applicationInfo.domain.api

import com.example.appinsight.applicationInfo.domain.models.AppInfo

interface AppInfoRepository {
    fun getAppInfo(packageName: String): AppInfo?
}
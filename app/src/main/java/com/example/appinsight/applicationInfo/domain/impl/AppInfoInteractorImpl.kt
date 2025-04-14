package com.example.appinsight.applicationInfo.domain.impl

import com.example.appinsight.applicationInfo.domain.api.AppInfoInteractor
import com.example.appinsight.applicationInfo.domain.api.AppInfoRepository
import com.example.appinsight.applicationInfo.domain.models.AppInfo

class AppInfoInteractorImpl(
    private val repository: AppInfoRepository,
) : AppInfoInteractor {
    override fun getAppInfo(packageName: String): AppInfo? {
        return repository.getAppInfo(packageName)
    }

}
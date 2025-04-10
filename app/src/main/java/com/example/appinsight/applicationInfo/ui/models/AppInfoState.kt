package com.example.appinsight.applicationInfo.ui.models

import com.example.appinsight.applicationInfo.domain.models.AppInfo

sealed class AppInfoState {
    data class Success(val appInfo: AppInfo): AppInfoState()
    object Loading: AppInfoState()
    object Init: AppInfoState()
    object Error: AppInfoState()
}
package com.example.appinsight.applicationInfo.ui.models

import androidx.compose.runtime.Immutable
import com.example.appinsight.applicationInfo.domain.models.AppInfo

sealed class AppInfoState {
    @Immutable
    data class Success(val appInfo: AppInfo): AppInfoState()
    data object Loading: AppInfoState()
    data object Init: AppInfoState()
    data object Error: AppInfoState()
}
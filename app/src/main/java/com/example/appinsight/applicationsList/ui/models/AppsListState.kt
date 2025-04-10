package com.example.appinsight.applicationsList.ui.models

import com.example.appinsight.applicationsList.domain.models.AppItem

sealed class AppsListState {
    data class Success(val appItem: List<AppItem>) : AppsListState()
    object Loading: AppsListState()
    object Init: AppsListState()
    object Error: AppsListState()
}
package com.example.appinsight.applicationsList.ui.models

import androidx.compose.runtime.Immutable
import com.example.appinsight.applicationsList.domain.models.AppItem

sealed class AppsListState {
    @Immutable
    data class Success(val appItem: List<AppItem>) : AppsListState()
    data object Loading: AppsListState()
    data object Init: AppsListState()
    data object Error: AppsListState()
}

package com.example.appinsight.applicationsList.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appinsight.applicationsList.domain.api.AppsInteractor
import com.example.appinsight.applicationsList.ui.models.AppsListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppsListViewModel(
    private val interactor: AppsInteractor
) : ViewModel() {
    private val appItemState = MutableStateFlow(AppsListState.Init as AppsListState)
    fun getAppItemState() = appItemState.asStateFlow()

    fun loadAppsList() {
        viewModelScope.launch {
            appItemState.emit(AppsListState.Loading)
            try {
                val appsList = interactor.getInstallApps()
                appItemState.emit(AppsListState.Success(appsList))
            } catch (e: Exception) {
                appItemState.emit(AppsListState.Error)
            }
        }
    }
}
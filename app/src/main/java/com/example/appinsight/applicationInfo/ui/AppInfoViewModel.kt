package com.example.appinsight.applicationInfo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appinsight.applicationInfo.domain.api.AppInfoInteractor
import com.example.appinsight.applicationInfo.ui.models.AppInfoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppInfoViewModel(
    private val interactor: AppInfoInteractor,
    private val packageName: String,
) : ViewModel() {
    private val appInfoState = MutableStateFlow(AppInfoState.Init as AppInfoState)
    fun getAppInfoState() = appInfoState.asStateFlow()

    fun loadAppInfo() {
        viewModelScope.launch {
            appInfoState.emit(AppInfoState.Loading)
            try {
                val appInfo = interactor.getAppInfo(packageName)
                appInfoState.emit(AppInfoState.Success(appInfo))
            } catch (e: Exception) {
                appInfoState.emit(AppInfoState.Error)
            }
        }
    }
}
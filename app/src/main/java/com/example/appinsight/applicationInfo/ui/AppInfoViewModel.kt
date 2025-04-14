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
    private val appInfoState: MutableStateFlow<AppInfoState> = MutableStateFlow(AppInfoState.Init)

    init {
        loadAppInfo()
    }

    fun getAppInfoState() = appInfoState.asStateFlow()

    fun loadAppInfo() {
        viewModelScope.launch {
            appInfoState.emit(AppInfoState.Loading)
            val appInfo = interactor.getAppInfo(packageName)
            appInfo?.let {
                appInfoState.emit(AppInfoState.Success(it))
            } ?: appInfoState.emit(AppInfoState.Error)
        }
    }
}
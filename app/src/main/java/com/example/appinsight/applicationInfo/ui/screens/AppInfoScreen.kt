package com.example.appinsight.applicationInfo.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.appinsight.applicationInfo.ui.AppInfoViewModel
import com.example.appinsight.applicationInfo.ui.models.AppInfoState
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppInfoScreen() {
    val viewModel: AppInfoViewModel = koinViewModel()
    val viewState = viewModel.getAppInfoState().collectAsStateWithLifecycle()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        when (viewState.value) {
            AppInfoState.Error -> ErrorView(
                onRetry = { viewModel.loadAppInfo() }
            )

            AppInfoState.Init -> viewModel.loadAppInfo()
            AppInfoState.Loading -> LoadingView()
            is AppInfoState.Success -> AppInfoSuccess(
                appInfo = (viewState.value as AppInfoState.Success).appInfo
            )
        }
    }
}
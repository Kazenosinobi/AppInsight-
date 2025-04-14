package com.example.appinsight.applicationInfo.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.appinsight.applicationInfo.ui.AppInfoViewModel
import com.example.appinsight.applicationInfo.ui.models.AppInfoState
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AppInfoScreen(
    packageName: String,
    navController: NavController
) {
    val viewModel: AppInfoViewModel = koinViewModel{ parametersOf(packageName) }
    val viewState = viewModel.getAppInfoState().collectAsStateWithLifecycle()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (viewState.value) {
            AppInfoState.Error -> ErrorView(
                onRetry = { viewModel.loadAppInfo() }
            )

            AppInfoState.Init -> viewModel.loadAppInfo()
            AppInfoState.Loading -> LoadingView()
            is AppInfoState.Success -> AppInfoSuccess(
                appInfo = (viewState.value as AppInfoState.Success).appInfo,
                onClick = { navController.popBackStack() }
            )
        }
    }
}
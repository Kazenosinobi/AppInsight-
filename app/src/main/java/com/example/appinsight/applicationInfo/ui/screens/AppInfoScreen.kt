package com.example.appinsight.applicationInfo.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.appinsight.R
import com.example.appinsight.applicationInfo.ui.AppInfoViewModel
import com.example.appinsight.applicationInfo.ui.models.AppInfoState
import com.example.appinsight.core.ui_kit.ErrorView
import com.example.appinsight.core.ui_kit.LoadingView
import com.example.appinsight.core.ui_kit.ToolBar
import com.example.appinsight.utils.statusBarHeightEdgeToEdge
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AppInfoScreen(
    modifier: Modifier = Modifier,
    packageName: String,
    navController: NavController
) {
    val viewModel: AppInfoViewModel = koinViewModel{ parametersOf(packageName) }
    val viewState = viewModel.getAppInfoState().collectAsStateWithLifecycle()
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ToolBar(
                modifier = Modifier.statusBarHeightEdgeToEdge(),
                text = stringResource(R.string.app_info_screen_name),
                onClick = { navController.popBackStack() }
            )
            AnimatedContent(targetState = viewState.value) { state ->
                when (state) {
                    AppInfoState.Error -> ErrorView(
                        text = stringResource(R.string.app_info_screen_error),
                        onRetry = { viewModel.loadAppInfo() }
                    )

                    AppInfoState.Init -> viewModel.loadAppInfo()
                    AppInfoState.Loading -> LoadingView(
                        text = stringResource(R.string.app_info_screen_loading)
                    )

                    is AppInfoState.Success -> AppInfoSuccess(
                        appInfo = (viewState.value as AppInfoState.Success).appInfo,
                    )
                }
            }
        }
    }
}
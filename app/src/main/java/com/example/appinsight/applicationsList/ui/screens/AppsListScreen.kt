package com.example.appinsight.applicationsList.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.appinsight.R
import com.example.appinsight.applicationInfo.ui.AppInfoFragment
import com.example.appinsight.applicationsList.ui.AppsListViewModel
import com.example.appinsight.applicationsList.ui.models.AppsListState
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppsListScreen(navController: NavController) {

    val viewModel: AppsListViewModel = koinViewModel()
    val viewState = viewModel.getAppItemState().collectAsStateWithLifecycle()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        when (viewState.value) {
            is AppsListState.Init -> viewModel.loadAppsList()
            AppsListState.Error -> ErrorView(onRetry = { viewModel.loadAppsList() })
            AppsListState.Loading -> LoadingView()
            is AppsListState.Success -> {
                AppsListSuccess(
                    apps = (viewState.value as AppsListState.Success).appItem,
                    onClick = { app ->
                        navController.navigate(
                            R.id.action_appsListFragment_to_appInfoFragment,
                            AppInfoFragment.createArgs(app.packageName)
                        )
                    }
                )
            }
        }
    }
}
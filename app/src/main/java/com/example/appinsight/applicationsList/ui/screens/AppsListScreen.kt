package com.example.appinsight.applicationsList.ui.screens

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
import com.example.appinsight.applicationInfo.ui.AppInfoFragment
import com.example.appinsight.applicationsList.ui.AppsListViewModel
import com.example.appinsight.applicationsList.ui.models.AppsListState
import com.example.appinsight.core.ui_kit.ErrorView
import com.example.appinsight.core.ui_kit.LoadingView
import com.example.appinsight.core.ui_kit.ToolBar
import com.example.appinsight.utils.statusBarHeightEdgeToEdge
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppsListScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    val viewModel: AppsListViewModel = koinViewModel()
    val viewState = viewModel.getAppItemState().collectAsStateWithLifecycle()
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
                text = stringResource(R.string.apps_screen_name),
            )
            when (viewState.value) {
                is AppsListState.Init -> viewModel.loadAppsList()
                AppsListState.Error -> ErrorView(
                    text = stringResource(R.string.apps_screen_error),
                    onRetry = { viewModel.loadAppsList() }
                )

                AppsListState.Loading -> LoadingView(
                    text = stringResource(R.string.apps_screen_loading)
                )

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
}
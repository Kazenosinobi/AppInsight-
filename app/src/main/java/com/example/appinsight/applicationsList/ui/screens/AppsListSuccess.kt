package com.example.appinsight.applicationsList.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appinsight.applicationsList.domain.models.AppItem
import com.example.appinsight.core.ui.theme.ProjectTheme
import com.example.appinsight.utils.getImeOrNavigationBarPadding

@NonRestartableComposable
@Composable
fun AppsListSuccess(
    modifier: Modifier = Modifier,
    apps: List<AppItem>,
    onClick: (AppItem) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(bottom = getImeOrNavigationBarPadding())
    ) {
        items(items = apps) { app ->
            AppItemView(
                app = app
            ) {
                onClick(app)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AppListSuccessPreview() {
    ProjectTheme {
        AppsListSuccess(
            apps = listOf(
                AppItem(
                    appName = "Google Maps",
                    packageName = "com.google.android.apps.maps",
                ),
                AppItem(
                    appName = "YouTube",
                    packageName = "com.google.android.youtube",
                ),
                AppItem(
                    appName = "Telegram",
                    packageName = "org.telegram.messenger",
                )
            ),
            onClick = {}
        )
    }
}
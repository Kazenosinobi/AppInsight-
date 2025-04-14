package com.example.appinsight.applicationsList.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appinsight.applicationsList.domain.models.AppItem
import com.example.appinsight.main.ui.theme.ProjectTheme
import com.example.appinsight.utils.debounce

@Composable
fun AppsListSuccess(
    apps: List<AppItem>,
    onClick: (AppItem) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    val debouncedOnClick = remember {
        debounce(
            delayMillis = 100L,
            coroutineScope = coroutineScope,
            useLastParam = true,
            action = onClick
        )
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(apps) { app ->
            AppItemView(
                app = app
            ) {
                debouncedOnClick(app)
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
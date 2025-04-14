package com.example.appinsight.applicationsList.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.example.appinsight.R
import com.example.appinsight.applicationsList.domain.models.AppItem
import com.example.appinsight.core.ui.theme.ProjectTheme
import com.example.appinsight.utils.clickableWithDebounce

@NonRestartableComposable
@Composable
fun AppItemView(
    modifier: Modifier = Modifier,
    app: AppItem,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickableWithDebounce(
                onClick = onClick
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (app.icon != null) {
            Image(
                bitmap = app.icon.toBitmap().asImageBitmap(),
                contentDescription = stringResource(R.string.app_icon_description),
                modifier = Modifier.size(48.dp)
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = stringResource(R.string.placeholder_description),
                modifier = Modifier.size(48.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = app.appName,
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = app.packageName,
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun AppItemViewPreview() {
    ProjectTheme {
        AppItemView(
            app = AppItem(
                appName = "Google Maps",
                packageName = "com.google.android.apps.maps",
            ),
            onClick = {}
        )
    }
}

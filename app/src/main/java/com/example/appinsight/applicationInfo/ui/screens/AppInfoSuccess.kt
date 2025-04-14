package com.example.appinsight.applicationInfo.ui.screens

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.example.appinsight.R
import com.example.appinsight.applicationInfo.domain.models.AppInfo
import com.example.appinsight.core.ui.theme.ProjectTheme

@NonRestartableComposable
@Composable
fun AppInfoSuccess(
    modifier: Modifier = Modifier,
    appInfo: AppInfo,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (appInfo.icon != null) {
                Image(
                    modifier = Modifier.size(64.dp),
                    bitmap = appInfo.icon.toBitmap().asImageBitmap(),
                    contentDescription = stringResource(R.string.app_icon_description),
                )
            } else {
                Image(
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(id = R.drawable.placeholder),
                    contentDescription = stringResource(R.string.placeholder_description),
                    colorFilter = ColorFilter.tint(
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }

            Text(
                text = appInfo.appName,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.primary,
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                InfoRow(title = stringResource(R.string.package_name), value = appInfo.packageName)
                InfoRow(title = stringResource(R.string.version), value = appInfo.version)
                InfoRow(title = stringResource(R.string.sha256), value = appInfo.sha256)
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {
                try {
                    val launchIntent =
                        context.packageManager.getLaunchIntentForPackage(appInfo.packageName)
                    if (launchIntent != null) {
                        context.startActivity(launchIntent)
                    } else {
                        Toast.makeText(
                            context,
                            context.getString(R.string.cannot_launch),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.error_launching, e.message),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
        ) {
            Text(
                text = stringResource(R.string.launch_button_name),
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun AppInfoSuccessPreview() {
    ProjectTheme {
        AppInfoSuccess(
            appInfo = AppInfo(
                appName = "Google Maps",
                packageName = "com.google.android.apps.maps",
                version = "1.0.123",
                sha256 = "124891092847271457657468334836862",
            ),
        )
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AppInfoSuccessPreviewDark() {
    ProjectTheme {
        AppInfoSuccess(
            appInfo = AppInfo(
                appName = "Google Maps",
                packageName = "com.google.android.apps.maps",
                version = "1.0.123",
                sha256 = "124891092847271457657468334836862",
            ),
        )
    }
}
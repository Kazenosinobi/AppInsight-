package com.example.appinsight.applicationInfo.ui.screens

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.example.appinsight.R
import com.example.appinsight.applicationInfo.domain.models.AppInfo
import com.example.appinsight.main.ui.theme.ProjectTheme

@Composable
fun AppInfoSuccess(
    appInfo: AppInfo,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = onClick),
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Back to apps list",
                colorFilter = ColorFilter.tint(
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                text = "Apps list",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (appInfo.icon != null) {
                Image(
                    modifier = Modifier.size(64.dp),
                    bitmap = appInfo.icon.toBitmap().asImageBitmap(),
                    contentDescription = "App icon",
                )
            } else {
                Image(
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(id = R.drawable.placeholder),
                    contentDescription = "Default app icon",
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
                InfoRow(title = "Package name:", value = appInfo.packageName)
                InfoRow(title = "Version:", value = appInfo.version)
                InfoRow(title = "SHA256:", value = appInfo.sha256.take(32) + "...")
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
                            "Cannot launch this application",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, "Error launching app: ${e.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
        ) {
            Text(
                text = "Launch Application",
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AppInfoSuccessPreview() {
    ProjectTheme {
        AppInfoSuccess(
            appInfo = AppInfo(
                appName = "Google Maps",
                packageName = "com.google.android.apps.maps",
                version = "1.0.123",
                sha256 = "124891092847271457657468334836862",
            ),
            onClick = {}
        )
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun AppInfoSuccessPreviewDark() {
    ProjectTheme {
        AppInfoSuccess(
            appInfo = AppInfo(
                appName = "Google Maps",
                packageName = "com.google.android.apps.maps",
                version = "1.0.123",
                sha256 = "124891092847271457657468334836862",
            ),
            onClick = {}
        )
    }
}
package com.example.appinsight.applicationInfo.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import com.example.appinsight.R
import com.example.appinsight.applicationInfo.domain.models.AppInfo

@Composable
fun AppInfoSuccess(
    appInfo: AppInfo,
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
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (appInfo.icon != null) {
                Image(
                    bitmap = appInfo.icon.toBitmap().asImageBitmap(),
                    contentDescription = "App icon",
                    modifier = Modifier.size(64.dp)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.placeholder),
                    contentDescription = "Default app icon",
                    modifier = Modifier.size(64.dp)
                )
            }

            Text(
                text = appInfo.appName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
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
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Launch Application")
        }
    }
}
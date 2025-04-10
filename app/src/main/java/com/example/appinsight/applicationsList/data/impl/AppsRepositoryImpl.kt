package com.example.appinsight.applicationsList.data.impl

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import com.example.appinsight.applicationsList.domain.api.AppsRepository
import com.example.appinsight.applicationsList.domain.models.AppItem

class AppsRepositoryImpl(private val packageManager: PackageManager) : AppsRepository {
    override fun getInstallApps(): List<AppItem> {
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        val apps = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.queryIntentActivities(
                intent,
                PackageManager.ResolveInfoFlags.of(0)
            )
        } else {
            packageManager.queryIntentActivities(intent, 0)
        }

        return apps.mapNotNull { resolveInfo ->
            val packageName = resolveInfo.activityInfo.packageName
            try {
                val appInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    packageManager.getApplicationInfo(
                        packageName,
                        PackageManager.ApplicationInfoFlags.of(0)
                    )
                } else {
                    packageManager.getApplicationInfo(packageName, 0)
                }

                AppItem(
                    appName = packageManager.getApplicationLabel(appInfo).toString(),
                    packageName = packageName,
                    icon = packageManager.getApplicationIcon(appInfo)
                )
            } catch (e: Exception) {
                null
            }
        }
    }
}
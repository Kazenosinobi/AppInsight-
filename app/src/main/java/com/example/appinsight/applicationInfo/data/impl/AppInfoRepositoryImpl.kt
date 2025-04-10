package com.example.appinsight.applicationInfo.data.impl

import android.content.pm.PackageManager
import android.os.Build
import com.example.appinsight.applicationInfo.domain.api.AppInfoRepository
import com.example.appinsight.applicationInfo.domain.models.AppInfo

class AppInfoRepositoryImpl(private val packageManager: PackageManager) : AppInfoRepository {
    override fun getAppInfo(packageName: String): AppInfo {

        return try {
            val appInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                packageManager.getApplicationInfo(
                    packageName,
                    PackageManager.ApplicationInfoFlags.of(0)
                )
            } else {
                packageManager.getApplicationInfo(packageName, 0)
            }

            val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                packageManager.getPackageInfo(
                    packageName,
                    PackageManager.PackageInfoFlags.of(0)
                )
            } else {
                packageManager.getPackageInfo(packageName, 0)
            }

            AppInfo(
                appName = packageManager.getApplicationLabel(appInfo).toString(),
                packageName = packageName,
                version = packageInfo.versionName ?: "N/A",
                versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    packageInfo.longVersionCode
                } else {
                    @Suppress("DEPRECATION")
                    packageInfo.versionCode.toLong()
                },
                icon = packageManager.getApplicationIcon(appInfo)
            )
        } catch (e: Exception) {
            throw RuntimeException("Failed to get app info", e)
        }
    }
}
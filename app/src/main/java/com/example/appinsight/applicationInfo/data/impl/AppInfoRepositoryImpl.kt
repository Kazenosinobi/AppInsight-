package com.example.appinsight.applicationInfo.data.impl

import android.content.pm.PackageManager
import android.os.Build
import com.example.appinsight.applicationInfo.domain.api.AppInfoRepository
import com.example.appinsight.applicationInfo.domain.models.AppInfo
import java.io.File
import java.io.FileInputStream
import java.security.MessageDigest

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

            val appName = packageManager.getApplicationLabel(appInfo).toString()
            val version = packageInfo.versionName ?: "N/A"
            val apkFile = File(appInfo.sourceDir)
            val sha256 = calculateApkSha256(apkFile)
            val icon = packageManager.getApplicationIcon(appInfo)

            AppInfo(
                appName = appName,
                packageName = packageName,
                version = version,
                sha256 = sha256,
                icon = icon
            )
        } catch (e: Exception) {
            throw RuntimeException("Failed to get app info", e)
        }
    }

    private fun calculateApkSha256(file: File): String {
        return try {
            val digest = MessageDigest.getInstance("SHA-256")
            FileInputStream(file).use { fis ->
                val buffer = ByteArray(8192)
                var bytesRead: Int
                while (fis.read(buffer).also { bytesRead = it } != -1) {
                    digest.update(buffer, 0, bytesRead)
                }
            }
            digest.digest().joinToString("") { "%02x".format(it) }
        } catch (e: Exception) {
            "Error calculating SHA-256: ${e.message}"
        }
    }
}
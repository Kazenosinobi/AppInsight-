package com.example.appinsight.applicationInfo.data.impl

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import com.example.appinsight.applicationInfo.domain.api.AppInfoRepository
import com.example.appinsight.applicationInfo.domain.models.AppInfo
import java.io.File
import java.io.FileInputStream
import java.security.MessageDigest

class AppInfoRepositoryImpl(private val packageManager: PackageManager) : AppInfoRepository {
    override fun getAppInfo(packageName: String): AppInfo? = runCatching {
            val appInfo = getApplicationInfo(packageName)

            val packageInfo = getPackageInfo(packageName)

            val appName = packageManager.getApplicationLabel(appInfo).toString()
            val version = packageInfo?.versionName.orEmpty()
            val apkFile = File(appInfo.sourceDir)
            val sha256 = calculateApkSha256(apkFile).orEmpty()
            val icon = packageManager.getApplicationIcon(appInfo)

            AppInfo(
                appName = appName,
                packageName = packageName,
                version = version,
                sha256 = sha256,
                icon = icon
            )
    }.getOrNull()

    private fun getPackageInfo(packageName: String): PackageInfo? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getPackageInfo(
                packageName,
                PackageManager.PackageInfoFlags.of(0)
            )
        } else {
            packageManager.getPackageInfo(packageName, 0)
        }

    private fun getApplicationInfo(packageName: String) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getApplicationInfo(
                packageName,
                PackageManager.ApplicationInfoFlags.of(0)
            )
        } else {
            packageManager.getApplicationInfo(packageName, 0)
        }

    private fun calculateApkSha256(file: File): String? = runCatching {
            val digest = MessageDigest.getInstance(SHA_256)
            FileInputStream(file).use { fis ->
                val buffer = ByteArray(BUFFER_CAPACITY)
                var bytesRead: Int
                while (fis.read(buffer).also { bytesRead = it } != -1) {
                    digest.update(buffer, 0, bytesRead)
                }
            }
            digest.digest().joinToString("") { "%02x".format(it) }
    }.getOrNull()

    private companion object {
        private const val SHA_256 = "SHA-256"
        private const val BUFFER_CAPACITY = 8192
    }
}

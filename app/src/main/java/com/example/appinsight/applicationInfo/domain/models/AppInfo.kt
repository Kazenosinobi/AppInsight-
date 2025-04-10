package com.example.appinsight.applicationInfo.domain.models

import android.graphics.drawable.Drawable

data class AppInfo(
    val appName: String,
    val packageName: String,
    val version: String,
    val versionCode: Long,
    val icon: Drawable? = null
)

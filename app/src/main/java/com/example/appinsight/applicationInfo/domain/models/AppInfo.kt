package com.example.appinsight.applicationInfo.domain.models

import android.graphics.drawable.Drawable

data class AppInfo(
    val appName: String,
    val packageName: String,
    val version: String,
    val sha256: String,
    val icon: Drawable? = null
)

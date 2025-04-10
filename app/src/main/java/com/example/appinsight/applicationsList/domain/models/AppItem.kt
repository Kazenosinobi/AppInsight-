package com.example.appinsight.applicationsList.domain.models

import android.graphics.drawable.Drawable

data class AppItem(
    val appName: String,
    val packageName: String,
    val icon: Drawable? = null
)

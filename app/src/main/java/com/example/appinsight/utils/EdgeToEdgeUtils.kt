package com.example.appinsight.utils

import android.os.Build
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp

fun Modifier.statusBarHeightEdgeToEdge() = composed {
    conditional(isEdgeToEdgeNeedsToBeHandled()) { statusBarsPadding() }
}

fun Modifier.navigationBarHeightEdgeToEdge() = composed {
    conditional(isEdgeToEdgeNeedsToBeHandled()) { navigationBarsPadding() }
}

@Composable
fun getImeOrNavigationBarPadding() = if (isEdgeToEdgeNeedsToBeHandled()) {
    val imeBottomPadding = WindowInsets.ime.asPaddingValues().calculateBottomPadding()
    if (imeBottomPadding > 0.dp) {
        imeBottomPadding
    } else {
        WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()
    }
} else {
    0.dp
}

fun isEdgeToEdgeNeedsToBeHandled() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

@Composable
private fun Modifier.conditional(
    condition: Boolean,
    modifier: @Composable Modifier.() -> Modifier
): Modifier {
    return if (condition) {
        modifier.invoke(this)
    } else {
        this
    }
}
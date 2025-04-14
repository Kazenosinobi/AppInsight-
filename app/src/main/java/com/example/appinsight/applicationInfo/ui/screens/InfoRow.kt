package com.example.appinsight.applicationInfo.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appinsight.main.ui.theme.ProjectTheme

@Composable
fun InfoRow(title: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.width(120.dp),
            text = title,
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun InfoRowPreview() {
    ProjectTheme {
        InfoRow(
            title = "Title",
            value = "value"
        )
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun InfoRowPreviewDark() {
    ProjectTheme {
        InfoRow(
            title = "Title",
            value = "value"
        )
    }
}
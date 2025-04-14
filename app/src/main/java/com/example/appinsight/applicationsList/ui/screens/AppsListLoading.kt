package com.example.appinsight.applicationsList.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appinsight.main.ui.theme.ProjectTheme

@Composable
fun LoadingView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(64.dp),
            strokeWidth = 4.dp,
            color = MaterialTheme.colorScheme.surface,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Loading apps...",
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
@Preview(showBackground = true)
fun LoadingViewPreview() {
    ProjectTheme {
        LoadingView()
    }
}
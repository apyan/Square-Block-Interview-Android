package com.example.squareinterview.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.squareinterview.R

@Composable
fun LoadingScreen() {
    Column(
        verticalArrangement= Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(100.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
            strokeWidth = 7.dp
        )

        Text(
            modifier = Modifier
                .padding(vertical = 100.dp)
                .fillMaxWidth(),
            text = stringResource(id = R.string.status_loading),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.DarkGray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    LoadingScreen()
}
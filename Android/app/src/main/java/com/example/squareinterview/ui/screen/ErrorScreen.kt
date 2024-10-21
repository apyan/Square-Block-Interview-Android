package com.example.squareinterview.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import com.example.squareinterview.viewmodel.LoadingStatus

@Composable
fun ErrorScreen(
    loadingStatus: LoadingStatus = LoadingStatus.EMPTY_LIST,
    retrySearch: () -> Unit = {}
) {
    Column(
        verticalArrangement= Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 100.dp)
                .fillMaxWidth(),
            text =
                when (loadingStatus) {
                    LoadingStatus.NO_CONNECTION -> stringResource(id = R.string.status_no_connection)
                    LoadingStatus.MALFORMED_LIST -> stringResource(id = R.string.status_malformed)
                    else -> stringResource(id = R.string.status_empty)
                },
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.DarkGray
        )

        Button(
            modifier = Modifier.padding(horizontal = 75.dp),
            onClick = { retrySearch.invoke() }
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.retry),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen()
}
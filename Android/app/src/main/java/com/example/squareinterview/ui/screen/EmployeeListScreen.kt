package com.example.squareinterview.ui.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.squareinterview.R
import com.example.squareinterview.model.response.Employee

@Composable
fun EmployeeListScreen(
    context: Context? = null,
    employeeList: List<Employee> = emptyList(),
    refreshSearch: () -> Unit = {},
    sortName: () -> Unit = {},
    sortTeam: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            Row (
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier.padding(2.dp),
                    onClick = { refreshSearch.invoke() }
                ) {
                    Text(
                        text = stringResource(id = R.string.refresh),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )
                }

                Text(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .align(Alignment.CenterVertically),
                    text = stringResource(id = R.string.sort_by),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )

                Button(
                    modifier = Modifier.padding(2.dp),
                    onClick = { sortName.invoke() }
                ) {
                    Text(
                        text = stringResource(id = R.string.sort_name),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )
                }

                Button(
                    modifier = Modifier.padding(2.dp),
                    onClick = { sortTeam.invoke() }
                ) {
                    Text(
                        text = stringResource(id = R.string.sort_team),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            items(employeeList) { employee ->
                EmployeeInfoItem(
                    context = context,
                    employee = employee
                )
            }
        }
    }
}

@Composable
fun EmployeeInfoItem(
    context: Context?,
    employee: Employee
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Color.LightGray),
        shape = MaterialTheme.shapes.medium,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val imageRequest = context?.let {
                ImageRequest.Builder(it)
                    .data(employee.photoUrlSmall)
                    .memoryCacheKey(employee.photoUrlSmall)
                    .diskCacheKey(employee.photoUrlSmall)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .fallback(R.drawable.ic_launcher_background)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .build()
            }

            AsyncImage(
                model = imageRequest,
                contentDescription = "Employee Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(75.dp)
                    .padding(10.dp),
            )

            Column(Modifier.padding(5.dp)) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 2.dp),
                    text = employee.fullName,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 2.dp),
                    text = employee.team,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 2.dp),
                    text = employee.emailAddress,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 2.dp),
                    text = employee.phoneNumber,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmployeeListScreenPreview() {
    EmployeeListScreen()
}
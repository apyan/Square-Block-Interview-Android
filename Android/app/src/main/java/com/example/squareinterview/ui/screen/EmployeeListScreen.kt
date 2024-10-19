package com.example.squareinterview.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.squareinterview.viewmodel.EmployeeListViewModel

@Composable
fun EmployeeListScreen() {
    val viewModel: EmployeeListViewModel = viewModel()

    if (viewModel.employeeList.value.isNotEmpty()) {
        //ComicBookDetailScreen(comicBookInfoResult = viewModel.comicBookList.value[0])
            Text(
                text = viewModel.employeeList.value.toString(),
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Green,
                modifier = Modifier
                    .background(Color.Black)
                    .padding(horizontal = 8.dp, vertical = 10.dp)
            )
    } else {
        LoadingSpinnerScreen()
    }
}

//@Composable
//fun ComicBookDetailScreen(comicBookInfoResult: ComicBookInfoResult) {
//    Column(
//        verticalArrangement= Arrangement.SpaceBetween
//    ) {
//        Column(
//            modifier = Modifier
//                .verticalScroll(rememberScrollState())
//                .weight(weight = 1f, fill = false)
//                .fillMaxWidth()
//        ) {
//            AsyncImage(
//                model = comicBookInfoResult.thumbnail.getImageUrl(),
//                contentDescription = "ComicBook Thumbnail Cover",
//                contentScale = ContentScale.FillWidth,
//                modifier = Modifier
//                    .fillMaxWidth()
//            )
//            Text(
//                text = comicBookInfoResult.title,
//                style = MaterialTheme.typography.headlineMedium,
//                color = Color.White,
//                modifier = Modifier
//                    .background(Color.Black)
//                    .padding(horizontal = 8.dp, vertical = 10.dp)
//            )
//            Text(
//                text = comicBookInfoResult.description,
//                style = MaterialTheme.typography.bodyLarge,
//                color = Color.White,
//                modifier = Modifier
//                    .background(Color.DarkGray)
//                    .padding(horizontal = 8.dp, vertical = 5.dp)
//            )
//        }
//    }
//}

@Composable
fun LoadingSpinnerScreen() {
    Surface(
        color = Color.LightGray,
        modifier = Modifier
            .fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(50.dp)
                .size(30.dp)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
            strokeWidth = 7.dp
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ScreenPreview() {
//    //ComicBookInfoScreen()
//    LoadingSpinnerScreen()
//}
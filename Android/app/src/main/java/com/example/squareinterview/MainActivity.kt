package com.example.squareinterview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.squareinterview.model.response.Employee
import com.example.squareinterview.ui.screen.EmployeeListScreen
import com.example.squareinterview.ui.screen.ErrorScreen
import com.example.squareinterview.ui.screen.LoadingScreen
import com.example.squareinterview.ui.theme.SquareInterviewTheme
import com.example.squareinterview.viewmodel.EmployeeListViewModel
import com.example.squareinterview.viewmodel.LoadingStatus

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SquareInterviewTheme {
                val viewModel: EmployeeListViewModel = viewModel()
                val employeeListing = remember { mutableStateListOf<Employee>() }

                when (viewModel.loadingStatus.value) {
                    LoadingStatus.INITIAL -> {
                        viewModel.launchEmployeeSearch(this)
                    }
                    LoadingStatus.DOWNLOADING -> {
                        LoadingScreen()
                    }
                    LoadingStatus.COMPLETED_LIST -> {
                        employeeListing.addAll(viewModel.employeeList.value)
                        EmployeeListScreen(
                            context = this,
                            employeeList = employeeListing,
                            refreshSearch = {
                                employeeListing.clear()
                                viewModel.launchEmployeeSearch(this)
                            },
                            sortName = {
                                employeeListing.clear()
                                employeeListing.addAll(viewModel.sortNameEmployees())
                            },
                            sortTeam = {
                                employeeListing.clear()
                                employeeListing.addAll(viewModel.sortTeamEmployees())
                            }
                        )
                    }
                    LoadingStatus.EMPTY_LIST,
                    LoadingStatus.MALFORMED_LIST,
                    LoadingStatus.NO_CONNECTION -> {
                        ErrorScreen(
                            loadingStatus = viewModel.loadingStatus.value,
                            retrySearch = {
                                employeeListing.clear()
                                viewModel.launchEmployeeSearch(this)
                            }
                        )
                    }
                }
            }
        }
    }
}
package com.example.squareinterview.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.squareinterview.model.EmployeeListRepository
import com.example.squareinterview.model.response.Employee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeListViewModel(
    private val employeeListRepository: EmployeeListRepository = EmployeeListRepository()
) : ViewModel() {

    val employeeList: MutableState<List<Employee>> = mutableStateOf(emptyList())
    val loadingStatus: MutableState<LoadingStatus> = mutableStateOf(LoadingStatus.INITIAL)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getEmployeeListInfo()
        }
    }

    private suspend fun getEmployeeListInfo() {
        try {
            loadingStatus.value = LoadingStatus.DOWNLOADING
            val employeeListResult = employeeListRepository.loadEmployeeListInfo().employees
            employeeList.value = employeeListResult
        } catch (error: Error) {
            employeeList.value = emptyList()
        }
    }

    private suspend fun getMalformedEmployeeListInfo() {
        try {
            loadingStatus.value = LoadingStatus.DOWNLOADING
            val employeeListResult = employeeListRepository.loadMalformedEmployeeListInfo().employees
            employeeList.value = employeeListResult
        } catch (error: Error) {
            employeeList.value = emptyList()
        }
    }

    private suspend fun getEmptyEmployeeListInfo() {
        try {
            loadingStatus.value = LoadingStatus.DOWNLOADING
            val employeeListResult = employeeListRepository.loadEmptyEmployeeListInfo().employees
            employeeList.value = employeeListResult
        } catch (error: Error) {
            employeeList.value = emptyList()
        }
    }
}

enum class LoadingStatus {
    INITIAL,
    NO_CONNECTION,
    DOWNLOADING,
    COMPLETED_LIST,
    MALFORMED_LIST,
    EMPTY_LIST
}
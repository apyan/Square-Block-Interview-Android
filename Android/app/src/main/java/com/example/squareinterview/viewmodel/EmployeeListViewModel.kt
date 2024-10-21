package com.example.squareinterview.viewmodel

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.squareinterview.core.Constant
import com.example.squareinterview.model.EmployeeListRepository
import com.example.squareinterview.model.response.Employee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeListViewModel(
    private val employeeListRepository: EmployeeListRepository = EmployeeListRepository()
) : ViewModel() {

    val employeeList: MutableState<List<Employee>> = mutableStateOf(emptyList())
    val loadingStatus: MutableState<LoadingStatus> = mutableStateOf(LoadingStatus.INITIAL)

    fun launchEmployeeSearch(context: Context) {
        if (Constant.isConnected(context)) {
            viewModelScope.launch(Dispatchers.IO) {
                getEmployeeListInfo()
                //getMalformedEmployeeListInfo()
                //getEmptyEmployeeListInfo()
            }
        } else {
            setLoadingStatus(LoadingStatus.NO_CONNECTION)
        }
    }

    private suspend fun getEmployeeListInfo() {
        try {
            setLoadingStatus(LoadingStatus.DOWNLOADING)
            val employeeListResult = employeeListRepository.loadEmployeeListInfo().employees
            employeeList.value = employeeListResult
            setLoadingStatus(LoadingStatus.COMPLETED_LIST)
        } catch (error: Error) {
            employeeList.value = emptyList()
            setLoadingStatus(LoadingStatus.EMPTY_LIST)
        }
    }

    private suspend fun getMalformedEmployeeListInfo() {
        try {
            setLoadingStatus(LoadingStatus.DOWNLOADING)
            val employeeListResult = employeeListRepository.loadMalformedEmployeeListInfo().employees
            employeeList.value = employeeListResult
            setLoadingStatus(LoadingStatus.MALFORMED_LIST)
        } catch (error: Error) {
            employeeList.value = emptyList()
            setLoadingStatus(LoadingStatus.MALFORMED_LIST)
        }
    }

    private suspend fun getEmptyEmployeeListInfo() {
        try {
            setLoadingStatus(LoadingStatus.DOWNLOADING)
            val employeeListResult = employeeListRepository.loadEmptyEmployeeListInfo().employees
            employeeList.value = employeeListResult
            setLoadingStatus(LoadingStatus.EMPTY_LIST)
        } catch (error: Error) {
            employeeList.value = emptyList()
            setLoadingStatus(LoadingStatus.EMPTY_LIST)
        }
    }

    private fun setLoadingStatus(status: LoadingStatus) {
        loadingStatus.value = status
    }

    fun sortNameEmployees(): List<Employee> {
        return employeeList.value.sortedBy { it.fullName }
    }

    fun sortTeamEmployees(): List<Employee> {
        return employeeList.value.sortedBy { it.team }
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
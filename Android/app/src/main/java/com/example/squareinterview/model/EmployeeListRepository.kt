package com.example.squareinterview.model

import com.example.squareinterview.model.response.EmployeeListResponse

class EmployeeListRepository(
    private val employeeListService: EmployeeListService = EmployeeListService()
) {
    suspend fun loadEmployeeListInfo(): EmployeeListResponse {
        return employeeListService.loadEmployeeListInfo()
    }

    suspend fun loadMalformedEmployeeListInfo(): EmployeeListResponse {
        return employeeListService.loadMalformedEmployeeListInfo()
    }

    suspend fun loadEmptyEmployeeListInfo(): EmployeeListResponse {
        return employeeListService.loadEmptyEmployeeListInfo()
    }
}
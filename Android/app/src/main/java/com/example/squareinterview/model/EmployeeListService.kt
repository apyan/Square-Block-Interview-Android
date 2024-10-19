package com.example.squareinterview.model

import com.example.squareinterview.core.Constant
import com.example.squareinterview.model.response.EmployeeListResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class EmployeeListService {

    private var employeeListApi: EmployeeListApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        employeeListApi = retrofit.create(EmployeeListApi::class.java)
    }

    suspend fun loadEmployeeListInfo(): EmployeeListResponse {
        return employeeListApi.loadEmployeeListInfo()
    }

    suspend fun loadMalformedEmployeeListInfo(): EmployeeListResponse {
        return employeeListApi.loadMalformedEmployeeListInfo()
    }

    suspend fun loadEmptyEmployeeListInfo(): EmployeeListResponse {
        return employeeListApi.loadEmptyEmployeeListInfo()
    }

    interface EmployeeListApi {
        @GET("employees.json")
        suspend fun loadEmployeeListInfo(): EmployeeListResponse

        @GET("employees_malformed.json")
        suspend fun loadMalformedEmployeeListInfo(): EmployeeListResponse

        @GET("employees_empty.json")
        suspend fun loadEmptyEmployeeListInfo(): EmployeeListResponse
    }
}
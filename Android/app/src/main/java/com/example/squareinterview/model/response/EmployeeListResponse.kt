package com.example.squareinterview.model.response

import com.google.gson.annotations.SerializedName

data class EmployeeListResponse(
    @SerializedName("employees")
    val employees: List<Employee>
)

data class Employee(
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("email_address")
    val emailAddress: String,
    @SerializedName("biography")
    val biography: String,
    @SerializedName("photo_url_small")
    val photoUrlSmall: String,
    @SerializedName("photo_url_large")
    val photoUrlLarge: String,
    @SerializedName("team")
    val team: String,
    @SerializedName("employee_type")
    val employeeType: String
)
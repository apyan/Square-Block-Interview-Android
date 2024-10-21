package com.example.squareinterview

import com.example.squareinterview.model.response.Employee
import com.example.squareinterview.viewmodel.EmployeeListViewModel
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class EmployeeListViewModelUnitTest {

    private lateinit var employeeListViewModel: EmployeeListViewModel

    val employee_0 = Employee(
        uuid = "",
        fullName = "Alice",
        phoneNumber = "",
        emailAddress = "",
        biography = "",
        photoUrlSmall = "",
        photoUrlLarge = "",
        team = "C",
        employeeType = ""
    )

    val employee_1 = Employee(
        uuid = "",
        fullName = "Jake",
        phoneNumber = "",
        emailAddress = "",
        biography = "",
        photoUrlSmall = "",
        photoUrlLarge = "",
        team = "B",
        employeeType = ""
    )

    val employee_2 = Employee(
        uuid = "",
        fullName = "Bob",
        phoneNumber = "",
        emailAddress = "",
        biography = "",
        photoUrlSmall = "",
        photoUrlLarge = "",
        team = "A",
        employeeType = ""
    )

    @Before
    fun setup() {
        employeeListViewModel = EmployeeListViewModel()
    }

    @Test
    fun check_user_name_sorting() {
        val secondName = "Bob"

        employeeListViewModel.employeeList.value = listOf(employee_2, employee_1, employee_0)
        assertThat(employeeListViewModel.sortNameEmployees()[1].fullName == secondName).isTrue()
    }

    @Test
    fun check_user_team_sorting() {
        val secondName = "B"

        employeeListViewModel.employeeList.value = listOf(employee_2, employee_1, employee_0)
        assertThat(employeeListViewModel.sortTeamEmployees()[1].fullName == secondName).isTrue()
    }
}
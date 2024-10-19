package com.example.squareinterview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.squareinterview.ui.screen.EmployeeListScreen
import com.example.squareinterview.ui.theme.SquareInterviewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SquareInterviewTheme {
                EmployeeListScreen()
            }
        }
    }
}
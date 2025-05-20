package com.example.taskora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.taskora.navigation.AppNavGraph
import com.example.taskora.ui.theme.TaskoraTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskoraTheme {
                val navController = rememberNavController()
                AppNavGraph(navController)
            }
        }
    }
}
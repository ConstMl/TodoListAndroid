package com.task.planner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import com.task.planner.presentation.screens.main.TaskApp
import com.task.planner.presentation.screens.task_list_screen.TaskListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskApp { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    TaskListScreen()
                }
            }
        }
    }
}

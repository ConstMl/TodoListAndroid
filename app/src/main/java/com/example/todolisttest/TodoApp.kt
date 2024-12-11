package com.example.todolisttest

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.todolisttest.presentation.screens.todo_list_screen.TodoListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TodoApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(stringResource(id = R.string.app_bar_name))
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.secondaryContainer
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            TodoListScreen()
        }
    }
}

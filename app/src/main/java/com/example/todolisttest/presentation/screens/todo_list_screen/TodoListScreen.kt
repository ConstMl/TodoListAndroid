package com.example.todolisttest.presentation.screens.todo_list_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolisttest.presentation.theme.OverlappingHeight
import com.example.todolisttest.presentation.widget.CustomCircularProgressIndicator

@Composable
internal fun TodoListScreen() {
    val viewModel = hiltViewModel<TodoListViewModel>()
    val state by viewModel.state.collectAsState()
    if (state.isLoading) {
        CustomCircularProgressIndicator(modifier = Modifier.fillMaxSize())
    } else {
        TodoItemsContainer(
            todoItems = state.taskList,
            onItemClick = viewModel::onItemClick,
            onItemDelete = viewModel::onItemRemove,
            overlappingElementsHeight = OverlappingHeight
        )
    }
}

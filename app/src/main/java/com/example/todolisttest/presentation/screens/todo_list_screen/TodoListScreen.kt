package com.example.todolisttest.presentation.screens.todo_list_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolisttest.presentation.theme.OverlappingHeight

@Composable
internal fun TodoListScreen() {
    val viewModel = hiltViewModel<TodoListViewModel>()
    val state by viewModel.state.collectAsState()
    TodoItemsContainer(
        todoItems = state.taskList,
        onItemClick = viewModel::onItemClick,
        onItemDelete = viewModel::onItemRemove,
        overlappingElementsHeight = OverlappingHeight
    )
}

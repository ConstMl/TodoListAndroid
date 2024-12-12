package com.example.todolisttest.presentation.screens.todo_list_screen

import com.example.todolisttest.domain.model.TaskListItemModel

data class TodoListState(
    val isLoading: Boolean = false,
    val taskList: List<TaskListItemModel> = emptyList(),
    val isShowMultiSelectionPanel: Boolean = false,
    val isItemsMultiSelected: Boolean = false,
    val hasError: Boolean = false,
    val errorText: String? = null
) {
    val multiSelectionIsNotEmpty get() = taskList.any { it.multiSelected }
}

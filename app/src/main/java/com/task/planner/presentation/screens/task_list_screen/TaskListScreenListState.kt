package com.task.planner.presentation.screens.task_list_screen

import com.task.planner.domain.model.TaskListItemModel

data class TaskListScreenListState(
    val isLoading: Boolean = false,
    val taskList: List<TaskListItemModel> = emptyList(),
    val isShowMultiSelectionPanel: Boolean = false,
    val isItemsMultiSelected: Boolean = false,
    val hasError: Boolean = false,
    val errorText: String? = null
) {
    val multiSelectionIsNotEmpty get() = taskList.any { it.multiSelected }
}

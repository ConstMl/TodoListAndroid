package com.task.planner.domain.model

import com.task.planner.data.datasources.response.TaskItemResponse

data class TaskListItemModel(
    val id: Int,
    val title: String,
    val completed: Boolean = false,
    val multiSelected: Boolean = false
) {
    companion object {
        fun from(item: TaskItemResponse) = TaskListItemModel(
            id = item.id,
            title = item.todo,
            completed = item.completed
        )
    }
}

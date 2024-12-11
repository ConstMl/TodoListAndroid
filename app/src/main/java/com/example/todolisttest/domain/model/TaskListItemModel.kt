package com.example.todolisttest.domain.model

import com.example.todolisttest.data.datasources.response.TodoItemResponse

data class TaskListItemModel(
    val id: Int,
    val title: String,
    val completed: Boolean = false
) {
    companion object {
        fun from(item: TodoItemResponse) = TaskListItemModel(
            id = item.id,
            title = item.todo,
            completed = item.completed
        )
    }
}

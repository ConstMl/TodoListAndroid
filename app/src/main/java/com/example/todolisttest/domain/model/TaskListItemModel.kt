package com.example.todolisttest.domain.model

import java.util.UUID

data class TaskListItemModel(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val isDone: Boolean = false
)

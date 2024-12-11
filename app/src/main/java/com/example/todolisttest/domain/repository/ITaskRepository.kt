package com.example.todolisttest.domain.repository

import com.example.todolisttest.data.cmmon.ClientResult
import com.example.todolisttest.domain.model.TaskListItemModel

interface ITaskRepository {
    suspend fun get(): ClientResult<List<TaskListItemModel>>
    suspend fun remoteGet(): ClientResult<List<TaskListItemModel>>
}

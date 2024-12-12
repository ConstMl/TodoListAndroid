package com.task.planner.domain.repository

import com.task.planner.data.cmmon.ClientResult
import com.task.planner.domain.model.TaskListItemModel

interface ITaskRepository {
    suspend fun get(): ClientResult<List<TaskListItemModel>>
    suspend fun remoteGet(): ClientResult<List<TaskListItemModel>>
}

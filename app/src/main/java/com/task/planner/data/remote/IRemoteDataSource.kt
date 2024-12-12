package com.task.planner.data.remote

import com.task.planner.data.cmmon.ClientResult
import com.task.planner.domain.model.TaskListItemModel

interface IRemoteDataSource {
    suspend fun getAllTask(): ClientResult<List<TaskListItemModel>>
}

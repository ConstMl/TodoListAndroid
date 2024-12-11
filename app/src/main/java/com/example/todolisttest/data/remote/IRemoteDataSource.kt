package com.example.todolisttest.data.remote

import com.example.todolisttest.data.cmmon.ClientResult
import com.example.todolisttest.domain.model.TaskListItemModel

interface IRemoteDataSource {
    suspend fun getAllTask(): ClientResult<List<TaskListItemModel>>
}

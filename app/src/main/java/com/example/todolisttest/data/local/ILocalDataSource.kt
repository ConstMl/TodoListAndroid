package com.example.todolisttest.data.local

import com.example.todolisttest.data.cmmon.ClientResult
import com.example.todolisttest.domain.model.TaskListItemModel

interface ILocalDataSource {
    suspend fun getAllTask(): ClientResult<List<TaskListItemModel>>
}

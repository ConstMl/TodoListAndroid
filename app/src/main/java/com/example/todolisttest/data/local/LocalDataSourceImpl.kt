package com.example.todolisttest.data.local

import com.example.todolisttest.data.cmmon.ClientResult
import com.example.todolisttest.domain.model.TaskListGenerator
import com.example.todolisttest.domain.model.TaskListItemModel

class LocalDataSourceImpl : ILocalDataSource {
    override suspend fun getAllTask(): ClientResult<List<TaskListItemModel>> {
        // todo: return data from store
        val data = TaskListGenerator.testList10Items
        val result = ClientResult.Success(data)
        return result
    }
}

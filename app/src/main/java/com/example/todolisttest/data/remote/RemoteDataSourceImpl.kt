package com.example.todolisttest.data.remote

import com.example.todolisttest.data.cmmon.ClientResult
import com.example.todolisttest.domain.model.TaskListGenerator
import com.example.todolisttest.domain.model.TaskListItemModel

class RemoteDataSourceImpl : IRemoteDataSource {
    override suspend fun getAllTask(): ClientResult<List<TaskListItemModel>> {
        // todo: return data remote data
        val data = TaskListGenerator.testList10Items
        val result = ClientResult.Success(data)
        return result
    }
}

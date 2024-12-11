package com.example.todolisttest.data.remote

import com.example.todolisttest.data.cmmon.ClientResult
import com.example.todolisttest.data.cmmon.DataSourceException
import com.example.todolisttest.data.datasources.APIService
import com.example.todolisttest.domain.model.TaskListItemModel

class RemoteDataSourceImpl(
    private val apiService: APIService
) : IRemoteDataSource {
    override suspend fun getAllTask(): ClientResult<List<TaskListItemModel>> {
        val response = apiService.getTodo(DEFAULT_DATA_SIZE)
        return if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                val list = body.todos.map { TaskListItemModel.from(it) }
                ClientResult.Success(list)
            } else {
                ClientResult.Error(DataSourceException.Message("body is null"))
            }
        } else {
            ClientResult.Error(DataSourceException.Server("server error"))
        }
        // todo: return data remote data
//        val data = TaskListGenerator.testList10Items
//        val result = ClientResult.Success(data)
//        return result
    }

    companion object {
        private const val DEFAULT_DATA_SIZE = 30
    }
}

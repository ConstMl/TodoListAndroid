package com.task.planner.data.remote

import com.task.planner.data.cmmon.ClientResult
import com.task.planner.data.cmmon.map
import com.task.planner.data.datasources.APIService
import com.task.planner.data.datasources.callClientResult
import com.task.planner.domain.model.TaskListItemModel

class RemoteDataSourceImpl(
    private val apiService: APIService
) : IRemoteDataSource {
    override suspend fun getAllTask(): ClientResult<List<TaskListItemModel>> {
        return callClientResult {
            apiService.getTasks(DEFAULT_DATA_SIZE)
        }.map { response ->
            response.todos.map { TaskListItemModel.from(it) }
        }
    }

    companion object {
        private const val DEFAULT_DATA_SIZE = 30
    }
}

package com.task.planner.data.remote

import com.task.planner.R
import com.task.planner.data.cmmon.ClientResult
import com.task.planner.data.cmmon.DataSourceException
import com.task.planner.data.datasources.APIService
import com.task.planner.domain.model.TaskListItemModel

class RemoteDataSourceImpl(
    private val apiService: APIService
) : IRemoteDataSource {
    override suspend fun getAllTask(): ClientResult<List<TaskListItemModel>> {
        val response = apiService.getTasks(DEFAULT_DATA_SIZE)
        return if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                val list = body.todos.map { TaskListItemModel.from(it) }
                ClientResult.Success(list)
            } else {
                ClientResult.Error(DataSourceException.MessageRes(R.string.error_server_body_null))
            }
        } else {
            ClientResult.Error(
                DataSourceException.Server(
                    id = R.string.error_server,
                    error = response.message()
                )
            )
        }
    }

    companion object {
        private const val DEFAULT_DATA_SIZE = 30
    }
}

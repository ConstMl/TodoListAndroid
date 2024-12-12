package com.task.planner.data.remote

import com.task.planner.R
import com.task.planner.data.cmmon.ClientResult
import com.task.planner.data.cmmon.DataSourceException
import com.task.planner.data.datasources.APIService
import com.task.planner.data.datasources.NoConnectivityException
import com.task.planner.domain.model.TaskListItemModel

class RemoteDataSourceImpl(
    private val apiService: APIService
) : IRemoteDataSource {
    override suspend fun getAllTask(): ClientResult<List<TaskListItemModel>> {
        try {
            val response = apiService.getTasks(DEFAULT_DATA_SIZE)
            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    val list = body.todos.map { TaskListItemModel.from(it) }
                    ClientResult.Success(list)
                } else {
                    // todo: fix string
                    ClientResult.Error(DataSourceException.Message("Server success, but body is null"))
                }
            } else {
                ClientResult.Error(
                    DataSourceException.Server(response.message())
                )
            }
        } catch (error: NoConnectivityException) {
            return ClientResult.Error(
                DataSourceException.Server(error.message)
            )
        } catch (error: Exception) {
            return ClientResult.Error(
                DataSourceException.Server(error.message.toString())
            )
        }
    }

    companion object {
        private const val DEFAULT_DATA_SIZE = 30
    }
}

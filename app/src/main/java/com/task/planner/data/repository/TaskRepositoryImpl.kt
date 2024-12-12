package com.task.planner.data.repository

import com.task.planner.data.cmmon.ClientResult
import com.task.planner.data.local.ILocalDataSource
import com.task.planner.data.remote.IRemoteDataSource
import com.task.planner.domain.model.TaskListItemModel
import com.task.planner.domain.repository.ITaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val remoteDataSource: IRemoteDataSource,
    private val localDataSource: ILocalDataSource
) : ITaskRepository {
    override suspend fun get(): ClientResult<List<TaskListItemModel>> {

        /**
         * todo:
         *  проверить локальный репозиторий
         *  если он не пустой, то вернуть данные из него
         *  если репозиторий пустой, то загрузить данные из сети
         *  сохранить данные локально
         *  вернуть данные
         * */

        return localDataSource.getAllTask()
    }

    override suspend fun remoteGet(): ClientResult<List<TaskListItemModel>> {

        /**
         * todo
         *  загрузить данные из сети
         *  сохранить сразу их локально
         *  вернуть данные
         * */

        return remoteDataSource.getAllTask()
    }
}

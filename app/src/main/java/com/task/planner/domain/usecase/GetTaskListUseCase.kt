package com.task.planner.domain.usecase

import com.task.planner.data.cmmon.ClientResult
import com.task.planner.domain.model.TaskListItemModel
import com.task.planner.domain.repository.ITaskRepository
import javax.inject.Inject

class GetTaskListUseCase @Inject constructor(
    private val taskRepository: ITaskRepository
) : UseCase<String, List<TaskListItemModel>>() {
    override suspend fun call(param: String): ClientResult<List<TaskListItemModel>> {
        return taskRepository.get()
    }
}

package com.example.todolisttest.domain.usecase

import com.example.todolisttest.data.cmmon.ClientResult
import com.example.todolisttest.domain.model.TaskListItemModel
import com.example.todolisttest.domain.repository.ITaskRepository
import javax.inject.Inject

class GetTaskListUseCase @Inject constructor(
    private val taskRepository: ITaskRepository
) : UseCase<String, List<TaskListItemModel>>() {
    override suspend fun call(param: String): ClientResult<List<TaskListItemModel>> {
        return taskRepository.get()
    }
}

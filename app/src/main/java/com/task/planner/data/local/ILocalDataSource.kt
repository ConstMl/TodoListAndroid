package com.task.planner.data.local

import com.task.planner.data.cmmon.ClientResult
import com.task.planner.domain.model.TaskListItemModel
import com.task.planner.presentation.theme.ThemeMode

interface ILocalDataSource {
    suspend fun getAllTask(): ClientResult<List<TaskListItemModel>>
    suspend fun storeThemeMode(mode: ThemeMode)
    suspend fun getThemeMode(): ThemeMode
}

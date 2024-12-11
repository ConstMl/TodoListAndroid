package com.example.todolisttest.data.local

import com.example.todolisttest.data.cmmon.ClientResult
import com.example.todolisttest.domain.model.TaskListItemModel
import com.example.todolisttest.presentation.theme.ThemeMode

interface ILocalDataSource {
    suspend fun getAllTask(): ClientResult<List<TaskListItemModel>>
    suspend fun storeThemeMode(mode: ThemeMode)
    suspend fun getThemeMode(): ThemeMode
}

package com.task.planner.domain.repository

import com.task.planner.presentation.theme.ThemeMode

interface IStorageRepository {
    suspend fun storeThemeMode(mode: ThemeMode)
    suspend fun getThemeMode(): ThemeMode
}

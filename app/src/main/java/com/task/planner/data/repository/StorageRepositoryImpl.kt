package com.task.planner.data.repository

import com.task.planner.data.local.ILocalDataSource
import com.task.planner.domain.repository.IStorageRepository
import com.task.planner.presentation.theme.ThemeMode
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val localDataSource: ILocalDataSource
) : IStorageRepository {
    override suspend fun storeThemeMode(mode: ThemeMode) {
        localDataSource.storeThemeMode(mode)
    }

    override suspend fun getThemeMode(): ThemeMode {
        return localDataSource.getThemeMode()
    }
}

package com.example.todolisttest.data.repository

import com.example.todolisttest.data.local.ILocalDataSource
import com.example.todolisttest.domain.repository.IStorageRepository
import com.example.todolisttest.presentation.theme.ThemeMode
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

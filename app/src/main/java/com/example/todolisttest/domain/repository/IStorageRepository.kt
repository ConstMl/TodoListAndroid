package com.example.todolisttest.domain.repository

import com.example.todolisttest.presentation.theme.ThemeMode

interface IStorageRepository {
    suspend fun storeThemeMode(mode: ThemeMode)
    suspend fun getThemeMode(): ThemeMode
}

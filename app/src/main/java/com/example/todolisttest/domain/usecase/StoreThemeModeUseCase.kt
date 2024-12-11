package com.example.todolisttest.domain.usecase

import com.example.todolisttest.data.cmmon.ClientResult
import com.example.todolisttest.domain.repository.IStorageRepository
import com.example.todolisttest.presentation.theme.ThemeMode
import javax.inject.Inject

class StoreThemeModeUseCase @Inject constructor(
    private val storageRepository: IStorageRepository
) : UseCase<ThemeMode, Unit>() {
    override suspend fun call(param: ThemeMode): ClientResult<Unit> {
        storageRepository.storeThemeMode(param)
        return ClientResult.Success(Unit)
    }
}

package com.task.planner.domain.usecase

import com.task.planner.data.cmmon.ClientResult
import com.task.planner.domain.repository.IStorageRepository
import com.task.planner.presentation.theme.ThemeMode
import javax.inject.Inject

class StoreThemeModeUseCase @Inject constructor(
    private val storageRepository: IStorageRepository
) : UseCase<ThemeMode, Unit>() {
    override suspend fun call(param: ThemeMode): ClientResult<Unit> {
        storageRepository.storeThemeMode(param)
        return ClientResult.Success(Unit)
    }
}

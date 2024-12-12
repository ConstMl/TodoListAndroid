package com.task.planner.domain.usecase

import com.task.planner.data.cmmon.ClientResult
import com.task.planner.domain.repository.IStorageRepository
import com.task.planner.presentation.theme.ThemeMode
import javax.inject.Inject

class GetThemeModeUseCase @Inject constructor(
    private val storageRepository: IStorageRepository
) : UseCase<Unit, ThemeMode>() {
    override suspend fun call(param: Unit): ClientResult<ThemeMode> {
        val result = storageRepository.getThemeMode()
        return ClientResult.Success(result)
    }
}

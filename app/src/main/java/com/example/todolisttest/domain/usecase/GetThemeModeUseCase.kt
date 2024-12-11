package com.example.todolisttest.domain.usecase

import com.example.todolisttest.data.cmmon.ClientResult
import com.example.todolisttest.domain.repository.IStorageRepository
import com.example.todolisttest.presentation.theme.ThemeMode
import javax.inject.Inject

class GetThemeModeUseCase @Inject constructor(
    private val storageRepository: IStorageRepository
) : UseCase<Unit, ThemeMode>() {
    override suspend fun call(param: Unit): ClientResult<ThemeMode> {
        val result = storageRepository.getThemeMode()
        return ClientResult.Success(result)
    }
}

package com.example.todolisttest.domain.usecase

import com.example.todolisttest.data.cmmon.ClientResult
import com.example.todolisttest.presentation.android_controller.IAndroidController
import javax.inject.Inject

class ExitAppUseCase @Inject constructor(
    private val androidController: IAndroidController
) : UseCase<Unit, Unit>() {
    override suspend fun call(param: Unit): ClientResult<Unit> {
        androidController.exitApp()
        return ClientResult.Success(Unit)
    }
}

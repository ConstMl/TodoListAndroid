package com.task.planner.domain.usecase

import com.task.planner.data.cmmon.ClientResult
import com.task.planner.presentation.android_controller.IAndroidController
import javax.inject.Inject

class ExitAppUseCase @Inject constructor(
    private val androidController: IAndroidController
) : UseCase<Unit, Unit>() {
    override suspend fun call(param: Unit): ClientResult<Unit> {
        androidController.exitApp()
        return ClientResult.Success(Unit)
    }
}

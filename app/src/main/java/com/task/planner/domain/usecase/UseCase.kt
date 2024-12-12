package com.task.planner.domain.usecase

import com.task.planner.data.cmmon.ClientResult

abstract class UseCase<Input, Output> {
    abstract suspend fun call(param: Input): ClientResult<Output>
}

package com.example.todolisttest.domain.usecase

import com.example.todolisttest.data.cmmon.ClientResult

abstract class UseCase<Input, Output> {
    abstract suspend fun call(param: Input): ClientResult<Output>
}

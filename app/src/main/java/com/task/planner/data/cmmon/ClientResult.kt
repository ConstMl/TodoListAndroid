package com.task.planner.data.cmmon

sealed class ClientResult<out T> {
    data class Success<out T>(val data: T) : ClientResult<T>()
    data class Error(val exception: DataSourceException) : ClientResult<Nothing>()
}

inline fun <T : Any> ClientResult<T>.onSuccess(action: (T) -> Unit): ClientResult<T> {
    if (this is ClientResult.Success) action(data)
    return this
}

inline fun <T : Any> ClientResult<T>.onError(action: (DataSourceException) -> Unit): ClientResult<T> {
    if (this is ClientResult.Error) action(exception)
    return this
}

inline fun <T : Any, R : Any> ClientResult<T>.map(transform: (T) -> R): ClientResult<R> {
    return when (this) {
        is ClientResult.Error -> ClientResult.Error(this.exception)
        is ClientResult.Success -> ClientResult.Success(transform.invoke(this.data))
    }
}

package com.example.todolisttest.data.cmmon

sealed class DataSourceException(
    val messageResource: Any?
) : RuntimeException() {
    class Unexpected(messageResource: Int) : DataSourceException(messageResource)
    class Server(error: Error?) : DataSourceException(error)
}

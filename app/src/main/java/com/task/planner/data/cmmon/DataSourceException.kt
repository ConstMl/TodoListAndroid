package com.task.planner.data.cmmon

sealed class DataSourceException(val msg: String) : RuntimeException() {
    class Server(msg: String) : DataSourceException(msg)
    class Message(msg: String) : DataSourceException(msg)
}

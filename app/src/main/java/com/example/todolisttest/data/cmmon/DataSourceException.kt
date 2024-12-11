package com.example.todolisttest.data.cmmon

import androidx.annotation.StringRes

sealed class DataSourceException : RuntimeException() {
    class Server(@StringRes id: Int, error: String) : DataSourceException()
    class MessageRes(@StringRes id: Int) : DataSourceException()
    class MessageString(message: String) : DataSourceException()
}

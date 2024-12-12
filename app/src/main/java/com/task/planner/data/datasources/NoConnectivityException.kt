package com.task.planner.data.datasources

import java.io.IOException

class NoConnectivityException : IOException() {
    override val message: String get() = "No Internet Connection"
}

package com.task.planner.data.datasources

import com.task.planner.data.cmmon.ClientResult
import com.task.planner.data.cmmon.DataSourceException
import retrofit2.Response

suspend fun <T> callClientResult(call: suspend () -> Response<T>): ClientResult<T> {
    try {
        val response = call.invoke()
        return if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                ClientResult.Success(body)
            } else {
                // todo: fix string
                ClientResult.Error(DataSourceException.Message("Server success, but body is null"))
            }
        } else {
            ClientResult.Error(
                DataSourceException.Server(response.message())
            )
        }
    } catch (error: NoConnectivityException) {
        return ClientResult.Error(
            DataSourceException.Server(error.message)
        )
    } catch (error: Exception) {
        return ClientResult.Error(
            DataSourceException.Server(error.message.toString())
        )
    }
}

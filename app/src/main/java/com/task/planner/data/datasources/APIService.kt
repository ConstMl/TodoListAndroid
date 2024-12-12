package com.task.planner.data.datasources

import com.task.planner.data.datasources.response.TaskResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("/todos")
    suspend fun getTasks(
        @Query("skip") page: Int,
        @Query("limit") pageSize: Int
    ): Response<TaskResponse>

    @GET("/todos?skip=0")
    suspend fun getTasks(
        @Query("limit") size: Int
    ): Response<TaskResponse>
}

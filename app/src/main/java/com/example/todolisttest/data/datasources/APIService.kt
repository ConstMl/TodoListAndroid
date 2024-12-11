package com.example.todolisttest.data.datasources

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("/todos")
    suspend fun getTodo(
        @Query("skip") page: Int,
        @Query("limit") pageSize: Int
    ): Response<TodoResponse>

    @GET("/todos?skip=0")
    suspend fun getTodo(
        @Query("limit") size: Int
    ): Response<TodoResponse>
}

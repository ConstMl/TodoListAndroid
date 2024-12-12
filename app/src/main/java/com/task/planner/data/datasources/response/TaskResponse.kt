package com.task.planner.data.datasources.response

import com.google.gson.annotations.SerializedName

data class TaskResponse(
    @SerializedName("total")
    val total: Int,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("todos")
    val todos: List<TaskItemResponse>
)

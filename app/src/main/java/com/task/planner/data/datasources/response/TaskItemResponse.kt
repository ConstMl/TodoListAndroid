package com.task.planner.data.datasources.response

import com.google.gson.annotations.SerializedName

data class TaskItemResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("todo")
    val todo: String,
    @SerializedName("completed")
    val completed: Boolean,
    @SerializedName("userId")
    val userId: Int
)

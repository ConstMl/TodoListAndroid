package com.example.todolisttest.data.datasources

import com.google.gson.annotations.SerializedName

data class TodoItemResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("todo")
    val todo: String,
    @SerializedName("completed")
    val completed: Boolean,
    @SerializedName("userId")
    val userId: Int
)

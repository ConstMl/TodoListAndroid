package com.example.todolisttest.data.datasources

import com.google.gson.annotations.SerializedName

data class TodoResponse(
    @SerializedName("total")
    val total: Int,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("todos")
    val todos: List<TodoItemResponse>
)

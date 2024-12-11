package com.example.todolisttest.domain.model

data class TaskListItemModel(
    val id: Int,
    val title: String,
    val completed: Boolean = false
)

/**
 * todo: API example
 * https://dummyjson.com/todos?limit=3&skip=0
 * skip - page
 * limit - pageSize
 * {
 *     "todos": [
 *         {
 *             "id": 1,
 *             "todo": "Do something nice for someone you care about",
 *             "completed": false,
 *             "userId": 152
 *         },
 *         {
 *             "id": 2,
 *             "todo": "Memorize a poem",
 *             "completed": true,
 *             "userId": 13
 *         },
 *         {
 *             "id": 3,
 *             "todo": "Watch a classic movie",
 *             "completed": true,
 *             "userId": 68
 *         }
 *     ],
 *     "total": 254,
 *     "skip": 0,
 *     "limit": 3
 * }
 * */

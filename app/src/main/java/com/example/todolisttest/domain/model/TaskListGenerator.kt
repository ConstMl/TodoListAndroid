package com.example.todolisttest.domain.model

import kotlin.random.Random

/**
 * todo: Test. Remove
 * */
object TaskListGenerator {
    /**
     * Test static list
     * */
    val testList10Items = generateList(50)

    /**
     * Generate paging item list
     * */
    private fun getPagingItems(
        page: Int,
        pageSize: Int,
        withRandomDone: Boolean = false
    ): List<TaskListItemModel> {
        return if (withRandomDone) {
            List(pageSize) { index ->
                TaskListItemModel(
                    id = page + index,
                    title = "Todo Item ${page + index}",
                    completed = Random.nextBoolean()
                )
            }
        } else {
            List(pageSize) { index ->
                TaskListItemModel(
                    id = page + index,
                    title = "Todo Item ${page + index}"
                )
            }
        }
    }

    /**
     * Generate static item list
     * */
    private fun generateList(
        size: Int,
        withRandomDone: Boolean = false
    ) = getPagingItems(0, size, withRandomDone)
}

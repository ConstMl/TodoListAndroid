package com.task.planner.presentation.screens.task_list_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.task.planner.domain.model.TaskListItemModel
import com.task.planner.presentation.theme.MediumDp

@Composable
fun TaskItemsContainer(
    modifier: Modifier = Modifier,
    taskItems: List<TaskListItemModel> = listOf(),
    onItemClick: (TaskListItemModel) -> Unit = {},
    onItemLongClick: (TaskListItemModel) -> Unit = {},
    onItemDoubleClick: (TaskListItemModel) -> Unit = {},
    onItemDelete: (TaskListItemModel) -> Unit = {},
    onItemToggleMultiSelection: (TaskListItemModel) -> Unit = {},
    isShowMultiSelection: Boolean = false,
    overlappingElementsHeight: Dp = 0.dp
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(MediumDp),
        verticalArrangement = Arrangement.spacedBy(MediumDp)
    ) {
        items(taskItems, key = { it.id }) { item ->
            Box(modifier = Modifier.animateItem()) {
                TaskListItem(
                    taskItem = item,
                    onItemClick = onItemClick,
                    onItemLongClick = onItemLongClick,
                    onItemDoubleClick = onItemDoubleClick,
                    onItemDelete = onItemDelete,
                    onItemToggleMultiSelection = onItemToggleMultiSelection,
                    isShowMultiSelection = isShowMultiSelection
                )
            }
        }
        item { Spacer(modifier = Modifier.height(overlappingElementsHeight)) }
    }
}

@Preview
@Composable
fun TaskItemsContainerPreview() {
    TaskItemsContainer(
        taskItems = listOf(
            TaskListItemModel(id = 1, title = "Todo Item 1", completed = true),
            TaskListItemModel(id = 2, title = "Todo Item 2"),
            TaskListItemModel(id = 3, title = "Todo Item 3"),
            TaskListItemModel(id = 4, title = "Todo Item 4", completed = true),
        )
    )
}

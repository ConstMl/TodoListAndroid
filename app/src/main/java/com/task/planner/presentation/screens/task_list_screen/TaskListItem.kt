package com.task.planner.presentation.screens.task_list_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.task.planner.R
import com.task.planner.domain.model.TaskListItemModel
import com.task.planner.presentation.theme.LargeDp
import com.task.planner.presentation.theme.MediumDp
import com.task.planner.presentation.theme.TaskItemActionButtonRippleRadius
import com.task.planner.presentation.theme.TaskItemHeight
import com.task.planner.presentation.theme.TaskItemIconSize
import com.task.planner.presentation.theme.TodoItemTitleTextStyle

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun TaskListItem(
    taskItem: TaskListItemModel,
    onItemClick: (TaskListItemModel) -> Unit = {},
    onItemLongClick: (TaskListItemModel) -> Unit = {},
    onItemDoubleClick: (TaskListItemModel) -> Unit = {},
    onItemDelete: (TaskListItemModel) -> Unit = {},
    onItemToggleMultiSelection: (TaskListItemModel) -> Unit = {},
    isShowMultiSelection: Boolean = false
) {
    val taskItemBackgroundColor = MaterialTheme.colorScheme.tertiaryContainer
    val taskItemIconColor = MaterialTheme.colorScheme.tertiary
    val taskItemTextColor = MaterialTheme.colorScheme.tertiary

    val backgroundColor = if (taskItem.completed) {
        taskItemBackgroundColor.copy(alpha = 0.5f)
    } else {
        taskItemBackgroundColor
    }

    val textColor = if (taskItem.completed) {
        taskItemTextColor.copy(alpha = 0.5f)
    } else {
        taskItemTextColor
    }

    val textDecoration = if (taskItem.completed) TextDecoration.LineThrough else null

    val iconId = if (taskItem.completed) {
        R.drawable.ic_check_box_checked
    } else {
        R.drawable.ic_check_box_unchecked
    }

    val iconColorFilter = if (taskItem.completed) {
        ColorFilter.tint(taskItemIconColor.copy(alpha = 0.5f))
    } else {
        ColorFilter.tint(taskItemIconColor)
    }

    val iconTintColor = if (taskItem.completed) {
        taskItemIconColor.copy(alpha = 0.5f)
    } else {
        taskItemIconColor
    }

    Row(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .weight(1f)
                .height(TaskItemHeight),
            elevation = CardDefaults.cardElevation(defaultElevation = LargeDp),
            shape = RoundedCornerShape(size = MediumDp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
                    .combinedClickable(
                        onClick = {
                            if (!isShowMultiSelection) {
                                onItemClick.invoke(taskItem)
                            } else {
                                onItemToggleMultiSelection.invoke(taskItem)
                            }
                        },
                        onLongClick = {
                            if (!isShowMultiSelection) onItemLongClick.invoke(taskItem)
                        },
                        onDoubleClick = {
                            if (!isShowMultiSelection) onItemDoubleClick.invoke(taskItem)
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AnimatedVisibility(visible = isShowMultiSelection) {
                    Checkbox(
                        modifier = Modifier
                            .size(TaskItemActionButtonRippleRadius),
                        checked = taskItem.multiSelected,
                        onCheckedChange = null
                    )
                }
                Text(
                    text = taskItem.title,
                    modifier = Modifier
                        .padding(MediumDp)
                        .weight(1f),
                    style = TodoItemTitleTextStyle.copy(color = textColor),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textDecoration = textDecoration
                )
                if (taskItem.completed) {
                    IconButton(
                        onClick = {
                            if (!isShowMultiSelection) onItemDelete.invoke(taskItem)
                        },
                        modifier = Modifier.size(TaskItemActionButtonRippleRadius)
                    ) {
                        Icon(
                            modifier = Modifier.size(TaskItemIconSize),
                            painter = painterResource(id = R.drawable.ic_remove),
                            contentDescription = null,
                            tint = iconTintColor
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun TodoListItemPreview() {
    Column(
        modifier = Modifier.padding(MediumDp),
        verticalArrangement = Arrangement.spacedBy(MediumDp)
    ) {
        TaskListItem(
            taskItem = TaskListItemModel(
                id = 1,
                title = "Todo Item 1 asd asd asd asd asd asd asd asd asd asd"
            ),
            isShowMultiSelection = true
        )
        TaskListItem(
            taskItem = TaskListItemModel(
                id = 2,
                title = "Todo Item 2 asd asd asd asd asd asd asd",
                completed = true
            ),
            isShowMultiSelection = true
        )
        TaskListItem(
            taskItem = TaskListItemModel(
                id = 3,
                title = "Todo Item 3",
                multiSelected = true
            )
        )
        TaskListItem(
            taskItem = TaskListItemModel(
                id = 4,
                title = "Todo Item 4",
                completed = true,
                multiSelected = true
            ),
            isShowMultiSelection = true
        )
    }
}

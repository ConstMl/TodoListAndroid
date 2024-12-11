package com.example.todolisttest.presentation.screens.todo_list_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.todolisttest.R
import com.example.todolisttest.domain.model.TaskListItemModel
import com.example.todolisttest.presentation.theme.LargeDp
import com.example.todolisttest.presentation.theme.MediumDp
import com.example.todolisttest.presentation.theme.TodoItemActionButtonRippleRadius
import com.example.todolisttest.presentation.theme.TodoItemHeight
import com.example.todolisttest.presentation.theme.TodoItemIconSize
import com.example.todolisttest.presentation.theme.TodoItemTitleTextStyle

@Composable
internal fun TodoListItem(
    todoItem: TaskListItemModel,
    onItemClick: (TaskListItemModel) -> Unit = {},
    onItemDelete: (TaskListItemModel) -> Unit = {}
) {
    val todoItemBackgroundColor = MaterialTheme.colorScheme.tertiaryContainer
    val todoItemIconColor = MaterialTheme.colorScheme.tertiary
    val todoItemTextColor = MaterialTheme.colorScheme.tertiary

    val backgroundColor = if (todoItem.completed) {
        todoItemBackgroundColor.copy(alpha = 0.5f)
    } else {
        todoItemBackgroundColor
    }

    val textColor = if (todoItem.completed) {
        todoItemTextColor.copy(alpha = 0.5f)
    } else {
        todoItemTextColor
    }

    val textDecoration = if (todoItem.completed) TextDecoration.LineThrough else null

    val iconId = if (todoItem.completed) {
        R.drawable.ic_check_box_checked
    } else {
        R.drawable.ic_check_box_unchecked
    }

    val iconColorFilter = if (todoItem.completed) {
        ColorFilter.tint(todoItemIconColor.copy(alpha = 0.5f))
    } else {
        ColorFilter.tint(todoItemIconColor)
    }

    val iconTintColor = if (todoItem.completed) {
        todoItemIconColor.copy(alpha = 0.5f)
    } else {
        todoItemIconColor
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(TodoItemHeight),
        elevation = CardDefaults.cardElevation(defaultElevation = LargeDp),
        shape = RoundedCornerShape(size = MediumDp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .clickable { onItemClick(todoItem) },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = null,
                modifier = Modifier
                    .padding(MediumDp)
                    .size(TodoItemIconSize),
                colorFilter = iconColorFilter
            )
            Text(
                text = todoItem.title,
                modifier = Modifier.weight(1f),
                style = TodoItemTitleTextStyle.copy(color = textColor),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textDecoration = textDecoration
            )
            if (todoItem.completed) {
                IconButton(
                    onClick = { onItemDelete(todoItem) },
                    modifier = Modifier.size(TodoItemActionButtonRippleRadius)
                ) {
                    Icon(
                        modifier = Modifier.size(TodoItemIconSize),
                        painter = painterResource(id = R.drawable.ic_remove),
                        contentDescription = null,
                        tint = iconTintColor
                    )
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
        TodoListItem(
            todoItem = TaskListItemModel(
                id = 1,
                title = "Todo Item 1 asd asd asd asd asd asd asd asd asd asd"
            )
        )
        TodoListItem(
            todoItem = TaskListItemModel(
                id = 2,
                title = "Todo Item 2 asd asd asd asd asd asd asd",
                completed = true
            )
        )
        TodoListItem(
            todoItem = TaskListItemModel(
                id = 3,
                title = "Todo Item 3"
            )
        )
        TodoListItem(
            todoItem = TaskListItemModel(
                id = 4,
                title = "Todo Item 4", completed = true
            )
        )
    }
}

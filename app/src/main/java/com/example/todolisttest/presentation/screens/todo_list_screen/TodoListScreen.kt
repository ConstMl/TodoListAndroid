package com.example.todolisttest.presentation.screens.todo_list_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolisttest.R
import com.example.todolisttest.domain.model.TaskListItemModel
import com.example.todolisttest.presentation.theme.OverlappingHeight
import com.example.todolisttest.presentation.widget.CommonDialog
import com.example.todolisttest.presentation.widget.CustomCircularProgressIndicator
import com.example.todolisttest.presentation.widget.InfoDialog

@Composable
internal fun TodoListScreen() {
    val viewModel = hiltViewModel<TodoListViewModel>()
    val state by viewModel.state.collectAsState()

    var openRemoveDialog by remember {
        mutableStateOf(Pair<Boolean, TaskListItemModel?>(false, null))
    }

    var openMoreDialog by remember {
        mutableStateOf(Pair<Boolean, String?>(false, ""))
    }

    if (state.isLoading) {
        CustomCircularProgressIndicator(modifier = Modifier.fillMaxSize())
    } else {
        TodoItemsContainer(
            todoItems = state.taskList,
            onItemClick = viewModel::onItemClick,
            onItemLongClick = { item ->
                openMoreDialog = Pair(true, item.title)
            },
            onItemDelete = viewModel::onItemRemove,
//            onItemDelete = { item -> // todo: remove ?
//                openRemoveDialog = Pair(true, item)
//            },
            overlappingElementsHeight = OverlappingHeight
        )
    }

    if (openRemoveDialog.first) {
        CommonDialog(
            title = stringResource(
                id = R.string.confirm_remove_task_dialog_title
            ),
            onConfirm = {
                openRemoveDialog.second?.let(viewModel::onItemRemove)
                openRemoveDialog = Pair(false, null)
            },
            onCancel = {
                openRemoveDialog = Pair(false, null)
            }
        )
    }

    if (openMoreDialog.first) {
        InfoDialog(title = openMoreDialog.second ?: "") {
            openMoreDialog = Pair(false, null)
        }
    }
}

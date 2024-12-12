package com.example.todolisttest.presentation.screens.todo_list_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolisttest.R
import com.example.todolisttest.presentation.theme.OverlappingHeight
import com.example.todolisttest.presentation.widget.CommonDialog
import com.example.todolisttest.presentation.widget.CustomCircularProgressIndicator
import com.example.todolisttest.presentation.widget.InfoDialog

@Composable
internal fun TodoListScreen() {
    val viewModel = hiltViewModel<TodoListViewModel>()
    val state by viewModel.state.collectAsState()

    var openRemoveDialog by remember { mutableStateOf(false) }

    var openMoreDialog by remember {
        mutableStateOf(Pair<Boolean, String?>(false, ""))
    }

    if (state.isLoading) {
        CustomCircularProgressIndicator(modifier = Modifier.fillMaxSize())
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            TodoItemsContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                todoItems = state.taskList,
                onItemClick = viewModel::onItemClick,
                onItemLongClick = viewModel::showMultiSelection,
                onItemDoubleClick = { item ->
                    openMoreDialog = Pair(true, item.title)
                },
                onItemDelete = viewModel::onItemRemove,
                onItemToggleMultiSelection = viewModel::toggleItemMultiSelection,
                isShowMultiSelection = state.isShowMultiSelectionPanel,
                overlappingElementsHeight = OverlappingHeight
            )
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.BottomCenter),
                visible = state.isShowMultiSelectionPanel,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                MultiSelectionBottomPanel(
                    itemsSelected = state.isItemsMultiSelected,
                    removeEnabled = state.multiSelectionIsNotEmpty,
                    onToggleMultiSelection = viewModel::toggleMultiSelection,
                    onClose = viewModel::hideMultiSelection,
                    onRemove = {
                        openRemoveDialog = true
                    }
                )
            }
        }
    }

    if (openRemoveDialog) {
        CommonDialog(
            title = stringResource(
                id = R.string.confirm_multi_selected_remove_task_dialog_title
            ),
            onConfirm = {
                viewModel.removeMultiSelection()
                openRemoveDialog = false
            },
            onCancel = {
                openRemoveDialog = false
            }
        )
    }

    if (openMoreDialog.first) {
        InfoDialog(title = openMoreDialog.second ?: "") {
            openMoreDialog = Pair(false, null)
        }
    }
}

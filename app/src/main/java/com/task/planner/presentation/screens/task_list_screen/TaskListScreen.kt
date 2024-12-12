package com.task.planner.presentation.screens.task_list_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
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
import com.task.planner.R
import com.task.planner.presentation.theme.OverlappingHeight
import com.task.planner.presentation.widget.CommonDialog
import com.task.planner.presentation.widget.CustomCircularProgressIndicator
import com.task.planner.presentation.widget.InfoDialog

@Composable
internal fun TaskListScreen() {
    val viewModel = hiltViewModel<TaskListViewModel>()
    val state by viewModel.state.collectAsState()

    var openRemoveDialog by remember { mutableStateOf(false) }

    var openMoreDialog by remember {
        mutableStateOf(Pair<Boolean, String?>(false, ""))
    }

    if (state.isLoading) {
        CustomCircularProgressIndicator(modifier = Modifier.fillMaxSize())
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            TaskItemsContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                taskItems = state.taskList,
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

    if (state.hasError) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = state.errorText.toString()
            )
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

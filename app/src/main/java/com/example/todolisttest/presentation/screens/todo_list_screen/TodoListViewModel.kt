package com.example.todolisttest.presentation.screens.todo_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolisttest.data.cmmon.onError
import com.example.todolisttest.data.cmmon.onSuccess
import com.example.todolisttest.domain.model.TaskListItemModel
import com.example.todolisttest.domain.usecase.GetRemoteTaskListUseCase
import com.example.todolisttest.domain.usecase.GetTaskListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val getAllTaskUseCase: GetTaskListUseCase,
    private val getRemoteTaskListUseCase: GetRemoteTaskListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(TodoListState())
    val state = _state.asStateFlow()

    private fun setState(newState: TodoListState) {
        _state.value = newState
    }

    init {
        initialLoad()
    }

    private fun initialLoad() = viewModelScope.launch {
        _state.emit(TodoListState(isLoading = true))
        getRemoteTaskListUseCase
            .call("")
            .onSuccess { data ->
                _state.emit(
                    TodoListState(
                        isLoading = false,
                        taskList = data
                    )
                )
            }
            .onError { error ->
                _state.emit(
                    TodoListState(
                        isLoading = false,
                        hasError = true,
                        errorText = error.message
                    )
                )
            }
    }

    fun onItemClick(item: TaskListItemModel) = viewModelScope.launch {
        _state.update { currentState ->
            currentState.copy(
                taskList = currentState.taskList.map { itm ->
                    if (itm.id == item.id) {
                        itm.copy(completed = !itm.completed)
                    } else {
                        itm
                    }
                }
            )
        }
    }

    fun onItemRemove(item: TaskListItemModel) = viewModelScope.launch {
        _state.update { currentState ->
            currentState.copy(
                taskList = currentState.taskList.filter { itm -> itm.id != item.id }
            )
        }
    }

    fun showMultiSelection(item: TaskListItemModel) {
        _state.update { currentState ->
            currentState.copy(
                isShowMultiSelectionPanel = true,
                taskList = currentState.taskList.map { itm ->
                    when {
                        item.id == itm.id && !item.multiSelected ->
                            itm.copy(multiSelected = true)

                        item.id != itm.id && item.multiSelected ->
                            itm.copy(multiSelected = false)

                        else -> itm
                    }
                }
            )
        }
    }

    fun hideMultiSelection() = viewModelScope.launch {
        _state.update { currentState ->
            currentState.copy(
                isShowMultiSelectionPanel = false,
                taskList = currentState.taskList.map { itm ->
                    if (itm.multiSelected) {
                        itm.copy(multiSelected = false)
                    } else {
                        itm
                    }
                }
            )
        }
    }

    fun toggleMultiSelection() = viewModelScope.launch {
        _state.update { currentState ->
            val newMultiSelection = !currentState.isItemsMultiSelected
            currentState.copy(
                isItemsMultiSelected = newMultiSelection,
                taskList = currentState.taskList.map { itm ->
                    if (itm.multiSelected != newMultiSelection) {
                        itm.copy(multiSelected = newMultiSelection)
                    } else {
                        itm
                    }
                }
            )
        }
    }

    fun toggleItemMultiSelection(item: TaskListItemModel) = viewModelScope.launch {
        _state.update { currentState ->
            val newTaskList = currentState.taskList.map { itm ->
                if (item.id == itm.id) {
                    itm.copy(multiSelected = !itm.multiSelected)
                } else {
                    itm
                }
            }
            currentState.copy(
                taskList = newTaskList,
                isItemsMultiSelected = newTaskList.all { itm -> itm.multiSelected }
            )
        }
    }

    fun removeMultiSelection() = viewModelScope.launch {
        _state.update { currentState ->
            currentState.copy(
                taskList = currentState.taskList.filter { itm ->
                    !itm.multiSelected
                }
            )
        }
    }
}

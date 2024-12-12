package com.task.planner.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.planner.data.cmmon.onSuccess
import com.task.planner.domain.usecase.ExitAppUseCase
import com.task.planner.domain.usecase.GetThemeModeUseCase
import com.task.planner.domain.usecase.StoreThemeModeUseCase
import com.task.planner.presentation.theme.ThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskAppViewModel @Inject constructor(
    private val exitAppUseCase: ExitAppUseCase,
    private val storeThemeModeUseCase: StoreThemeModeUseCase,
    private val getThemeModeUseCase: GetThemeModeUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(TaskAppState())
    val state = _state.asStateFlow()

    init {
        getThemeMode()
    }

    fun storeThemeMode(mode: ThemeMode) = viewModelScope.launch {
        _state.update { currentState ->
            currentState.copy(themeMode = mode)
        }
        storeThemeModeUseCase.call(mode)
    }

    private fun getThemeMode() = viewModelScope.launch {
        getThemeModeUseCase.call(Unit).onSuccess { mode ->
            _state.update { currentState ->
                currentState.copy(themeMode = mode)
            }
        }
    }

    fun exitApp() = viewModelScope.launch {
        exitAppUseCase.call(Unit)
    }
}

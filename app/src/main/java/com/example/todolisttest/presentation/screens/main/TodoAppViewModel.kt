package com.example.todolisttest.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolisttest.data.cmmon.onSuccess
import com.example.todolisttest.domain.usecase.ExitAppUseCase
import com.example.todolisttest.domain.usecase.GetThemeModeUseCase
import com.example.todolisttest.domain.usecase.StoreThemeModeUseCase
import com.example.todolisttest.presentation.theme.ThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoAppViewModel @Inject constructor(
    private val exitAppUseCase: ExitAppUseCase,
    private val storeThemeModeUseCase: StoreThemeModeUseCase,
    private val getThemeModeUseCase: GetThemeModeUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(TodoAppState())
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

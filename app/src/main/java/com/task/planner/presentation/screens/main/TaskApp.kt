package com.task.planner.presentation.screens.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.task.planner.presentation.theme.TaskPlannerAppTheme
import kotlinx.coroutines.launch

@Composable
internal fun TaskApp(
    content: @Composable (PaddingValues) -> Unit
) {
    val viewModel = hiltViewModel<TaskAppViewModel>()
    val state by viewModel.state.collectAsState()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    TaskPlannerAppTheme(themeMode = state.themeMode) {
        CustomModalNavigationDrawer(
            drawerState = drawerState,
            themeMode = state.themeMode,
            onThemeClick = viewModel::storeThemeMode,
            onExitAppClick = viewModel::exitApp
        ) {
            CustomAppBar(
                onMenuClick = {
                    scope.launch {
                        if (drawerState.isClosed) {
                            drawerState.open()
                        } else {
                            drawerState.close()
                        }
                    }
                },
                onExitAppClick = viewModel::exitApp
            ) { innerPadding ->
                content(innerPadding)
            }
        }
    }
}

package com.example.todolisttest.presentation.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolisttest.R
import com.example.todolisttest.presentation.theme.ThemeMode
import com.example.todolisttest.presentation.theme.TodoListTestTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TodoApp(
    content: @Composable (PaddingValues) -> Unit
) {
    val viewModel = hiltViewModel<TodoAppViewModel>()
    val state by viewModel.state.collectAsState()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

//    var themeMode by remember { mutableStateOf(ThemeMode.SYSTEM) }  // todo: save in store

    TodoListTestTheme(themeMode = state.themeMode) {
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Spacer(Modifier.height(12.dp))
                        Text(
                            text = stringResource(id = R.string.settings_title),
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.titleLarge
                        )
                        HorizontalDivider()

                        Text(
                            text = stringResource(id = R.string.theme_mode_title),
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.titleMedium
                        )
                        NavigationDrawerItem(
                            label = { Text(stringResource(id = R.string.theme_mode_system)) },
                            selected = state.themeMode == ThemeMode.SYSTEM,
                            onClick = { viewModel.storeThemeMode(ThemeMode.SYSTEM) }
                        )
                        NavigationDrawerItem(
                            label = { Text(stringResource(id = R.string.theme_mode_light)) },
                            selected = state.themeMode == ThemeMode.LIGHT,
                            onClick = { viewModel.storeThemeMode(ThemeMode.LIGHT) }
                        )
                        NavigationDrawerItem(
                            label = { Text(stringResource(id = R.string.theme_mode_dark)) },
                            selected = state.themeMode == ThemeMode.DARK,
                            onClick = { viewModel.storeThemeMode(ThemeMode.DARK) }
                        )

                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                        Text(
                            text = stringResource(id = R.string.settings_other),
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.titleMedium
                        )
                        NavigationDrawerItem(
                            label = { Text("Settings") },
                            selected = false,
                            icon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
                            badge = { Text("20") }, // Placeholder
                            onClick = { /* Handle click */ }
                        )

                        NavigationDrawerItem(
                            label = { Text(stringResource(id = R.string.settings_exit)) },
                            selected = false,
                            icon = {
                                Icon(
                                    Icons.AutoMirrored.Outlined.ExitToApp,
                                    contentDescription = null
                                )
                            },
                            onClick = { /* todo: exit app */ },
                        )
                        Spacer(Modifier.height(12.dp))
                    }
                }
            },
            drawerState = drawerState
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    CenterAlignedTopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                        title = {
                            Text(stringResource(id = R.string.app_bar_name))
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    if (drawerState.isClosed) {
                                        drawerState.open()
                                    } else {
                                        drawerState.close()
                                    }
                                }
                            }) {
                                Icon(Icons.Default.Menu, contentDescription = null)
                            }
                        },
                        actions = {
                            IconButton(onClick = { /* todo: exit app */ }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                                    contentDescription = null
                                )
                            }
                        },
                        scrollBehavior = scrollBehavior
                    )
                }
            ) { innerPadding ->
                content(innerPadding)
            }
        }
    }
}
package com.task.planner.presentation.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.task.planner.R
import com.task.planner.presentation.theme.ThemeMode

@Composable
fun CustomModalNavigationDrawer(
    drawerState: DrawerState,
    themeMode: ThemeMode,
    onThemeClick: (ThemeMode) -> Unit,
    onExitAppClick: () -> Unit,
    content: @Composable () -> Unit
) {
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
                        selected = themeMode == ThemeMode.SYSTEM,
                        onClick = { onThemeClick.invoke(ThemeMode.SYSTEM) }
                    )
                    NavigationDrawerItem(
                        label = { Text(stringResource(id = R.string.theme_mode_light)) },
                        selected = themeMode == ThemeMode.LIGHT,
                        onClick = { onThemeClick.invoke(ThemeMode.LIGHT) }
                    )
                    NavigationDrawerItem(
                        label = { Text(stringResource(id = R.string.theme_mode_dark)) },
                        selected = themeMode == ThemeMode.DARK,
                        onClick = { onThemeClick.invoke(ThemeMode.DARK) }
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
                        onClick = { onExitAppClick.invoke() },
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
        },
        drawerState = drawerState
    ) {
        content()
    }
}

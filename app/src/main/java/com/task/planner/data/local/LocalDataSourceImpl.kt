package com.task.planner.data.local

import android.content.SharedPreferences
import com.task.planner.data.cmmon.ClientResult
import com.task.planner.domain.model.TaskListGenerator
import com.task.planner.domain.model.TaskListItemModel
import com.task.planner.presentation.theme.ThemeMode

class LocalDataSourceImpl(
    private val sharedPreferences: SharedPreferences
) : ILocalDataSource {

    override suspend fun getAllTask(): ClientResult<List<TaskListItemModel>> {
        // todo: return data from store
        val data = TaskListGenerator.testList10Items
        val result = ClientResult.Success(data)
        return result
    }

    override suspend fun storeThemeMode(mode: ThemeMode) {
        sharedPreferences
            .edit()
            .putString(NAME_THEME_MODE, mode.name)
            .apply()
    }

    override suspend fun getThemeMode(): ThemeMode {
        val themeMode = sharedPreferences.getString(NAME_THEME_MODE, ThemeMode.SYSTEM.name)
        return ThemeMode.valueOf(themeMode ?: ThemeMode.SYSTEM.name)
    }

    companion object {
        private const val NAME_THEME_MODE = "NAME_THEME_MODE"
    }
}

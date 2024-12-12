package com.task.planner.di

import android.content.SharedPreferences
import com.task.planner.data.datasources.APIService
import com.task.planner.data.local.ILocalDataSource
import com.task.planner.data.local.LocalDataSourceImpl
import com.task.planner.data.remote.IRemoteDataSource
import com.task.planner.data.remote.RemoteDataSourceImpl
import com.task.planner.data.repository.StorageRepositoryImpl
import com.task.planner.data.repository.TaskRepositoryImpl
import com.task.planner.domain.repository.IStorageRepository
import com.task.planner.domain.repository.ITaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        apiService: APIService
    ): IRemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        sharedPreferences: SharedPreferences
    ): ILocalDataSource {
        return LocalDataSourceImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideTaskRepository(
        iRemoteDataSource: IRemoteDataSource,
        localDataSource: ILocalDataSource
    ): ITaskRepository {
        return TaskRepositoryImpl(
            remoteDataSource = iRemoteDataSource,
            localDataSource = localDataSource
        )
    }

    @Provides
    @Singleton
    fun provideStorageRepository(
        localDataSource: ILocalDataSource
    ): IStorageRepository {
        return StorageRepositoryImpl(
            localDataSource
        )
    }
}

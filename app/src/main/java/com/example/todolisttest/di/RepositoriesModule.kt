package com.example.todolisttest.di

import android.content.SharedPreferences
import com.example.todolisttest.data.datasources.APIService
import com.example.todolisttest.data.local.ILocalDataSource
import com.example.todolisttest.data.local.LocalDataSourceImpl
import com.example.todolisttest.data.remote.IRemoteDataSource
import com.example.todolisttest.data.remote.RemoteDataSourceImpl
import com.example.todolisttest.data.repository.StorageRepositoryImpl
import com.example.todolisttest.data.repository.TaskRepositoryImpl
import com.example.todolisttest.domain.repository.IStorageRepository
import com.example.todolisttest.domain.repository.ITaskRepository
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

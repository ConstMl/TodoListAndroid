package com.example.todolisttest.di

import com.example.todolisttest.data.local.ILocalDataSource
import com.example.todolisttest.data.local.LocalDataSourceImpl
import com.example.todolisttest.data.remote.IRemoteDataSource
import com.example.todolisttest.data.remote.RemoteDataSourceImpl
import com.example.todolisttest.data.repository.TaskRepositoryImpl
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
    fun provideRemoteDataSource(/* todo: add API */): IRemoteDataSource {
        return RemoteDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(/* todo: add Store */): ILocalDataSource {
        return LocalDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideCharactersRepository(
        iRemoteDataSource: IRemoteDataSource,
        localDataSource: ILocalDataSource
    ): ITaskRepository {
        return TaskRepositoryImpl(
            remoteDataSource = iRemoteDataSource,
            localDataSource = localDataSource
        )
    }
}

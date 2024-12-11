package com.example.todolisttest.di

import com.example.todolisttest.presentation.android_controller.AndroidControllerImpl
import com.example.todolisttest.presentation.android_controller.IAndroidController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AndroidModule {

    @Provides
    @Singleton
    fun provideAndroidController(
    ): IAndroidController {
        return AndroidControllerImpl()
    }
}

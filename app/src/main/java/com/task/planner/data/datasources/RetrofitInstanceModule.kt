package com.task.planner.data.datasources

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitInstanceModule {

    @Provides
    @Singleton
    fun theRetrofitInstance(): APIService {
        val api: APIService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)
        }
        return api
    }

    companion object {
        private const val BASE_URL = "https://dummyjson.com/"
    }
}

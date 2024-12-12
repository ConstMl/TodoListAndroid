package com.task.planner.data.datasources

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient.Builder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitInstanceModule {

    @Provides
    @Singleton
    fun theRetrofitInstance(@ApplicationContext context: Context): APIService {
        val api: APIService by lazy {
            val oktHttpClient = Builder()
                .addInterceptor(NetworkConnectionInterceptor(context))
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(oktHttpClient)
                .build()
                .create(APIService::class.java)
        }
        return api
    }

    companion object {
        private const val BASE_URL = "https://dummyjson.com/"
    }
}

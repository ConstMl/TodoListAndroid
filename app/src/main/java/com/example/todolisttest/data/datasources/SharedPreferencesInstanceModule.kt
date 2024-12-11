package com.example.todolisttest.data.datasources

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferencesInstanceModule {

    @Provides
    @Singleton
    fun theSharedPreferencesInstance(@ApplicationContext context: Context): SharedPreferences {
        val sharedPreferences by lazy {
            val mainKey = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()
            val sharedPreferences = EncryptedSharedPreferences.create(
                context,
                SHARED_PREFERENCES_FILE_NAME,
                mainKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
            sharedPreferences
        }
        return sharedPreferences
    }

    companion object {
        private const val SHARED_PREFERENCES_FILE_NAME = "prefs"
    }
}

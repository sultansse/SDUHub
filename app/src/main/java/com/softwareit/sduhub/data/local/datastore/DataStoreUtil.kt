package com.softwareit.sduhub.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class DataStoreUtil(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

        val THEME_KEY = booleanPreferencesKey("theme_key")
        val AUTH_TOKEN_KEY = stringPreferencesKey("auth_token_key")
    }

    fun getTheme(): Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[THEME_KEY] ?: false
        }

//    fun isAuthed(): Flow<Boolean> = context.dataStore.data
//        .map {  preferences ->
//            preferences[AUTH_TOKEN_KEY] ?: "false"
//        }

    suspend fun saveTheme(isDarkThemeEnabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkThemeEnabled
        }
    }

    suspend fun saveAuthToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[AUTH_TOKEN_KEY] = token
        }
    }
}
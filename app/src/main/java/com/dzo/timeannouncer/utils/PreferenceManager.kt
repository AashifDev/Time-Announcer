package com.dzo.timeannouncer.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("app_prefs")

@Singleton
class PreferenceManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val SELECTED_OPTION_KEY = stringPreferencesKey("selected_option")
        private val SELECTED_SOUND_KEY = stringPreferencesKey("selected_sound")
    }

    suspend fun saveSelectedOption(value: String) {
        context.dataStore.edit { prefs ->
            prefs[SELECTED_OPTION_KEY] = value
        }
    }

    suspend fun saveSelectedSound(value: String) {
        context.dataStore.edit { prefs ->
            prefs[SELECTED_SOUND_KEY] = value
        }
    }

    val selectedOption: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[SELECTED_OPTION_KEY]
    }

    val selectedSound: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[SELECTED_SOUND_KEY]
    }
}

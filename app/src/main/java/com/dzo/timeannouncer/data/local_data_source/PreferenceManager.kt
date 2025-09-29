package com.dzo.timeannouncer.data.local_data_source

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.dzo.timeannouncer.domain.model.RepeatOption
import com.dzo.timeannouncer.domain.model.Settings
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("app_prefs")

private val gson = Gson()
@Singleton
class PreferenceManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val SELECTED_OPTION_KEY = stringPreferencesKey("selected_option")
        private val SELECTED_SOUND_KEY = stringPreferencesKey("selected_sound")
        // Keys
        private val KEY_CHIME = booleanPreferencesKey("key_chime")
        private val KEY_REPEAT_MIN = intPreferencesKey("key_repeat_min")
        private val KEY_SOUND = stringPreferencesKey("key_sound")
        private val KEY_VIBRATION = stringPreferencesKey("key_vibration")
        private val KEY_VOLUME = intPreferencesKey("key_volume")
        private val KEY_CUSTOM_CHIME = booleanPreferencesKey("key_custom_chime")
    }


    suspend fun saveSelectedRepeatOption(option: RepeatOption) {
        val json = gson.toJson(option)
        context.dataStore.edit { prefs ->
            prefs[SELECTED_OPTION_KEY] = json
        }
    }

    val selectedRepeatOption: Flow<RepeatOption?> = context.dataStore.data.map { prefs ->
        prefs[SELECTED_OPTION_KEY]?.let { json ->
            gson.fromJson(json, RepeatOption::class.java)
        }
    }

    suspend fun saveSelectedSound(value: String) {
        context.dataStore.edit { prefs ->
            prefs[SELECTED_SOUND_KEY] = value
        }
    }

    fun settingsFlow(): Flow<Settings> {
        return context.dataStore.data.map { prefs ->
            Settings(
                isChimeEnabled = prefs[KEY_CHIME] ?: false,
                repeatEveryMinutes = prefs[KEY_REPEAT_MIN] ?: 5,
                sound = prefs[KEY_SOUND] ?: "Beep",
                vibration = prefs[KEY_VIBRATION] ?: "None",
                volume = prefs[KEY_VOLUME] ?: 100,
                isCustomChimeEnabled = prefs[KEY_CUSTOM_CHIME] ?: false
            )
        }
    }

    suspend fun readSettings(): Settings {
        val prefs = context.dataStore.data.first()
        return Settings(
            isChimeEnabled = prefs[KEY_CHIME] ?: false,
            repeatEveryMinutes = prefs[KEY_REPEAT_MIN] ?: 5,
            sound = prefs[KEY_SOUND] ?: "Beep",
            vibration = prefs[KEY_VIBRATION] ?: "None",
            volume = prefs[KEY_VOLUME] ?: 100,
            isCustomChimeEnabled = prefs[KEY_CUSTOM_CHIME] ?: false
        )
    }

    suspend fun saveSettings(settings: Settings) {
        context.dataStore.edit { prefs ->
            prefs[KEY_CHIME] = settings.isChimeEnabled
            prefs[KEY_REPEAT_MIN] = settings.repeatEveryMinutes
            prefs[KEY_SOUND] = settings.sound
            prefs[KEY_VIBRATION] = settings.vibration
            prefs[KEY_VOLUME] = settings.volume
            prefs[KEY_CUSTOM_CHIME] = settings.isCustomChimeEnabled
        }
    }

    val selectedSound: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[SELECTED_SOUND_KEY]
    }
}

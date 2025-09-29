package com.dzo.timeannouncer.data.repository.setting_repository

import android.content.Context
import com.dzo.timeannouncer.domain.model.Settings
import com.dzo.timeannouncer.data.local_data_source.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepositoryImpl @Inject constructor(
    private  val preferenceManager: PreferenceManager
) : SettingsRepository {
    override fun settingsFlow(): Flow<Settings> = preferenceManager.settingsFlow()

    override suspend fun getSettings(): Settings = withContext(Dispatchers.IO) {
        preferenceManager.readSettings()
    }

    override suspend fun saveSettings(settings: Settings) = withContext(Dispatchers.IO) {
        preferenceManager.saveSettings(settings)
    }
}

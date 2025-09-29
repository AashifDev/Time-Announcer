package com.dzo.timeannouncer.data.repository.setting_repository

import com.dzo.timeannouncer.domain.model.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun settingsFlow(): Flow<Settings>
    suspend fun getSettings(): Settings
    suspend fun saveSettings(settings: Settings)
}

package com.dzo.timeannouncer.domain.setting_usecase

import com.dzo.timeannouncer.data.repository.setting_repository.SettingsRepository
import com.dzo.timeannouncer.domain.model.Settings
import javax.inject.Inject

class SaveSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(settings: Settings) {
        settingsRepository.saveSettings(settings)
    }
}


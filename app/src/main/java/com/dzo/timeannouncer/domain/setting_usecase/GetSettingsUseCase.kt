package com.dzo.timeannouncer.domain.setting_usecase

import com.dzo.timeannouncer.data.repository.setting_repository.SettingsRepository
import com.dzo.timeannouncer.domain.model.Settings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    operator fun invoke(): Flow<Settings> = settingsRepository.settingsFlow()
}

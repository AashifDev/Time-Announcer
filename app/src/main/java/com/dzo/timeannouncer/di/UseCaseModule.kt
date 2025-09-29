package com.dzo.timeannouncer.di

import com.dzo.timeannouncer.data.repository.setting_repository.SettingsRepository
import com.dzo.timeannouncer.domain.setting_usecase.CancelTimerUseCase
import com.dzo.timeannouncer.domain.setting_usecase.GetSettingsUseCase
import com.dzo.timeannouncer.domain.setting_usecase.SaveSettingsUseCase
import com.dzo.timeannouncer.domain.setting_usecase.StartTimerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideUseCases(repo: SettingsRepository): UseCaseBundle {
        return UseCaseBundle(
            getSettingsUseCase = GetSettingsUseCase(repo),
            saveSettingsUseCase = SaveSettingsUseCase(repo),
            startTimerUseCase = StartTimerUseCase(),
            cancelTimerUseCase = CancelTimerUseCase()
        )
    }
}

// ---------------------- Use Case Bundle ----------------------
data class UseCaseBundle(
    val getSettingsUseCase: GetSettingsUseCase,
    val saveSettingsUseCase: SaveSettingsUseCase,
    val startTimerUseCase: StartTimerUseCase,
    val cancelTimerUseCase: CancelTimerUseCase
)
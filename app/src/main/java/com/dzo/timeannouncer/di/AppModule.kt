package com.dzo.timeannouncer.di

import android.content.Context
import com.dzo.timeannouncer.data.local_data_source.PreferenceManager
import com.dzo.timeannouncer.data.repository.repeat_repository.RepeatOptionRepository
import com.dzo.timeannouncer.data.repository.repeat_repository.RepeatOptionRepositoryImpl
import com.dzo.timeannouncer.data.repository.setting_repository.SettingsRepository
import com.dzo.timeannouncer.data.repository.setting_repository.SettingsRepositoryImpl
import com.dzo.timeannouncer.data.repository.sound_repository.SoundOptionRepository
import com.dzo.timeannouncer.data.repository.sound_repository.SoundOptionRepositoryImpl
import com.dzo.timeannouncer.domain.setting_usecase.CancelTimerUseCase
import com.dzo.timeannouncer.domain.setting_usecase.GetSettingsUseCase
import com.dzo.timeannouncer.domain.setting_usecase.SaveSettingsUseCase
import com.dzo.timeannouncer.domain.setting_usecase.StartTimerUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindRepeatRepository(
        impl: RepeatOptionRepositoryImpl
    ): RepeatOptionRepository

    @Binds
    @Singleton
    abstract fun bindSoundRepository(
        impl: SoundOptionRepositoryImpl
    ): SoundOptionRepository

    @Binds
    @Singleton
    abstract fun bindSettingRepository(
        impl: SettingsRepositoryImpl
    ): SettingsRepository


}
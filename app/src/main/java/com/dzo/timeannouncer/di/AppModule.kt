package com.dzo.timeannouncer.di

import com.dzo.timeannouncer.data.repeat_repository.RepeatOptionRepository
import com.dzo.timeannouncer.data.repeat_repository.RepeatOptionRepositoryImpl
import com.dzo.timeannouncer.data.sound_repository.SoundOptionRepository
import com.dzo.timeannouncer.data.sound_repository.SoundOptionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
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
}
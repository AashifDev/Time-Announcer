package com.dzo.timeannouncer.data.sound_repository

import com.dzo.timeannouncer.domain.model.NotificationSound
import kotlinx.coroutines.flow.Flow

interface SoundOptionRepository{
    fun getSounds(): List<NotificationSound>
    suspend fun setSelectedSound(sound: NotificationSound)
    fun getSelectedSound(): Flow<NotificationSound?>

}
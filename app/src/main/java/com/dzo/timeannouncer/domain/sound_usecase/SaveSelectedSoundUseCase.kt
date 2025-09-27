package com.dzo.timeannouncer.domain.sound_usecase

import com.dzo.timeannouncer.data.sound_repository.SoundOptionRepository
import com.dzo.timeannouncer.data.sound_repository.SoundOptionRepositoryImpl
import com.dzo.timeannouncer.domain.model.NotificationSound
import javax.inject.Inject

class SaveSelectedSoundUseCase @Inject constructor(
    private val repository: SoundOptionRepository
){
    suspend operator fun invoke(soundOption: NotificationSound) = repository.setSelectedSound(soundOption)
}
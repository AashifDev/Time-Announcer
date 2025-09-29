package com.dzo.timeannouncer.domain.sound_usecase

import com.dzo.timeannouncer.data.repository.sound_repository.SoundOptionRepository
import javax.inject.Inject

class GetSoundOptionUseCase @Inject constructor(
    private val repository: SoundOptionRepository
){
    operator fun invoke() = repository.getSounds()
}
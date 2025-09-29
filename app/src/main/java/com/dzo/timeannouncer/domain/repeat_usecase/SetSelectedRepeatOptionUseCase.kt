package com.dzo.timeannouncer.domain.repeat_usecase

import com.dzo.timeannouncer.data.repository.repeat_repository.RepeatOptionRepository
import com.dzo.timeannouncer.domain.model.RepeatOption

import javax.inject.Inject

class SaveSelectedOptionUseCase @Inject constructor(
    private val repository: RepeatOptionRepository
) {
    suspend operator fun invoke(option: RepeatOption) = repository.setSelectedOption(option)
}

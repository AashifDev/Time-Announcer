package com.dzo.timeannouncer.domain.repeat_usecase

import com.dzo.timeannouncer.data.repeat_repository.RepeatOptionRepository
import com.dzo.timeannouncer.domain.model.RepeatOption
import javax.inject.Inject


class GetRepeatOptionsUseCase @Inject constructor(
    private val repository: RepeatOptionRepository
) {
    operator fun invoke(): List<RepeatOption> = repository.getOptions()
}
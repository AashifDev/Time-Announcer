package com.dzo.timeannouncer.domain.repeat_usecase

import com.dzo.timeannouncer.data.repository.repeat_repository.RepeatOptionRepository
import com.dzo.timeannouncer.domain.model.RepeatOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSelectedOptionUseCase @Inject constructor(
    private val repository: RepeatOptionRepository
) {
    operator fun invoke(): Flow<RepeatOption?> = repository.getSelectedOption()
}

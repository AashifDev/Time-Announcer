package com.dzo.timeannouncer.domain.setting_usecase

import kotlinx.coroutines.Job
import javax.inject.Inject

class CancelTimerUseCase @Inject constructor() {
    operator fun invoke(job: Job?) {
        job?.cancel()
    }
}

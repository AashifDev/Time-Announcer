package com.dzo.timeannouncer.domain.setting_usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Starts a timer that ticks every second until totalSeconds reached.
 * Returns the Job so the caller (ViewModel) can cancel it.
 */
class StartTimerUseCase @Inject constructor() {
    operator fun invoke(
        scope: CoroutineScope,
        totalSeconds: Int,
        onTick: (elapsedSeconds: Int) -> Unit,
        onFinish: () -> Unit
    ): Job {
        return scope.launch {
            var elapsed = 0
            while (isActive && elapsed < totalSeconds) {
                delay(1000)
                elapsed += 1
                onTick(elapsed)
            }
            if (isActive) onFinish()
        }
    }
}

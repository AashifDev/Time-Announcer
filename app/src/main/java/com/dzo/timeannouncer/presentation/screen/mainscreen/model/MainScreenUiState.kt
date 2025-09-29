package com.dzo.timeannouncer.presentation.screen.mainscreen.model

import com.dzo.timeannouncer.domain.model.Settings

data class MainScreenUiState(
    val settings: Settings = Settings(),
    val elapsedSeconds: Int = 0,
    val totalTime: Int = 60,
    val isTimerRunning: Boolean = false,
    val error: String? = null
)
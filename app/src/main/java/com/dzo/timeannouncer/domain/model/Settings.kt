package com.dzo.timeannouncer.domain.model

data class Settings(
    val isChimeEnabled: Boolean = false,
    val repeatEveryMinutes: Int = 1, // store as minutes
    val sound: String = "Beep",
    val vibration: String = "None",
    val volume: Int = 100,
    val isCustomChimeEnabled: Boolean = false,
    val elapsedSeconds: Int = 0,
    val isTimerRunning: Boolean = false

)
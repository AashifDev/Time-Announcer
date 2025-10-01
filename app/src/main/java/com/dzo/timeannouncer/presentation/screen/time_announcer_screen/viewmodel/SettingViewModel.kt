package com.dzo.timeannouncer.presentation.screen.time_announcer_screen.viewmodel

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzo.timeannouncer.data.service.TimerService
import com.dzo.timeannouncer.di.UseCaseBundle
import com.dzo.timeannouncer.domain.model.Settings
import com.dzo.timeannouncer.presentation.screen.time_announcer_screen.model.MainScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/*@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val useCases: UseCaseBundle
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState: StateFlow<MainScreenUiState> = _uiState


    private var timerJob: Job? = null

    init {
        // Observe settings
        viewModelScope.launch {
            useCases.getSettingsUseCase().collectLatest { settings ->
                _uiState.value = _uiState.value.copy(settings = settings)
            }
        }
    }

    fun saveSettings(settings: Settings) {
        viewModelScope.launch {
            useCases.saveSettingsUseCase(settings)
        }
    }

    fun toggleChime(enabled: Boolean){
        val newSettings = _uiState.value.settings.copy(isChimeEnabled = enabled)
        saveSettings(newSettings)

    }

    fun startTimerForMinutes(minutes: Int) {
        timerJob?.cancel()
        val totalSeconds = minutes
        _uiState.value = _uiState.value.copy(isTimerRunning = true, elapsedSeconds = 0)
        timerJob = useCases.startTimerUseCase(
            scope = viewModelScope,
            totalSeconds = totalSeconds,
            onTick = { elapsed ->
                _uiState.value = _uiState.value.copy(elapsedSeconds = elapsed)
                val newSettings = _uiState.value.settings.copy(elapsedSeconds = elapsed)
                saveSettings(newSettings)
            },
            onFinish = {
                _uiState.value = _uiState.value.copy(isTimerRunning = true)
                // You may trigger the chime here using audio manager, etc.
            }
        )
    }

    fun cancelTimer() {
        useCases.cancelTimerUseCase(timerJob)
        timerJob = null
        _uiState.value = _uiState.value.copy(isTimerRunning = false, elapsedSeconds = 0)
    }
}*/


@HiltViewModel
class SettingsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val useCases: UseCaseBundle
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState: StateFlow<MainScreenUiState> = _uiState

    private var timerJob: Job? = null

    init {
        // Observe persisted settings
        viewModelScope.launch {
            useCases.getSettingsUseCase().collectLatest { settings ->
                _uiState.value = _uiState.value.copy(
                    settings = settings,
                    elapsedSeconds = settings.elapsedSeconds,
                    isTimerRunning = settings.isChimeEnabled
                )

                // If timer was running, resume
                if (settings.isChimeEnabled) {
                    startTimerForMinutes(_uiState.value.totalTime, settings.elapsedSeconds)
                }
            }
        }
    }

    fun saveSettings(settings: Settings) {
        viewModelScope.launch {
            useCases.saveSettingsUseCase(settings)
        }
    }

    fun toggleChime(enabled: Boolean) {
        val newSettings = _uiState.value.settings.copy(
            isChimeEnabled = enabled,
            elapsedSeconds = if (enabled) _uiState.value.elapsedSeconds else 0
        )
        saveSettings(newSettings)

        if (enabled) {
            startTimerForMinutes(_uiState.value.totalTime, newSettings.elapsedSeconds)
        } else {
            cancelTimer()
        }
    }

    fun startTimerForMinutes(minutes: Int, startFrom: Int = 0) {
        timerJob?.cancel()
        val totalSeconds = minutes
        _uiState.value = _uiState.value.copy(isTimerRunning = true, elapsedSeconds = startFrom)

        timerJob = useCases.startTimerUseCase(
            scope = viewModelScope,
            totalSeconds = totalSeconds,
            startFrom = startFrom,
            onTick = { elapsed ->
                _uiState.value = _uiState.value.copy(elapsedSeconds = elapsed)
                val newSettings = _uiState.value.settings.copy(elapsedSeconds = elapsed)
                saveSettings(newSettings)
            },
            onFinish = {
                // Restart automatically
                startTimerForMinutes(totalSeconds, 0)
            }
        )
    }

    fun cancelTimer() {
        useCases.cancelTimerUseCase(timerJob)
        timerJob = null
        _uiState.value = _uiState.value.copy(isTimerRunning = false, elapsedSeconds = 0)
        val newSettings = _uiState.value.settings.copy(elapsedSeconds = 0, isChimeEnabled = false)
        saveSettings(newSettings)
    }

    fun toggleChimee(enabled: Boolean) {
        if (enabled) {
            startService("START")
        } else {
            startService("STOP")
        }
    }

    private fun startService(action: String) {
        val serviceIntent = Intent(context, TimerService::class.java).apply {
            this.action = action
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent)
        } else {
            context.startService(serviceIntent)
        }
    }
}




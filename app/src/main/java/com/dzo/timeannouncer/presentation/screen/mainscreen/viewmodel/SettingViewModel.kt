package com.dzo.timeannouncer.presentation.screen.mainscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzo.timeannouncer.di.UseCaseBundle
import com.dzo.timeannouncer.domain.model.Settings
import com.dzo.timeannouncer.presentation.screen.mainscreen.model.MainScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
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

    fun toggleChime(enabled: Boolean) {
        val newSettings = _uiState.value.settings.copy(isChimeEnabled = enabled)
        saveSettings(newSettings)
    }

    fun startTimerForMinutes(minutes: Int) {
        timerJob?.cancel()
        val totalSeconds = minutes * 60
        _uiState.value = _uiState.value.copy(isTimerRunning = true, elapsedSeconds = 0)
        timerJob = useCases.startTimerUseCase(
            scope = viewModelScope,
            totalSeconds = totalSeconds,
            onTick = { elapsed ->
                _uiState.value = _uiState.value.copy(elapsedSeconds = elapsed)
            },
            onFinish = {
                _uiState.value = _uiState.value.copy(isTimerRunning = false)
                // You may trigger the chime here using audio manager, etc.
            }
        )
        println("SettingsViewModel.startTimerForMinutes::$totalSeconds")
    }

    fun cancelTimer() {
        useCases.cancelTimerUseCase(timerJob)
        timerJob = null
        _uiState.value = _uiState.value.copy(isTimerRunning = false, elapsedSeconds = 0)
    }
}


package com.dzo.timeannouncer.presentation.viewmodel

import android.content.Context
import android.media.AudioManager
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainScreenUiState(
    val isChimeEnabled: Boolean = false,
    val repeatEvery: String = "5 Minutes",
    val sound: String = "Beep",
    val vibration: String = "None",
    val volume: Int = 100,
    val isCustomChimeEnabled: Boolean = false
)
@HiltViewModel
class SettingsViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState: StateFlow<MainScreenUiState> = _uiState

    val elapsedTime = mutableIntStateOf(0)
    private var timerJob: Job? = null

    fun startTimer(totalTime: Int) {
        timerJob?.cancel()
        elapsedTime.intValue = 0
        timerJob = viewModelScope.launch {
            while (elapsedTime.intValue < totalTime) {
                delay(1000)
                elapsedTime.intValue += 1
            }
        }
    }

    fun cancelTimer() {
        timerJob?.cancel()
        timerJob = null
        elapsedTime.intValue = 0
    }

    fun toggleChime(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(isChimeEnabled = enabled)
    }
}

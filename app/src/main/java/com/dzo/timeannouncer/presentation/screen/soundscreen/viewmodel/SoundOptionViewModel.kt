package com.dzo.timeannouncer.presentation.screen.soundscreen.viewmodel

import android.content.Context
import android.database.ContentObserver
import android.media.AudioManager
import android.net.Uri
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzo.timeannouncer.domain.model.NotificationSound
import com.dzo.timeannouncer.domain.sound_usecase.GetSelectedSoundUseCase
import com.dzo.timeannouncer.domain.sound_usecase.GetSoundOptionUseCase
import com.dzo.timeannouncer.domain.sound_usecase.SaveSelectedSoundUseCase
import com.dzo.timeannouncer.presentation.screen.soundscreen.model.VolumeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SoundOptionViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    getSoundOptionUseCase: GetSoundOptionUseCase,
    getSelectedOptionUseCase: GetSelectedSoundUseCase,
    private val saveSelectedOptionUseCase: SaveSelectedSoundUseCase,
) : ViewModel() {

    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    private val _uiState = MutableStateFlow(
        VolumeUiState(
            currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC),
            maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        )
    )
    val uiState: StateFlow<VolumeUiState> = _uiState

    val options: List<NotificationSound> = getSoundOptionUseCase()

    val selectedOption: StateFlow<NotificationSound?> =
        getSelectedOptionUseCase().stateIn(viewModelScope, SharingStarted.Lazily, null)


    private val contentObserver = object : ContentObserver(Handler(Looper.getMainLooper())) {
        override fun onChange(selfChange: Boolean, uri: Uri?) {
            super.onChange(selfChange, uri)
            updateVolumeState()
        }
    }

    init {
        // register observer for system volume changes
        context.contentResolver.registerContentObserver(
            android.provider.Settings.System.CONTENT_URI,
            true,
            contentObserver
        )
    }


    fun selectSound(option: NotificationSound) {
        viewModelScope.launch {
            saveSelectedOptionUseCase(option)
        }
    }


    fun setVolume(value: Int) {
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, value, 0)
        updateVolumeState()
    }

    private fun updateVolumeState() {
        viewModelScope.launch {
            _uiState.value = VolumeUiState(
                currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC),
                maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        context.contentResolver.unregisterContentObserver(contentObserver)
    }
}

package com.dzo.timeannouncer.presentation.screen.repeatscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzo.timeannouncer.domain.model.RepeatOption
import com.dzo.timeannouncer.domain.repeat_usecase.GetRepeatOptionsUseCase
import com.dzo.timeannouncer.domain.repeat_usecase.GetSelectedOptionUseCase
import com.dzo.timeannouncer.domain.repeat_usecase.SaveSelectedOptionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepeatOptionsViewModel @Inject constructor(
    getRepeatOptionsUseCase: GetRepeatOptionsUseCase,
    getSelectedOptionUseCase: GetSelectedOptionUseCase,
    private val saveSelectedOptionUseCase: SaveSelectedOptionUseCase
) : ViewModel() {

    val options: List<RepeatOption> = getRepeatOptionsUseCase()

    val selectedOption: StateFlow<RepeatOption?> =
        getSelectedOptionUseCase().stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun selectOption(option: RepeatOption) {
        viewModelScope.launch {
            saveSelectedOptionUseCase(option)
        }
    }
}
package com.dzo.timeannouncer.presentation.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor() : ViewModel()  {

    val elapsedTime = mutableIntStateOf(0)

    fun startTimer(totalTime:Int){
        elapsedTime.intValue = 0
        viewModelScope.launch {
            while (elapsedTime.intValue < totalTime){
                delay(1000)
                elapsedTime.intValue += 1
            }
        }
    }
}
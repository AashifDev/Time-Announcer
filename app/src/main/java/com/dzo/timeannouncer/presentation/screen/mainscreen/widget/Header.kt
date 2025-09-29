/*
package com.dzo.timeannouncer.presentation.screen.mainscreen.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dzo.timeannouncer.presentation.screen.mainscreen.model.MainScreenUiState
import com.dzo.timeannouncer.presentation.screen.mainscreen.viewmodel.SettingsViewModel
import com.dzo.timeannouncer.presentation.screen.repeatscreen.viewmodel.RepeatOptionsViewModel

@Composable
fun Header(
    uiState: MainScreenUiState,
    settingsViewModel: SettingsViewModel,
    repeatOptionsViewModel: RepeatOptionsViewModel
) {
    val repeatEveryDuration by repeatOptionsViewModel.selectedOption.collectAsState()
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF00BFFF)) // Sky blue
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(100.dp)
            ) {
                CircularTimerProgress(
                    elapsed = uiState.elapsedSeconds,
                    total = uiState.totalTime,
                    isRunning = uiState.isTimerRunning
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    if (uiState.isTimerRunning){
                        FutureTimeText()
                    }else{
                        Text(
                            text = "OFF",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                    if (uiState.isTimerRunning) {
                        Text(
                            text = "Next Chime",
                            fontSize = 12.sp,
                            color = Color.Black
                        )
                    }
                }
            }

            Spacer(Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = if (uiState.isTimerRunning) "Time Announcer  ON" else "Time Announcer OFF",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Enable repeat chime for minute wise chime",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            */
/*Switch(
                checked = uiState.isTimerRunning,
                onCheckedChange = { enabled ->
                    settingsViewModel.toggleChime(enabled)
                    if (enabled) {
                        settingsViewModel.startTimerForMinutes(repeatEveryDuration!!.value) // for example: 60 sec
                    } else {
                        settingsViewModel.cancelTimer()
                    }
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    checkedTrackColor = Color(0xFFFFFFFF),
                    disabledCheckedBorderColor = Color.Transparent,
                    disabledCheckedThumbColor = Color.Transparent,
                    uncheckedBorderColor = Color.Transparent
                ),
                modifier = Modifier.wrapContentWidth()
            )*//*

            Switch(
                checked = uiState.isTimerRunning,
                onCheckedChange = { enabled ->
                    settingsViewModel.toggleChime(enabled)
                    if (enabled) {
                        settingsViewModel.startTimerForMinutes(repeatEveryDuration!!.value, true) // seconds or minutes based
                    } else {
                        settingsViewModel.cancelTimer()
                    }
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    checkedTrackColor = Color(0xFFFFFFFF),
                    disabledCheckedBorderColor = Color.Transparent,
                    disabledCheckedThumbColor = Color.Transparent,
                    uncheckedBorderColor = Color.Transparent
                ),
                modifier = Modifier.wrapContentWidth()
            )

        }
    }
}*/

package com.dzo.timeannouncer.presentation.screen.mainscreen.widget

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dzo.timeannouncer.presentation.screen.mainscreen.model.MainScreenUiState
import com.dzo.timeannouncer.presentation.screen.mainscreen.viewmodel.SettingsViewModel
import com.dzo.timeannouncer.presentation.screen.repeatscreen.viewmodel.RepeatOptionsViewModel

@Composable
fun Header(
    uiState: MainScreenUiState,
    settingsViewModel: SettingsViewModel,
    repeatOptionsViewModel: RepeatOptionsViewModel
) {
    val repeatEveryDuration by repeatOptionsViewModel.selectedOption.collectAsState()
    val context = LocalContext.current
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF00BFFF))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(100.dp)
            ) {
                CircularTimerProgress(
                    elapsed = uiState.elapsedSeconds,
                    total = repeatEveryDuration?.value ?: 2,
                    isRunning = uiState.isTimerRunning
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    if (uiState.isTimerRunning){
                        FutureTimeText()
                    } else {
                        Text(
                            text = "OFF",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                    if (uiState.isTimerRunning) {
                        Text(
                            text = "Next Chime",
                            fontSize = 12.sp,
                            color = Color.Black
                        )
                    }
                }
            }

            Spacer(Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = if (uiState.isTimerRunning) "Time Announcer ON" else "Time Announcer OFF",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Enable repeat chime for minute wise chime",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Switch(
                checked = uiState.isTimerRunning,
                onCheckedChange = { enabled ->
                    settingsViewModel.toggleChime(enabled)
                    if (enabled) {
                        // Start timer and enable repeat automatically
                        settingsViewModel.startTimerForMinutes(
                            minutes = repeatEveryDuration?.value!!,
                        )
                    } else {
                        settingsViewModel.cancelTimer()
                    }
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    checkedTrackColor = Color.White,
                    disabledCheckedBorderColor = Color.Transparent,
                    disabledCheckedThumbColor = Color.Transparent,
                    uncheckedBorderColor = Color.Transparent
                ),
                modifier = Modifier.wrapContentWidth()
            )
        }
    }
}

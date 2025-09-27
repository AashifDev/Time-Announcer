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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dzo.timeannouncer.presentation.viewmodel.MainScreenUiState
import com.dzo.timeannouncer.presentation.viewmodel.SettingsViewModel

@Composable
fun Header(state: MainScreenUiState, elapsed: Int, totalTime: Int, viewModel: SettingsViewModel) {
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
                    elapsed = elapsed,
                    total = totalTime,
                    isRunning = state.isChimeEnabled
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    if (state.isChimeEnabled){
                        FutureTimeText()
                    }else{
                        Text(
                            text = "OFF",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                    if (state.isChimeEnabled) {
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
                    text = if (state.isChimeEnabled) "Hourly Chime ON" else "Hourly Chime OFF",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Enable repeat chime for hourly chime",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Switch(
                checked = state.isChimeEnabled,
                onCheckedChange = { enabled ->
                    viewModel.toggleChime(enabled)
                    viewModel.toggleChime(enabled)
                    if (enabled) {
                        viewModel.startTimer(totalTime = 60) // for example: 60 sec
                    } else {
                        viewModel.cancelTimer()
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
}
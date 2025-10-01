package com.dzo.timeannouncer.presentation.screen.time_announcer_screen.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dzo.timeannouncer.domain.model.NotificationSound
import com.dzo.timeannouncer.domain.model.RepeatOption
import com.dzo.timeannouncer.presentation.navgraph.AppRoutes
import com.dzo.timeannouncer.presentation.screen.soundscreen.viewmodel.SoundOptionViewModel


@Composable
fun GeneralSettings(
    navController: NavHostController,
    selectedOption: RepeatOption?,
    selectedSoundOption: NotificationSound?
) {
    val soundViewModel: SoundOptionViewModel = hiltViewModel()
    val volumeState by soundViewModel.uiState.collectAsState()

    val percentage = remember(volumeState.currentVolume, volumeState.maxVolume) {
        if (volumeState.maxVolume > 0) {
            (volumeState.currentVolume * 100) / volumeState.maxVolume
        } else 0
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF))
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(16.dp)
        ) {
            Text("General settings", style = MaterialTheme.typography.titleMedium)

            Spacer(Modifier.height(8.dp))

            SettingItem(
                icon = Icons.Default.Repeat,
                title = "Repeat every",
                value = selectedOption?.title ?: "None"
            ) {
                navController.navigate(AppRoutes.RepeatEveryScreen.route)
            }

            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color(0x97F1EFEF))
            )

            SettingItem(
                icon = Icons.Default.MusicNote,
                title = "Sounds",
                value = selectedSoundOption?.name ?: "None"
            ) {
                navController.navigate(AppRoutes.SoundScreen.route)
            }

            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color(0x97F1EFEF))
            )

            SettingItem(
                icon = Icons.Default.Vibration,
                title = "Vibration",
                value = ""
            ) {
                navController.navigate(AppRoutes.VibrationScreen.route)
            }

            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color(0x97F1EFEF))
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
            ) {
                SettingItem(
                    icon = Icons.Default.VolumeUp,
                    title = "Volume",
                    value = "${percentage}%"
                )
                Spacer(Modifier.height(1.dp))
                Slider(
                    value = percentage.toFloat(),
                    onValueChange = { newValue ->
                        val actualVolume = (newValue / 100f * volumeState.maxVolume).toInt()
                        soundViewModel.setVolume(actualVolume)
                    },
                    valueRange = 0f..100f,
                    steps = volumeState.maxVolume
                )
            }
        }

    }
}
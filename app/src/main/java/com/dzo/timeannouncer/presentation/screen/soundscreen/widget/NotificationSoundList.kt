package com.dzo.timeannouncer.presentation.screen.soundscreen.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dzo.timeannouncer.domain.model.NotificationSound
import com.dzo.timeannouncer.presentation.screen.soundscreen.viewmodel.SoundOptionViewModel

@Composable
fun NotificationSoundList(
    soundOptionViewModel: SoundOptionViewModel,
    onSoundClick: (NotificationSound) -> Unit
) {
    val soundOptions = soundOptionViewModel.options
    val selectedSoundOption by soundOptionViewModel.selectedOption.collectAsState()
    LazyColumn(
        modifier = Modifier
            .wrapContentHeight()
            .padding(top = 16.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        items(soundOptions) { sound ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onSoundClick(sound)
                        soundOptionViewModel.selectSound(sound)
                    }
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = sound.name, style = MaterialTheme.typography.bodyLarge,
                    color = if (selectedSoundOption == sound) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface)
                if (selectedSoundOption == sound) {
                    Icon(
                        Icons.Default.VolumeUp, contentDescription = "Play",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}





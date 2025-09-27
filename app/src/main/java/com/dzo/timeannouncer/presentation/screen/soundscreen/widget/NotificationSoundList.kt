package com.dzo.timeannouncer.presentation.screen.soundscreen.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dzo.timeannouncer.domain.model.NotificationSound
import com.dzo.timeannouncer.presentation.screen.soundscreen.viewmodel.SoundOptionViewModel

@Composable
fun NotificationSoundList(
    options: List<NotificationSound>,
    viewModel: SoundOptionViewModel,
    selectedOption: NotificationSound?,
    onSoundClick: (NotificationSound) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(options) { sound ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onSoundClick(sound)
                        viewModel.selectSound(sound)
                    }
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = sound.name, style = MaterialTheme.typography.bodyLarge)
                if (selectedOption == sound) {
                    Icon(
                        Icons.Default.VolumeUp, contentDescription = "Play",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}





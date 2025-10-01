package com.dzo.timeannouncer.presentation.screen.time_announcer_screen.widget

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun FutureTimeText(minutesToAdd: Long = 0) {
    var displayTime by remember { mutableStateOf("") }

    LaunchedEffect(minutesToAdd) {
        while (true) {
            val now = LocalTime.now().plusMinutes(minutesToAdd)
            val formatter = DateTimeFormatter.ofPattern("h:mm a") // 12:10 PM
            displayTime = now.format(formatter)
            delay(1000L) // Update every second
        }
    }

    Text(
        text = displayTime,
        style = MaterialTheme.typography.titleLarge
    )
}
package com.dzo.timeannouncer.presentation.widgets

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CircularTimerProgress(elapsed: Int, total: Int, isRunning: Boolean) {
    // Calculate target progress (0f..1f)
    val targetProgress = if (total > 0) elapsed / total.toFloat() else 0f

    // Animate smoothly every frame when running
    val progress by animateFloatAsState(
        targetValue = if (isRunning) targetProgress else 0f,
        animationSpec = if (isRunning) tween(
            durationMillis = 2000, // smooth for each second increment
            easing = LinearEasing
        ) else tween(500) // reset immediately when stopped
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(CircleShape)
            .background(Color.White)
            .padding(1.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 12.dp,
            trackColor = Color(0xFFE6FAFF),
            strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
        )
    }
}


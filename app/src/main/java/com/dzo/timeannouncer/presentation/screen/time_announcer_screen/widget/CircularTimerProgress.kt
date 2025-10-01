package com.dzo.timeannouncer.presentation.screen.time_announcer_screen.widget

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.isActive

/*@Composable
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
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 10.dp,
            trackColor = Color(0xFFE6FAFF),
            strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
        )
    }
}*/


@Composable
fun CircularTimerProgress(elapsed: Int, total: Int, isRunning: Boolean) {
    val progressAnim = remember { androidx.compose.animation.core.Animatable(0f) }

    LaunchedEffect(isRunning, total) {
        while (isRunning && isActive) {
            progressAnim.snapTo(elapsed.toFloat() / total)
            for (second in 0..total) {
                val target = second.toFloat() / total
                progressAnim.animateTo(
                    target,
                    animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
                )
            }
        }
        if (!isRunning) {
            progressAnim.snapTo(0f)
        }
    }

    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(Color.White)
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = { progressAnim.value },
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 10.dp,
            trackColor = Color(0xFFD7F6FD),
            strokeCap = StrokeCap.Round,
        )
    }
}


/*

@Composable
fun CircularTimerProgress(
    elapsed: Int,
    total: Int,
    isRunning: Boolean
) {
    val progressAnim = remember { Animatable(0f) }

    LaunchedEffect(elapsed, isRunning, total) {
        while (isRunning && isActive){
            if (total > 0) {
                // prevent invalid state
                val safeElapsed = if (elapsed > total) 0 else elapsed
                val target = safeElapsed.toFloat() / total

                progressAnim.animateTo(
                    target,
                    animationSpec = tween(durationMillis = 500, easing = LinearEasing)
                )
            } else {
                progressAnim.snapTo(0f)
            }
        }

    }

    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(Color.White)
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = { progressAnim.value },
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 10.dp,
            trackColor = Color(0xFFD7F6FD),
            strokeCap = StrokeCap.Round,
        )
    }
}

*/


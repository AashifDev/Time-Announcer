package com.dzo.timeannouncer.data.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.dzo.timeannouncer.MainActivity
import com.dzo.timeannouncer.R
import com.dzo.timeannouncer.data.repository.setting_repository.SettingsRepository
import com.dzo.timeannouncer.domain.model.Settings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable.isActive
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TimerService : LifecycleService() {

    @Inject
    lateinit var settingsRepository: SettingsRepository

    private var job: Job? = null
    private val channelId = "timer_channel"
    private val notificationId = 101

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        when (intent?.action) {
            "START" -> startTimer(10) // default 10 sec
            "STOP" -> stopTimer()
        }
        return START_STICKY
    }

    private fun startTimer(totalSeconds: Int) {
        job?.cancel()
        job = lifecycleScope.launch {
            var elapsed = 0
            while (isActive && elapsed <= totalSeconds) {
                // Save progress to DataStore
                settingsRepository.saveSettings(
                    Settings(isChimeEnabled = true, elapsedSeconds = elapsed)
                )

                // Update notification
                showNotification(elapsed, totalSeconds)

                delay(1000)
                elapsed++
            }
            stopTimer()
        }
    }

    private fun stopTimer() {
        job?.cancel()
        lifecycleScope.launch {
            settingsRepository.saveSettings(Settings(isChimeEnabled = false, elapsedSeconds = 0))
        }
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    private fun showNotification(elapsed: Int, total: Int) {
        val progress = (elapsed * 100 / total)

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Timer Running")
            .setContentText("Progress: $progress%")
            .setSmallIcon(R.drawable.logo)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setProgress(100, progress, false)
            .build()

        startForeground(notificationId, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                channelId,
                "Timer Service Channel",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }
}

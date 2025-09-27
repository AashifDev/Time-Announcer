package com.dzo.timeannouncer.utils

import android.content.Context
import android.media.MediaPlayer
import com.dzo.timeannouncer.domain.model.NotificationSound

class Utils {
    fun playSound(context: Context, sound: NotificationSound) {
        val mediaPlayer = MediaPlayer.create(context, sound.resId)
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener { it.release() }
    }

}
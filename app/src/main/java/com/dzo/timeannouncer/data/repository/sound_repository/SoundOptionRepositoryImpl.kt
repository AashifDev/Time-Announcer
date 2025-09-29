package com.dzo.timeannouncer.data.repository.sound_repository

import android.content.Context
import com.dzo.timeannouncer.R
import com.dzo.timeannouncer.domain.model.NotificationSound
import com.dzo.timeannouncer.data.local_data_source.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SoundOptionRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val preferenceManager: PreferenceManager
) : SoundOptionRepository{

    val soundIds = listOf(
        R.raw.long_pop,
        R.raw.pop_up_alert,
        R.raw.door_opens,
        R.raw.smbw_yoshi,
        R.raw.balloon_get,
        R.raw.mario_starwarp,
        R.raw.smw_power_up,
        R.raw.super_mario_jump,
        R.raw.super_mario_world,
        R.raw.super_mario_world_14,
    )

    private var selectedSound: NotificationSound? = null


    override fun getSounds(): List<NotificationSound> {
        return soundIds.map { id ->
            val name = context.resources.getResourceEntryName(id) // <- yaha context use hoga
                .replace("_", " ")
                .replaceFirstChar { it.uppercase() }

            NotificationSound(id, name)
        }
    }
    override suspend fun setSelectedSound(sound: NotificationSound) {
        selectedSound = sound
        preferenceManager.saveSelectedSound(sound.name)
    }

    override fun getSelectedSound(): Flow<NotificationSound?> {
        return preferenceManager.selectedSound.map { name ->
            getSounds().find { it.name == name }
        }
    }

}
package com.dzo.timeannouncer.data.repeat_repository

import com.dzo.timeannouncer.domain.model.RepeatOption
import kotlinx.coroutines.flow.Flow

interface RepeatOptionRepository {
    fun getOptions(): List<RepeatOption>
    suspend fun setSelectedOption(option: RepeatOption)
    fun getSelectedOption(): Flow<RepeatOption?>

}
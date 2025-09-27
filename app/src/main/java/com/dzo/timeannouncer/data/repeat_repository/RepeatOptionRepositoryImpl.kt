package com.dzo.timeannouncer.data.repeat_repository

import com.dzo.timeannouncer.domain.model.RepeatOption
import com.dzo.timeannouncer.utils.PreferenceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepeatOptionRepositoryImpl  @Inject constructor(
    private val preferenceManager: PreferenceManager
): RepeatOptionRepository {

    private val options = listOf(
        RepeatOption("2 Minutes"),
        RepeatOption("5 Minutes"),
        RepeatOption("10 Minutes"),
        RepeatOption("15 Minutes"),
        RepeatOption("30 Minutes"),
        RepeatOption("1 Hour")
    )

    private var selectedOption: RepeatOption? = null

    override fun getOptions(): List<RepeatOption> = options

    override suspend fun setSelectedOption(option: RepeatOption) {
        selectedOption = option
        preferenceManager.saveSelectedOption(option.title)
    }

    //override fun getSelectedOption(): RepeatOption? = selectedOption

    override fun getSelectedOption(): Flow<RepeatOption?>{
        return preferenceManager.selectedOption.map {title->
            options.find { it.title == title }
        }
    }
}

package com.dzo.timeannouncer.data.repository.repeat_repository

import com.dzo.timeannouncer.domain.model.RepeatOption
import com.dzo.timeannouncer.data.local_data_source.PreferenceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepeatOptionRepositoryImpl  @Inject constructor(
    private val preferenceManager: PreferenceManager
): RepeatOptionRepository {

    private val options = listOf(
        RepeatOption("2 Minutes",2),
        RepeatOption("5 Minutes", 5),
        RepeatOption("10 Minutes",10),
        RepeatOption("15 Minutes",15),
        RepeatOption("30 Minutes",30),
        RepeatOption("1 Hour",60)
    )

    private var selectedOption: RepeatOption? = null

    override fun getOptions(): List<RepeatOption> = options

    override suspend fun setSelectedOption(option: RepeatOption) {
        selectedOption = option
        preferenceManager.saveSelectedRepeatOption(option)
    }

    //override fun getSelectedOption(): RepeatOption? = selectedOption

    override fun getSelectedOption(): Flow<RepeatOption?> {
        return preferenceManager.selectedRepeatOption.map { savedOption ->
            savedOption?.let { option ->
                options.find { it.title == option.title } ?: option
            }
        }
    }

}

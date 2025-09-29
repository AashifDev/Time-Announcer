package com.dzo.timeannouncer

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.dzo.timeannouncer.presentation.navgraph.AppRoutes
import com.dzo.timeannouncer.presentation.navgraph.MainNavGraph
import com.dzo.timeannouncer.presentation.screen.mainscreen.viewmodel.SettingsViewModel
import com.dzo.timeannouncer.presentation.screen.repeatscreen.viewmodel.RepeatOptionsViewModel
import com.dzo.timeannouncer.presentation.screen.soundscreen.viewmodel.SoundOptionViewModel
import com.dzo.timeannouncer.presentation.ui.theme.TimeAnnouncerTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val settingsViewModel: SettingsViewModel = hiltViewModel()
            val soundOptionViewModel: SoundOptionViewModel = hiltViewModel()
            val repeatOptionsViewModel: RepeatOptionsViewModel = hiltViewModel()
            val repeatEveryDuration by repeatOptionsViewModel.selectedOption.collectAsState()
            val context = LocalContext.current
/*
            LaunchedEffect(Unit) {
                repeatOptionsViewModel.selectOption(
                    repeatOptionsViewModel.options.first().copy(value = repeatOptionsViewModel.options.first().value * 60)
                )
                delay(1000)
                settingsViewModel.toggleChime(true)
                if (settingsViewModel.uiState.value.settings.isChimeEnabled) {
                    settingsViewModel.startTimerForMinutes(
                        minutes = repeatEveryDuration?.value ?: 0,
                        repeat = true
                    )
                } else {
                    settingsViewModel.cancelTimer()
                }

            }*/

            TimeAnnouncerTheme {
                MainNavGraph(
                    startDestination = AppRoutes.AppMainScreenScreen.route,
                    navController = navController,
                    settingsViewModel = settingsViewModel,
                    soundOptionViewModel = soundOptionViewModel,
                    repeatOptionsViewModel = repeatOptionsViewModel
                )
            }
        }
    }
}



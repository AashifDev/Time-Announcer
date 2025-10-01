package com.dzo.timeannouncer.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dzo.timeannouncer.presentation.screen.time_announcer_screen.TimeAnnouncerScreen
import com.dzo.timeannouncer.presentation.screen.time_announcer_screen.viewmodel.SettingsViewModel
import com.dzo.timeannouncer.presentation.screen.repeatscreen.RepeatEveryScreen
import com.dzo.timeannouncer.presentation.screen.repeatscreen.viewmodel.RepeatOptionsViewModel
import com.dzo.timeannouncer.presentation.screen.soundscreen.SoundScreen
import com.dzo.timeannouncer.presentation.screen.soundscreen.viewmodel.SoundOptionViewModel
import com.dzo.timeannouncer.presentation.screen.vibrationscreen.VibrationScreen

@Composable
fun MainNavGraph(
    startDestination: String,
    navController: NavHostController
) {
    val settingsViewModel: SettingsViewModel = hiltViewModel()
    val soundOptionViewModel: SoundOptionViewModel = hiltViewModel()
    val repeatOptionsViewModel: RepeatOptionsViewModel = hiltViewModel()


    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = AppRoutes.AppMainScreenScreen.route
        ){

            TimeAnnouncerScreen(navController,settingsViewModel,soundOptionViewModel,repeatOptionsViewModel)
        }
        composable(
            route = AppRoutes.RepeatEveryScreen.route
        ){
            RepeatEveryScreen(navController)
        }
        composable(
            route = AppRoutes.SoundScreen.route
        ){
            SoundScreen(navController, soundOptionViewModel)
        }
        composable(
            route = AppRoutes.VibrationScreen.route
        ){
            VibrationScreen(navController)
        }
    }
}
package com.dzo.timeannouncer.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dzo.timeannouncer.presentation.screen.mainscreen.TimeAnnouncerScreen
import com.dzo.timeannouncer.presentation.screen.repeatscreen.RepeatEveryScreen
import com.dzo.timeannouncer.presentation.screen.soundscreen.SoundScreen
import com.dzo.timeannouncer.presentation.screen.vibrationscreen.VibrationScreen

@Composable
fun MainNavGraph(
    startDestination: String,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = AppRoutes.AppMainScreenScreen.route
        ){
            TimeAnnouncerScreen(navController)
        }
        composable(
            route = AppRoutes.RepeatEveryScreen.route
        ){
            RepeatEveryScreen(navController)
        }
        composable(
            route = AppRoutes.SoundScreen.route
        ){
            SoundScreen(navController)
        }
        composable(
            route = AppRoutes.VibrationScreen.route
        ){
            VibrationScreen(navController)
        }
    }
}
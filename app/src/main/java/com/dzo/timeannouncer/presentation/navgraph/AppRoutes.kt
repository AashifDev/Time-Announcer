package com.dzo.timeannouncer.presentation.navgraph


sealed class AppRoutes(
    val route: String,
) {
    data object AppMainScreenScreen : AppRoutes(route = "app_main_screen")
    data object RepeatEveryScreen : AppRoutes(route = "repeat_every_screen")
    data object SoundScreen : AppRoutes(route = "sound_screen")
    data object VibrationScreen : AppRoutes(route = "vibration_screen")
}

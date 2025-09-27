package com.dzo.timeannouncer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.dzo.timeannouncer.presentation.navgraph.AppRoutes
import com.dzo.timeannouncer.presentation.navgraph.MainNavGraph
import com.dzo.timeannouncer.presentation.ui.theme.TimeAnnouncerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            TimeAnnouncerTheme {
                MainNavGraph(
                    startDestination = AppRoutes.AppMainScreenScreen.route,
                    navController = navController
                )
            }
        }
    }
}



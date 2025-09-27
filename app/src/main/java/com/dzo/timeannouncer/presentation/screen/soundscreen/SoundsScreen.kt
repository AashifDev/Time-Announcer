package com.dzo.timeannouncer.presentation.screen.soundscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dzo.timeannouncer.presentation.screen.mainscreen.widget.MyAppBar
import com.dzo.timeannouncer.presentation.screen.soundscreen.viewmodel.SoundOptionViewModel
import com.dzo.timeannouncer.presentation.screen.soundscreen.widget.NotificationSoundList
import com.dzo.timeannouncer.utils.Utils

@Composable
fun SoundScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            MyAppBar(
                title = "Notification Sounds",
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            val context = LocalContext.current
            NotificationSoundList(options,viewModel,selectedOption) { sound ->
                Utils().playSound(context, sound)
            }
        }
    }
}
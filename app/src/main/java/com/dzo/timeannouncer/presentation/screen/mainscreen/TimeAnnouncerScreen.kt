package com.dzo.timeannouncer.presentation.screen.mainscreen

import MainScreen
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dzo.timeannouncer.presentation.screen.mainscreen.viewmodel.SettingsViewModel
import com.dzo.timeannouncer.presentation.screen.mainscreen.widget.MyAppBar
import com.dzo.timeannouncer.presentation.screen.repeatscreen.viewmodel.RepeatOptionsViewModel
import com.dzo.timeannouncer.presentation.screen.soundscreen.viewmodel.SoundOptionViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeAnnouncerScreen(
    navController: NavHostController,
    settingsViewModel: SettingsViewModel,
    soundOptionViewModel: SoundOptionViewModel,
    repeatOptionsViewModel: RepeatOptionsViewModel
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = { MyAppBar(actions = {
            IconButton(onClick = { /* search click */ }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More")
            }
        }) }
    )
    { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MainScreen(navController,settingsViewModel,soundOptionViewModel,repeatOptionsViewModel)
        }
    }
}
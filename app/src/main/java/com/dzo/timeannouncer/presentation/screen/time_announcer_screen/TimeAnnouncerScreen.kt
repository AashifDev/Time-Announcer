package com.dzo.timeannouncer.presentation.screen.time_announcer_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dzo.timeannouncer.presentation.screen.time_announcer_screen.viewmodel.SettingsViewModel
import com.dzo.timeannouncer.presentation.screen.time_announcer_screen.widget.GeneralSettings
import com.dzo.timeannouncer.presentation.screen.time_announcer_screen.widget.Header
import com.dzo.timeannouncer.presentation.screen.time_announcer_screen.widget.MyAppBar
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
@Composable
fun MainScreen(
    navController: NavHostController,
    settingsViewModel: SettingsViewModel,
    soundOptionViewModel: SoundOptionViewModel,
    repeatOptionsViewModel: RepeatOptionsViewModel
) {
    val uiState by settingsViewModel.uiState.collectAsState()
    val selectedOption by repeatOptionsViewModel.selectedOption.collectAsState()
    val selectedSoundOption by soundOptionViewModel.selectedOption.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        //Header Card
        Header(uiState,settingsViewModel,repeatOptionsViewModel)

        Spacer(Modifier.height(20.dp))

        //General settings
        GeneralSettings(navController,selectedOption, selectedSoundOption)

        Spacer(Modifier.height(16.dp))

        // Custom hourly chime
        /*Text("Custom hourly chime", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Enable Custom Chime")
            Spacer(Modifier.weight(1f))
            Switch(
                checked = state.isCustomChimeEnabled,
                onCheckedChange = { viewModel.toggleCustomChime(it) }
            )
        }

        SettingItem(title = "Custom chime", value = "Choose sound")*/
    }
}
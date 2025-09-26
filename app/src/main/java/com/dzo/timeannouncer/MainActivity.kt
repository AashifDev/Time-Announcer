package com.dzo.timeannouncer

import MainScreen
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dzo.timeannouncer.presentation.viewmodel.SettingsViewModel
import com.dzo.timeannouncer.presentation.ui.theme.TimeAnnouncerTheme
import com.dzo.timeannouncer.presentation.viewmodel.TimerViewModel
import com.dzo.timeannouncer.presentation.widgets.CircularTimerProgress
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimeAnnouncerTheme {
                TimeAnnounceScreen()
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeAnnounceScreen() {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = { MyAppBar() }
    )
    { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            MainScreen()
        }
    }
}

@Composable
fun StartTimer() {
    val viewModel :TimerViewModel = hiltViewModel()
    var totalTime by remember { mutableIntStateOf(10) }
    val elapsed by viewModel.elapsedTime

    LaunchedEffect(Unit) {
        viewModel.startTimer(totalTime)
    }
        //CircularTimerProgress(elapsed = elapsed, total = totalTime,size = 200)

    /*Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularTimerProgress(elapsed = elapsed, total = totalTime)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { viewModel.startTimer(totalTime) }) {
            Text("Start Timer")
        }
    }*/

}

@Preview
@Composable
private fun Preview() {
    TimeAnnounceScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar() {
    TopAppBar(
        title = { Text("Time Announcer") },
        actions = {
            IconButton(onClick = { /* search click */ }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More")
            }
        },
    )
}

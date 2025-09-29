import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dzo.timeannouncer.presentation.screen.mainscreen.viewmodel.SettingsViewModel
import com.dzo.timeannouncer.presentation.screen.mainscreen.widget.GeneralSettings
import com.dzo.timeannouncer.presentation.screen.mainscreen.widget.Header
import com.dzo.timeannouncer.presentation.screen.repeatscreen.viewmodel.RepeatOptionsViewModel
import com.dzo.timeannouncer.presentation.screen.soundscreen.viewmodel.SoundOptionViewModel

@Composable
fun MainScreen(
    navController: NavHostController,
    settingsViewModel: SettingsViewModel,
    soundOptionViewModel: SoundOptionViewModel,
    repeatOptionsViewModel1: RepeatOptionsViewModel
) {
    val uiState by settingsViewModel.uiState.collectAsState()
    //val totalTime = 10
    //val elapsed by remember { settingsViewModel.elapsedTime }

    val repeatOptionsViewModel: RepeatOptionsViewModel = hiltViewModel()
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
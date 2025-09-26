import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dzo.timeannouncer.presentation.viewmodel.MainScreenUiState
import com.dzo.timeannouncer.presentation.viewmodel.SettingsViewModel
import com.dzo.timeannouncer.presentation.widgets.CircularTimerProgress

@Composable
fun MainScreen(viewModel: SettingsViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()
    val totalTime = 10
    val elapsed by remember { viewModel.elapsedTime }

    val repeatOptions = listOf(
        "2 Minutes",
        "5 Minutes",
        "10 Minutes",
        "15 Minutes",
        "30 Minutes",
        "1 Hour"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // 🔹 Header Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF00BFFF)) // Sky blue
        ) {
            //Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(100.dp)
                ) {
                    CircularTimerProgress(
                        elapsed = elapsed,
                        total = totalTime,
                        isRunning = state.isChimeEnabled
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = if (state.isChimeEnabled) "12:05 PM" else "OFF",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        if (state.isChimeEnabled) {
                            Text(
                                text = "Next Chime",
                                fontSize = 12.sp,
                                color = Color.Black
                            )
                        }
                    }
                }

                Spacer(Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = if (state.isChimeEnabled) "Hourly Chime ON" else "Hourly Chime OFF",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "Enable repeat chime for hourly chime",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Switch(
                    checked = state.isChimeEnabled,
                    onCheckedChange = { enabled ->
                        viewModel.toggleChime(enabled)
                        viewModel.toggleChime(enabled)
                        if (enabled) {
                            viewModel.startTimer(totalTime = 60) // for example: 60 sec
                        } else {
                            viewModel.cancelTimer()
                        }
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.primary,
                        checkedTrackColor = Color(0xFFFFFFFF),
                        disabledCheckedBorderColor = Color.Transparent,
                        disabledCheckedThumbColor = Color.Transparent,
                        uncheckedBorderColor = Color.Transparent
                    ),
                    modifier = Modifier.wrapContentWidth()
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        //General settings
        GeneralSettings(repeatOptions, state, viewModel)

        Spacer(Modifier.height(16.dp))

        // Custom hourly chime
        Text("Custom hourly chime", style = MaterialTheme.typography.titleMedium)
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

        SettingItem(title = "Custom chime", value = "Choose sound")
    }
}

@Composable
fun GeneralSettings(
    repeatOptions: List<String>,
    state: MainScreenUiState,
    viewModel: SettingsViewModel
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF))
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(16.dp)
        ) {
            Text("General settings", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            /*SettingItemDropdown(
                title = "Repeat every",
                options = repeatOptions,
                selectedOption = state.repeatEvery,
                onOptionSelected = { viewModel.updateRepeatEvery(it) }
            )*/
            SettingItem(
                icon = Icons.Default.Repeat,
                title = "Repeat every",
                value = state.vibration
            )
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color(0x97F1EFEF))
            )
            SettingItem(
                icon = Icons.Default.MusicNote, title = "Sounds", value = state.sound
            )
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color(0x97F1EFEF))
            )
            SettingItem(
                icon = Icons.Default.Vibration,
                title = "Vibration",
                value = state.vibration
            )
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color(0x97F1EFEF))
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
            ) {
                SettingItem(
                    icon = Icons.Default.VolumeUp,
                    title = "Volume",
                    value = state.vibration
                )
                Spacer(Modifier.height(1.dp))
                Slider(
                    value = state.volume.toFloat(),
                    onValueChange = { viewModel.updateVolume(it.toInt()) },
                    valueRange = 0f..100f
                )
            }
        }

    }
}

@Composable
fun SettingItem(icon: ImageVector? = null, title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            if (icon != null) {
                Icon(imageVector = icon, contentDescription = null)
                Spacer(Modifier.width(12.dp))
            }
            Spacer(Modifier.width(12.dp))
            Text(title, style = MaterialTheme.typography.bodyLarge)
        }
        Text(value)
    }
}

@Composable
fun SettingItemDropdown(
    title: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .clickable { expanded = true }
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(imageVector = Icons.Default.Sync, contentDescription = "Repeat")
            Spacer(Modifier.width(12.dp))
            Text(title, style = MaterialTheme.typography.bodyLarge)
        }

        Box {
            Text(
                text = selectedOption,
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                shadowElevation = MenuDefaults.ShadowElevation,
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        },
                        modifier = Modifier
                            .background(Color.White)
                    )
                }
            }
        }
    }
}

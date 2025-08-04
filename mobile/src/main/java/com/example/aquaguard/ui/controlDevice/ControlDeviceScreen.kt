package com.example.aquaguard.ui.controlDevice

import SensorViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aquaguard.ui.theme.AquaGuardTheme
import com.example.aquaguard.ui.controlDevice.components.ChangeValveComponent
import com.example.aquaguard.ui.home.viewmodels.WaterViewModel
import androidx.compose.runtime.getValue
import com.example.aquaguard.ui.home.viewmodels.WeatherViewModel

@Composable
fun ControlDeviceScreen(
    sensorViewModel: SensorViewModel = viewModel(),
    waterViewModel: WaterViewModel = viewModel(),
) {
    val noFilterValveStateValue = sensorViewModel.noFilterValve.collectAsState()
    val filterValveStateValue = sensorViewModel.filterValve.collectAsState()

    val waterData by waterViewModel.waterInfo.collectAsState()
    val waterNotNull = waterData.filterNotNull()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        sensorViewModel.connectToHiveMQ()
        waterViewModel.loadWaterData(context)
    }

    AquaGuardTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .background(color = MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Box (
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Gestión de válvulas de agua",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        "Gestiona el paso de agua en tu sistema.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                ChangeValveComponent(
                    noFilterValveStateValue.value,
                    onValveClick = { newValveValue ->
                        sensorViewModel.openNoFilterValve(newValveValue)
                    },
                    "NOFILTER"
                )
            }

            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                ChangeValveComponent(
                    filterValveStateValue.value,
                    onValveClick = { newValveValue ->
                        sensorViewModel.openFilterValve(newValveValue)
                    },
                    "FILTER"
                )
            }
        }
    }
}

package com.example.aquaguard.presentation.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.aquaguard.presentation.ui.main.components.DateCard
import com.example.aquaguard.presentation.ui.main.components.PHCard
import com.example.aquaguard.presentation.ui.main.components.TDSCard
import com.example.aquaguard.presentation.ui.main.components.TankCard
import com.example.aquaguard.presentation.ui.main.components.TemperatureCard
import com.example.aquaguard.presentation.ui.main.viewmodels.DeviceInformationViewModel
import java.util.Objects

@Composable
fun DetailsScreenSecondTank(
    waterViewModel: DeviceInformationViewModel,
) {
    val receivedData by waterViewModel.secondTankInformation.collectAsState()
    val lastData = receivedData.lastOrNull()

    LaunchedEffect(Unit) {
        waterViewModel.loadWaterData()
    }

    if (receivedData.isEmpty() || lastData == null) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text("No hay datos disponibles", style = MaterialTheme.typography.body1)
        }
    } else {
        lastData.let { data ->
            ScalingLazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Text(
                        "Tanque sin filtrar",
                        style = MaterialTheme.typography.title1,
                        color = MaterialTheme.colors.primary
                    )
                }
                item {
                    TankCard(tank = data.tipoTanque.name)
                }
                item {
                    PHCard(ph = data.ph)
                }
                item {
                    TemperatureCard(temp = data.temperatura)
                }
                item {
                    TDSCard(tds = data.tds)
                }
                item {
                    DateCard(date = data.getFechaFormateada())
                }
            }
        }
    }
}
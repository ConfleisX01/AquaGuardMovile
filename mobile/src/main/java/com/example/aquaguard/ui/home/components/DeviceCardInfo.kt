package com.example.aquaguard.ui.home.components

import SensorViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aquaguard.ui.home.viewmodels.AQIViewModel
import com.example.aquaguard.ui.home.viewmodels.WaterViewModel
import com.example.aquaguard.ui.home.viewmodels.WeatherViewModel
import androidx.compose.ui.platform.LocalContext

@Composable
fun DeviceCardInfo(
    viewModel: AQIViewModel = viewModel(),
    weatherViewModel: WeatherViewModel = viewModel(),
    sensorViewModel: SensorViewModel = viewModel(),
    waterViewModel: WaterViewModel = viewModel()
) {
    val context = LocalContext.current

    val scrollState = rememberScrollState()
    val datosConAQI by viewModel.dataListState.collectAsState()
    val ultimoDato = datosConAQI.lastOrNull()

    val weaterData by weatherViewModel.weather.collectAsState()

    val waterFilterData by waterViewModel.waterFilter.collectAsState()
    val waterStoredFilter = waterFilterData.lastOrNull()

    val waterNoFilterData by waterViewModel.waterNoFilter.collectAsState()
    val waterStoredNoFilter = waterNoFilterData.lastOrNull()

    val storeNoFilter by sensorViewModel.storeNoFilter.collectAsState()
    val storeFilter by sensorViewModel.storeFilter.collectAsState()

    val noFilterLevel = storeNoFilter?.waterLevel
    val filterLevel = storeFilter?.waterLevel

    LaunchedEffect(Unit) {
        sensorViewModel.connectToHiveMQ()
        waterViewModel.loadWaterData(context)
        viewModel.cargarDatosAQI()
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {

        DeviceCardWeatherInfoCard(ultimoDato, weaterData, storeNoFilter, waterStoredFilter)

        // Niveles de agua primero (noFilterLevel y filterLevel)
        Text(
            "Información de los almacenes",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
        )
        DeviceCardWaterInfo(
            storeNoFilterWater = noFilterLevel ?: 0f,
            storeFilterWater = filterLevel ?: 0f
        )

        // Stats con waterStoredFilter y waterStoredNoFilter primero
        Text(
            "Información del agua en los almacenes",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.onSurface
        )

        DeviceCardWaterStats(
            title = "Agua sin filtrar",
            ph = storeNoFilter?.ph?.toString() ?: "--",
            temperature = storeNoFilter?.temperature?.toString() ?: "--",
            ores = storeNoFilter?.tds?.toString() ?: "--"
        )
        DeviceCardWaterStats(
            title = "Agua filtrada",
            ph = storeFilter?.ph?.toString() ?: "--",
            temperature = storeFilter?.temperature?.toString() ?: "--",
            ores = storeFilter?.tds?.toString() ?: "--"
        )
    }
}

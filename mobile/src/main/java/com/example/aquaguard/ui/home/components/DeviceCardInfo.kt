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
    val context = LocalContext.current // Contexto

    val scrollState = rememberScrollState() // Scroll State
    val datosConAQI by viewModel.dataListState.collectAsState() // Datos del aire con AQI
    val ultimoDato = datosConAQI.lastOrNull() // Ultimo dato recolectado del aire con AQI

    val weaterData by weatherViewModel.weather.collectAsState()

    val waterFilterData by waterViewModel.waterFilter.collectAsState() // Datos de la agua almacendada
    var waterStoredFilter = waterFilterData.lastOrNull() // Ultimo dato de la informacion del agua

    val waterNoFilterData by waterViewModel.waterNoFilter.collectAsState() // Datos de la agua almacendada
    var waterStoredNoFilter =
        waterNoFilterData.lastOrNull() // Ultimo dato de la informacion del agua

    // Datos de los sensores
    val storeNoFilter by sensorViewModel.storeNoFilter.collectAsState() // Almacen sin filtrar
    val storeFilter by sensorViewModel.storeFilter.collectAsState() // Almacen filtrado

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

        Text(
            "Información de los almacenes",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
        )
        if (noFilterLevel != null && filterLevel != null) {
            DeviceCardWaterInfo(storeNoFilterWater = noFilterLevel, storeFilterWater = filterLevel)
        } else {
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Parece que aún no hay agua registrada",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        "Te avisaremos cuando haya datos disponibles",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        Text(
            "Información del agua en los almacenes",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.onSurface
        )

        if (storeNoFilter != null && storeFilter != null) {
            DeviceCardWaterStats(
                title = "Agua sin filtrar",
                ph = storeNoFilter?.ph.toString(),
                temperature = storeNoFilter?.temperature.toString(),
                ores = storeNoFilter?.tds.toString()
            )

            DeviceCardWaterStats(
                title = "Agua filtrada",
                ph = storeFilter?.ph.toString(),
                temperature = storeFilter?.temperature.toString(),
                ores = storeFilter?.tds.toString()
            )
        } else if (waterStoredFilter != null && waterStoredNoFilter != null) {
            DeviceCardWaterStats(
                title = "Agua sin filtrar",
                ph = waterStoredFilter.ph.toString(),
                temperature = waterStoredFilter.temperatura.toString(),
                ores = waterStoredFilter.tds.toString()
            )

            DeviceCardWaterStats(
                title = "Agua filtrada",
                ph = waterStoredNoFilter.ph.toString(),
                temperature = waterStoredNoFilter.temperatura.toString(),
                ores = waterStoredNoFilter.tds.toString()
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "No hay datos para mostrar",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    "Los datos se mostrarán aquí cuando existan",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }

    }
}
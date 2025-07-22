package com.example.aquaguard.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aquaguard.ui.home.HomeViewModel
import com.example.aquaguard.ui.home.utils.classifyAqi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DeviceCardInfo (viewModel: HomeViewModel = viewModel()) {
    val scrollState = rememberScrollState()
    val datosConAQI by viewModel.dataListState.collectAsState()
    val ultimoDato = datosConAQI.lastOrNull()
    val ultimaActualizacion = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.cargarDatosPeriodicos()
    }

    LaunchedEffect(ultimoDato) {
        if (ultimoDato != null) {
            ultimaActualizacion.value =
                SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        }
    }

    Column (
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        DeviceCardWeatherInfoCard(ultimoDato?.aqiEstimado ?: 20, true, true, viewModel)
        Text(
            "Información de los almacenes",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
        )
        DeviceCardWaterInfo()
        Text(
            "Información del agua en los almacenes",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.onSurface
        )
        DeviceCardWaterStats(title = "Agua sin filtrar", ph = "Malo", temperature = 18, ores = "Alto")
        DeviceCardWaterStats(title = "Agua filtrada", ph = "Bueno", temperature = 22, ores = "Bajo")
        ultimoDato?.let {
            Text("Estado del AQI: ${classifyAqi(it.aqiEstimado)} a las: ${ultimaActualizacion.value}")
        }
    }
}
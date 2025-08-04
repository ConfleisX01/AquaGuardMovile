package com.example.aquaguard.ui.home.components

import WaterData
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aquaguard.ui.home.viewmodels.WeatherViewModel
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aquaguard.data.models.AirInformationWithAQI
import com.example.aquaguard.data.models.WaterQualityClass
import com.example.aquaguard.data.models.WeatherInfo

@Composable
fun DeviceCardWeatherInfoCard (
    aqiData: AirInformationWithAQI?,
    weatherData: WeatherInfo?,
    waterData: WaterData?,
    waterStoredData: WaterQualityClass?
) {
    val weatherViewModel: WeatherViewModel = viewModel()

    val title = weatherViewModel.dataWeatherDesitionTitle.collectAsState()
    val description = weatherViewModel.dataWeatherDesition.collectAsState()

    LaunchedEffect(aqiData, weatherData, waterStoredData, waterData) {
        weatherViewModel.verifyWeather(aqiData, weatherData, waterData, waterStoredData)
    }

    OutlinedCard (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        border = CardDefaults.outlinedCardBorder(
            enabled = false
        ),
        modifier  = Modifier
            .fillMaxWidth()
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.Center
        ) {
            if (aqiData?.aqiEstimado == -1) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        "Aún no hay datos",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    )
                    Text(
                        "Recopilaremos información cuando llueva",
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            } else {
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (title.value.isBlank()) {
                        Text(
                            text = "",
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                                .placeholder(
                                    visible = true,
                                    highlight = PlaceholderHighlight.shimmer(),
                                    color = MaterialTheme.colorScheme.primary
                                )
                        )
                    } else {
                        Text(
                            text = title.value,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Text(
                            text = "AQI: ${aqiData?.aqiEstimado.toString()}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }

                if (description.value.isBlank()) {
                    Text(
                        text = "Cargando...",
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .placeholder(
                                visible = true,
                                highlight = PlaceholderHighlight.shimmer(),
                                color = MaterialTheme.colorScheme.primary
                            )
                    )
                } else {
                    Text(text = description.value, color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    }
}
package com.example.aquaguard.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aquaguard.ui.home.HomeViewModel
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder

@Composable
fun DeviceCardWeatherInfoCard (aqi: Int = 0, isRaining: Boolean = false, isLowWater: Boolean = false, viewModel: HomeViewModel = viewModel()) {
    LaunchedEffect(Unit) {
        viewModel.verifyWeather(aqi, isRaining, isLowWater)
    }

    val title = viewModel.dataWeatherDesitionTitle.collectAsState()
    val description = viewModel.dataWeatherDesition.collectAsState()

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
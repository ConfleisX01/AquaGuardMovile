package com.example.aquaguard.ui.debug

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aquaguard.data.models.WeatherInfo

@Composable
fun DebugScreen(
    viewModel: DebugViewModel
) {
    val weatherTestData by viewModel.weatherTestData.collectAsState()

    // Cargar datos al inicio
    LaunchedEffect(Unit) {
        viewModel.loadWeatherTestData()
    }

    // Si aún no está cargado, no mostrar nada
    weatherTestData?.let { weatherInfo ->

        // Estados locales para modificar los valores
        var temp by remember { mutableStateOf(weatherInfo.temperature) }
        var wind by remember { mutableStateOf(weatherInfo.windspeed) }
        var humidity by remember { mutableStateOf(weatherInfo.humidity) }
        var precipitation by remember { mutableStateOf(weatherInfo.precipitation) }
        var cloudcover by remember { mutableStateOf(weatherInfo.cloudcover) }
        var description by remember { mutableStateOf(weatherInfo.description) }

        Column(modifier = Modifier.padding(16.dp)) {
            Text("Configuración de Clima Falso", style = MaterialTheme.typography.titleLarge)

            SliderWithLabel("Temperatura", temp, 0.0..50.0) { temp = it }
            SliderWithLabel("Viento", wind, 0.0..30.0) { wind = it }
            SliderWithLabel("Humedad", humidity, 0.0..100.0) { humidity = it }
            SliderWithLabel("Precipitación", precipitation, 0.0..100.0) { precipitation = it }
            SliderWithLabel("Nubosidad", cloudcover, 0.0..100.0) { cloudcover = it }

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )

            Button(
                onClick = {
                    val fakeWeather = WeatherInfo(
                        temperature = temp,
                        windspeed = wind,
                        humidity = humidity,
                        precipitation = precipitation,
                        cloudcover = cloudcover,
                        description = description,
                        iconCode = 1 // puedes cambiarlo o dejarlo así
                    )
                    viewModel.updateWeatherTestData(fakeWeather)
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Aplicar Clima Falso")
            }
        }
    }
}

@Composable
fun SliderWithLabel(label: String, value: Double, range: ClosedFloatingPointRange<Double>, onValueChange: (Double) -> Unit) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text("$label: ${value.toInt()}")
        Slider(
            value = value.toFloat(),
            onValueChange = { onValueChange(it.toDouble()) },
            valueRange = range.start.toFloat()..range.endInclusive.toFloat(),
            steps = 10
        )
    }
}

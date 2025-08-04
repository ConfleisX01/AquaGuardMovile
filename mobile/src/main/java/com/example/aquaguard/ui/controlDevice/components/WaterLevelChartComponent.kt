package com.example.aquaguard.ui.controlDevice.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.aquaguard.data.models.WaterQualityClass
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStart
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberTop
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries

@Composable
fun WaterLevelChart(data: List<WaterQualityClass>, modifier: Modifier = Modifier) {
    val modelProducer = remember { CartesianChartModelProducer() }
    val tiempos = data.map { it.tiempo }

    LaunchedEffect(data) {
        modelProducer.runTransaction {
            lineSeries {
                series(data.map { it.cantidad })
            }
        }
    }

    if (data.isEmpty()) {
        Text("No hay datos, sabe por que")
    } else {
        CartesianChartHost(
            chart = rememberCartesianChart(
                rememberLineCartesianLayer(),
                startAxis = VerticalAxis.rememberStart(
                    title = "Holi"
                ),
                bottomAxis = HorizontalAxis.rememberBottom(
                    title = "Tiempo",
                ),
                topAxis  = HorizontalAxis.rememberTop(),
            ),
            modelProducer = modelProducer,
            modifier = modifier
        )
    }
}
package com.example.aquaguard.ui.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import com.example.aquaguard.ui.theme.AquaGuardTheme
import com.example.aquaguard.R
import com.example.aquaguard.ui.home.viewmodels.RainViewModel
import java.time.LocalDate

@Composable
@Preview(showBackground = true)
fun RainHistoricalCalendar (viewModel: RainViewModel = viewModel()) {
    val dataRain by viewModel.rainDays.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadDataRain()
    }

    AquaGuardTheme {
        OutlinedCard (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
            ) {
                Text("Historial de lluvias")
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (!dataRain.isNullOrEmpty()) {
                        dataRain.forEach { day ->
                            val dayConverted = LocalDate.parse(day!!.tiempo)
                            RainHistoricalCalendarItem(
                                day = dayConverted.dayOfMonth,
                                itRained = day.lluviaDetectada
                            )
                        }
                    } else {
                        Text(
                            "No hay datos para mostrar",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RainHistoricalCalendarItem(day: Int = 0, itRained: Boolean = false) {
    val borderColor = if (itRained) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
    val backgroundColor = if (itRained) MaterialTheme.colorScheme.primaryContainer else Color.Transparent
    val contentColor = if (itRained) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface

    Card (
        modifier = Modifier
            .size(60.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        border = BorderStroke(1.dp, borderColor),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = day.toString(),
                color = contentColor,
                style = MaterialTheme.typography.titleSmall
            )
            Image(
                painter = painterResource(id = if (itRained) R.drawable.rain else R.drawable.sun),
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

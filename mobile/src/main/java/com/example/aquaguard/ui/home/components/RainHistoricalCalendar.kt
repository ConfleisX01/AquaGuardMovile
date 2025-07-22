package com.example.aquaguard.ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aquaguard.ui.theme.AquaGuardTheme

@Composable
@Preview(showBackground = true)
fun RainHistoricalCalendar () {
    AquaGuardTheme {
        OutlinedCard (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text("Historial de lluvias")

            }
        }
    }
}
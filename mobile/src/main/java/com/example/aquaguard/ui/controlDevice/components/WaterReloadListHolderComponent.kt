package com.example.aquaguard.ui.controlDevice.components

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun WaterReloadListHolderComponent() {
    Column (
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Historial", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
        WaterReloadListItemComponent()
        WaterReloadListItemComponent()
        WaterReloadListItemComponent()
        WaterReloadListItemComponent()
        WaterReloadListItemComponent()
        WaterReloadListItemComponent()
        WaterReloadListItemComponent()
        WaterReloadListItemComponent()
    }
}